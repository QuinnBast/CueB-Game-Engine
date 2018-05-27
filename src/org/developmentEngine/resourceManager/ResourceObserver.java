package org.developmentEngine.resourceManager;

import org.developmentEngine.resourceManager.Resources.Resource;

/**
 * Created by Quinn on 5/15/2018.
 */
public interface ResourceObserver {

    void onResourceAdd(Resource r);
    void onResourceRemove(Resource r);

}
