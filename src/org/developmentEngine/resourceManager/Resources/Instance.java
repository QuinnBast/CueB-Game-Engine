package org.developmentEngine.resourceManager.Resources;

import org.developmentEngine.resourceManager.resourceProperties.InstanceProperties;

/**
 * Created by Quinn on 5/26/2018.
 */
public class Instance extends Resource {

    public Instance(String path) {
        super(path);
    }

    public Instance(ObjectResource obj){
        super(obj.getFilePath() + "ins");
        this.resourceProperties = new InstanceProperties();
        ((InstanceProperties)this.resourceProperties).setParentObject(obj);
    }
}
