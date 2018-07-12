package org.developmentEngine.resourceManager.resourceProperties;

import org.developmentEngine.resourceManager.Resources.Instance;
import org.developmentEngine.resourceManager.Resources.ObjectResource;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Quinn on 5/19/2018.
 */
public class RoomProperties extends ResourceProperties {

    private Rectangle2D size = new Rectangle2D.Double(0, 0, 500, 500);
    private String name = "";
    private int desiredFramerate = 60;
    private Color backgroundColor = Color.BLACK;
    private String backgroundImageLink = "";
    private ArrayList<Instance> instanceList = new ArrayList<>();

    public Rectangle2D getSize() {
        return size;
    }

    public void setSize(Rectangle2D size) {
        this.size = size;

        //Remove all instances that are not in the room
        for(Instance instance : instanceList){
            if(!this.size.contains(instance.getProperties().getRoomLocation())){
                this.instanceList.remove(instance);
            }
        }

        this.notifyUpdate(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifyUpdate(this);
    }

    public int getDesiredFramerate() {
        return desiredFramerate;
    }

    public void setDesiredFramerate(int desiredFramerate) {
        this.desiredFramerate = desiredFramerate;
        this.notifyUpdate(this);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.notifyUpdate(this);
    }

    public String getBackgroundImageLink() {
        return backgroundImageLink;
    }

    public void setBackgroundImageLink(String backgroundImageLink) {
        this.backgroundImageLink = backgroundImageLink;
        this.notifyUpdate(this);
    }

    public void addInstance(Instance i){
        this.instanceList.add(i);
        this.notifyUpdate(this);
    }

    public ArrayList<Instance> getInstances(){
        return this.instanceList;
    }

    public void removeInstance(Instance i){
        if(this.instanceList.contains(i)){
            this.instanceList.remove(i);
        }
        this.notifyUpdate(this);
    }

    public RoomProperties(){

    }
}
