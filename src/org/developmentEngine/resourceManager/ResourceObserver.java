package org.developmentEngine.resourceManager;

import org.userInterface.window.fileBrowser.Resources.Resource;

/**
 * Created by Quinn on 5/15/2018.
 */
public interface ResourceObserver {

    void onResourceAdd(Resource r);
    void onResourceRemove(Resource r);

}
