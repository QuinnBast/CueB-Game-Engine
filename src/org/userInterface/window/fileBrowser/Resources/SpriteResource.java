package org.userInterface.window.fileBrowser.Resources;

import org.developmentEngine.resourceManager.resourceProperties.ScriptProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;

/**
 * Created by Quinn on 5/5/2018.
 */
public class SpriteResource extends Resource {

    public SpriteResource(String filePath){
        super(filePath + ".spr");
        this.resourceProperties = new SpriteProperties();
    }
}
