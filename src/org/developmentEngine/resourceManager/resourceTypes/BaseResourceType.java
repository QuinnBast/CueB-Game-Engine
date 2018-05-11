package org.developmentEngine.resourceManager.resourceTypes;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceManager;

/**
 * Created by Quinn on 5/5/2018.
 */
public abstract class BaseResourceType {

    public BaseResourceType(){
        if(this instanceof Object){
            Object self = (Object) this;
            DevelopmentEngine.resourceManager.objectList.add(self);
        } else if (this instanceof Sprite){
            Sprite self = (Sprite) this;
            DevelopmentEngine.resourceManager.spriteList.add(self);
        } else if (this instanceof Room){
            Room self = (Room) this;
            DevelopmentEngine.resourceManager.roomList.add(self);
        } else if (this instanceof Script){
            Script self = (Script) this;
            DevelopmentEngine.resourceManager.scriptList.add(self);
        }
    }
}
