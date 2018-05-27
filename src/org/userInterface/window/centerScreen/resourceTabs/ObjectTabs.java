package org.userInterface.window.centerScreen.resourceTabs;

import org.userInterface.window.centerScreen.resourceTabs.objectTabs.ObjectEventTab;
import org.userInterface.window.centerScreen.resourceTabs.objectTabs.ObjectPropertiesTab;
import org.developmentEngine.resourceManager.Resources.Resource;

import javax.swing.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class ObjectTabs extends JTabbedPane {

    public ObjectTabs(Resource r){
        this.addTab("Properties", null, new ObjectPropertiesTab(r));
        this.addTab("Events", null, new ObjectEventTab(r));
    }

}
