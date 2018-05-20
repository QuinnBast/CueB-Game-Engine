package org.userInterface.window.fileBrowser.Resources;

import org.developmentEngine.resourceManager.resourceProperties.ScriptProperties;

/**
 * Created by Quinn on 5/5/2018.
 */
public class ScriptResource extends Resource {

    public ScriptResource(String path) {
        super(path + ".scr");
        this.resourceProperties = new ScriptProperties();
    }
}
