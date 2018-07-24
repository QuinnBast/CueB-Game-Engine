package org.developmentEngine.resourceManager.Resources;

import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;

import java.util.ArrayList;

/**
 * Created by Quinn on 5/5/2018.
 */
public class RoomResource extends Resource {

    public RoomResource(String path) {
        super(path + ".rm");
        this.resourceProperties = new RoomProperties();
    }

    public RoomResource(RoomResource roomCopy){
        super(roomCopy);
        this.resourceProperties = new RoomProperties(roomCopy.getProperties());
    }

    @Override
    public RoomProperties getProperties() {
        return (RoomProperties)this.resourceProperties;
    }
}
