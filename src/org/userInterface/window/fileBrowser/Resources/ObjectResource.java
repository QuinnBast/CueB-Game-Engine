package org.userInterface.window.fileBrowser.Resources;


import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;

/**
 * Created by Quinn on 5/5/2018.
 */
public class ObjectResource extends Resource {

    public ObjectResource(String path) {
        super(path + ".obj");
        this.resourceProperties = new ObjectProperties();
    }
}
