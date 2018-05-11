package org.userInterface.tabbedPane.resourceTabs;

import org.userInterface.tabbedPane.resourceTabs.objectTabs.ObjectEventTab;
import org.userInterface.tabbedPane.resourceTabs.objectTabs.ObjectPropertiesTab;

import javax.swing.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class ObjectTabs extends JTabbedPane {

    public ObjectTabs(){
        this.addTab("Properties", null, new ObjectPropertiesTab());
        this.addTab("Events", null, new ObjectEventTab());
    }

}
