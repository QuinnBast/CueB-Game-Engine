package org.developmentEngine.resourceManager.Resources;

import com.rits.cloning.Cloner;
import org.applicationEngine.objects.ObjectType;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;

import javax.swing.*;
import java.io.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public abstract class Resource extends JPanel implements Serializable {

    private String filePath;
    protected ResourceProperties resourceProperties;

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
