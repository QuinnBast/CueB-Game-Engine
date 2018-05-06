package org.userInterface.fileBrowser.Resources;

import org.applicationEngine.objects.Base.SpriteObject;
import org.developmentEngine.resourceManager.resourceTypes.BaseResourceType;
import org.developmentEngine.resourceManager.resourceTypes.Sprite;

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

}
