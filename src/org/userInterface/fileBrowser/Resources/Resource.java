package org.userInterface.fileBrowser.Resources;

import org.applicationEngine.objects.Base.SpriteObject;
import org.applicationEngine.objects.ObjectType;
import org.developmentEngine.resourceManager.resourceTypes.*;
import org.developmentEngine.resourceManager.resourceTypes.Object;

import javax.swing.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public abstract class Resource extends JPanel {

    private String filePath;
    private BaseResourceType referencedObject;

    public Resource(String path, BaseResourceType object){
        this.filePath = path;
        this.referencedObject = object;
    }

    public String getFilePath(){
        return this.filePath;
    }

    public BaseResourceType getReferencedObject(){
        return this.referencedObject;
    }

    public ObjectType getObjectType(){
        if(referencedObject instanceof Sprite){
            return ObjectType.SPRITE;
        } else if (referencedObject instanceof Room){
            return ObjectType.ROOM;
        } else if (referencedObject instanceof Object){
            return ObjectType.OBJECT;
        } else if (referencedObject instanceof Script){
            return ObjectType.SCRIPT;
        }
        return null;
    }

}
