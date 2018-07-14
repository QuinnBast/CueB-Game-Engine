package org.developmentEngine.resourceManager.resourceProperties;

import org.developmentEngine.resourceManager.Resources.Resource;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Quinn on 5/19/2018.
 */
public abstract class ResourceProperties implements Serializable {

    private ArrayList<PropertyObserver> propertyObservers = new ArrayList<>();

    public void notifyUpdate(ResourceProperties properties){
        for(PropertyObserver po : propertyObservers){
                po.onResourceUpdate(properties);
        }
    }

    public void addPropertyObserver(PropertyObserver po){
        this.propertyObservers.add(po);
    }

    public void removePropertyObserver(PropertyObserver po){
        this.propertyObservers.remove(po);
    }


}
