package org.developmentEngine.resourceManager.Resources;

import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;

/**
 * Created by Quinn on 5/5/2018.
 */
public class SpriteResource extends Resource {

    public SpriteResource(String filePath){
        super(filePath + ".spr");
        this.resourceProperties = new SpriteProperties();
    }

    @Override
    public SpriteProperties getProperties() {
        return (SpriteProperties)this.resourceProperties;
    }
}
