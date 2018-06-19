package org.developmentEngine.resourceManager.Resources;

import org.developmentEngine.resourceManager.resourceProperties.InstanceProperties;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;

/**
 * Created by Quinn on 5/26/2018.
 */
public class Instance extends Resource {

    public Instance(String path) {
        super(path);
    }

    @Override
    public InstanceProperties getProperties() {
        return (InstanceProperties)this.resourceProperties;
    }

    public Instance(ObjectResource obj){
        super(obj.getFilePath());
        this.resourceProperties = new InstanceProperties();
        ((InstanceProperties)this.resourceProperties).setParentObject(obj);
    }
}
