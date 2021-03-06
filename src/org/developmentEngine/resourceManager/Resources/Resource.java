package org.developmentEngine.resourceManager.Resources;

import com.rits.cloning.Cloner;
import org.applicationEngine.objects.ObjectType;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Quinn on 5/4/2018.
 */
public abstract class Resource extends JPanel implements Serializable {

    private String filePath;
    private UUID uuid = UUID.randomUUID();
    protected ResourceProperties resourceProperties;
    private transient ArrayList<ResourceObserver> observers;

    public Resource(String path){
        this.observers = new ArrayList<>();
        this.filePath = path;
    }

    public void notifyUpdate(){
        for(ResourceObserver ro : observers){
            ro.onResourceUpdate(this);
        }
    }

    public UUID getUuid(){
        return this.uuid;
    }

    public void addResourceObserver(ResourceObserver ro){
        this.observers.add(ro);
    }

    public void removeResourceObserver(ResourceObserver ro){
        this.observers.remove(ro);
    }

    public Resource(Resource copy){
        this.filePath = copy.filePath;
        this.uuid = copy.uuid;
        this.observers = copy.observers;
    }

    public Resource deepCopy(){
        //Copy constructor allows the creation of copy resources on runtime so that manipulation of variables during the runtime does not change objects in the development engine
        Cloner cloner = new Cloner();
        return (Resource)cloner.deepClone(this);
    }

    public String getFilePath(){
        return this.filePath;
    }

    public void setFilePath(String path){
        this.filePath = path;
        this.notifyUpdate();
    }

    public ObjectType getObjectType(){
        if(this instanceof SpriteResource){
            return ObjectType.SPRITE;
        } else if (this instanceof RoomResource){
            return ObjectType.ROOM;
        } else if (this instanceof ObjectResource){
            return ObjectType.OBJECT;
        } else if (this instanceof ScriptResource){
            return ObjectType.SCRIPT;
        }
        return null;
    }

    public ResourceProperties getProperties(){
        return this.resourceProperties;
    }

    @Override
    public String toString() {
        return this.filePath;
    }

    public ArrayList<ResourceObserver> getObservers(){
        return this.observers;
    }

    public void removeObservers(){
        this.observers.clear();
    }

    public void setObservers(ArrayList<ResourceObserver> observers){
        this.observers = observers;
    }

    public void instantiate(){
        this.observers = new ArrayList<>();
        this.getProperties().instantiate();
    }
}
