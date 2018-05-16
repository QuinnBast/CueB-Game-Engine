package org.userInterface.tabbedPane.resourceTabs;

import org.userInterface.tabbedPane.resourceTabs.roomTabs.RoomCameraTab;
import org.userInterface.tabbedPane.resourceTabs.roomTabs.RoomEditorTab;
import org.userInterface.tabbedPane.resourceTabs.roomTabs.RoomPropertiesTab;

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
