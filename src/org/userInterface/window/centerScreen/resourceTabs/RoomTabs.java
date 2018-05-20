package org.userInterface.window.centerScreen.resourceTabs;

import org.userInterface.window.centerScreen.resourceTabs.Tabs.roomTabs.RoomCameraTab;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.roomTabs.RoomEditorTab;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.roomTabs.RoomPropertiesTab;
import org.userInterface.window.fileBrowser.Resources.Resource;

import javax.swing.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class RoomTabs extends JTabbedPane {

    public RoomTabs(Resource r){
        this.add("Properties", new RoomPropertiesTab(r));
        this.add("Cameras", new RoomCameraTab(r));
        this.add("Room Editor", new RoomEditorTab(r));
    }

}
