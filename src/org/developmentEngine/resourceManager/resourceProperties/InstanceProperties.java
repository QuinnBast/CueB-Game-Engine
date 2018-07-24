package org.developmentEngine.resourceManager.resourceProperties;

import org.developmentEngine.resourceManager.Resources.ObjectResource;

import java.awt.geom.Point2D;

/**
 * Created by Quinn on 5/26/2018.
 */
public class InstanceProperties extends ResourceProperties {

    private ObjectResource parentObject;
    private Point2D.Double roomLocation;


    public InstanceProperties(){

    }

    public void setRoomLocation(Point2D.Double point){
        this.roomLocation = point;
        this.notifyUpdate(this);
    }

    public void setParentObject(ObjectResource obj){
        this.parentObject = obj;
    }

    public ObjectResource getParentObject(){
        return this.parentObject;
    }

    public Point2D.Double getRoomLocation(){
        return this.roomLocation;
    }

}
