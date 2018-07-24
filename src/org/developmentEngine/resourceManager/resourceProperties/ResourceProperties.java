package org.developmentEngine.resourceManager.resourceProperties;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Quinn on 5/19/2018.
 */
public abstract class ResourceProperties implements Serializable {

    private transient ArrayList<PropertyObserver> propertyObservers = new ArrayList<>();

    public ResourceProperties(ResourceProperties copy){
        this.propertyObservers = copy.propertyObservers;
    }

    public ResourceProperties() {

    }

    public void notifyUpdate(ResourceProperties properties){
        for(PropertyObserver po : propertyObservers){
                po.onPropertyUpdate(properties);
        }
    }

    public void addPropertyObserver(PropertyObserver po){
        this.propertyObservers.add(po);
    }

    public void removePropertyObserver(PropertyObserver po){
        this.propertyObservers.remove(po);
    }

    public ArrayList<PropertyObserver> getObservers(){
        return this.propertyObservers;
    }

    public void removeObservers(){
        this.propertyObservers.clear();
    }

    public void setPropertyObservers(ArrayList<PropertyObserver> observers){
        this.propertyObservers.addAll(observers);
    }

    public void instantiate(){
        this.propertyObservers = new ArrayList<>();
    }


}
