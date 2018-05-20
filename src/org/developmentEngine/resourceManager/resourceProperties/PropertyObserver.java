package org.developmentEngine.resourceManager.resourceProperties;

import org.userInterface.window.fileBrowser.Resources.Resource;

import java.io.IOException;

/**
 * Created by Quinn on 5/20/2018.
 */
public interface PropertyObserver {

    void onResourceUpdate() throws IOException;

}
