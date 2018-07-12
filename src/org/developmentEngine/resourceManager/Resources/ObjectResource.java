package org.developmentEngine.resourceManager.Resources;


import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;

/**
 * Created by Quinn on 5/5/2018.
 */
public class ObjectResource extends Resource {

    public ObjectResource(String path) {
        super(path + ".obj");
        ObjectProperties op = new ObjectProperties();
        this.resourceProperties = op;
    }

    @Override
    public ObjectProperties getProperties() {
        return (ObjectProperties)this.resourceProperties;
    }
}
