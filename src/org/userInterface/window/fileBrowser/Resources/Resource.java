package org.userInterface.window.fileBrowser.Resources;

import org.applicationEngine.objects.ObjectType;

import javax.swing.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public abstract class Resource extends JPanel {

    private String filePath;

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
}
