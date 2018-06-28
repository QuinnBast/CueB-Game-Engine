package org.developmentEngine.resourceManager.Resources;

import com.rits.cloning.Cloner;
import org.applicationEngine.objects.ObjectType;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Quinn on 5/4/2018.
 */
public abstract class Resource {

    private String filePath;
    protected ResourceProperties resourceProperties;
    private ArrayList<ResourceObserver> observers = new ArrayList<>();

    public void notifyUpdate(){
        for(ResourceObserver ro : observers){
            ro.onResourceUpdate(this);
        }
    }

    public void addResourceObserver(ResourceObserver ro){
        this.observers.add(ro);
    }

    public void removeResourceObserver(ResourceObserver ro){
        this.observers.remove(ro);
    }

    public Resource deepCopy(){
        //Copy constructor allows the creation of copy resources on runtime so that manipulation of variables during the runtime does not change objects in the development engine
        Cloner cloner = new Cloner();
        return (Resource)cloner.deepClone(this);
    }

    public Resource(String path){
        this.filePath = path;
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
}
