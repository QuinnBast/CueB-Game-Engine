package org.userInterface.window.centerScreen.resourceTabs;

import org.userInterface.window.centerScreen.resourceTabs.Tabs.objectTabs.ObjectEventTab;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.objectTabs.ObjectPropertiesTab;
import org.userInterface.window.fileBrowser.Resources.Resource;

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
