package org.userInterface.window.centerScreen.resourceTabs;

import org.userInterface.window.centerScreen.resourceTabs.roomTabs.RoomCameraTab;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.RoomEditorTab;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.RoomPropertiesTab;

import javax.swing.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class RoomTabs extends JTabbedPane {

    public RoomTabs(){
        this.add("Properties", new RoomPropertiesTab());
        this.add("Cameras", new RoomCameraTab());
        this.add("Room Editor", new RoomEditorTab());
    }

}
