package org.developmentEngine.resourceManager.Resources;

import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;

import java.util.ArrayList;

/**
 * Created by Quinn on 5/5/2018.
 */
public class RoomResource extends Resource {

    private RoomProperties roomProperties = new RoomProperties();

    public RoomResource(String path) {
        super(path + ".rm");
        this.resourceProperties = new RoomProperties();
    }
}
