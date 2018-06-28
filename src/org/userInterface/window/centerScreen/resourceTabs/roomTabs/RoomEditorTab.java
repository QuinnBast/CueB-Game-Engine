package org.userInterface.window.centerScreen.resourceTabs.roomTabs;

import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;
import org.userInterface.window.centerScreen.resourceTabs.Tab;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.ObjectPane;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.RoomEditorCanvas;
import org.developmentEngine.resourceManager.Resources.Resource;
import org.developmentEngine.resourceManager.Resources.RoomResource;

import java.awt.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class RoomEditorTab extends Tab {

    RoomEditorCanvas roomEditor;
    private RoomProperties roomProperties;

    public RoomEditorTab(Resource r){
        super(r);
        this.roomEditor = new RoomEditorCanvas((RoomResource)r);
        this.setLayout(new BorderLayout());
        this.add(roomEditor, BorderLayout.CENTER);
        this.add(new ObjectPane(), BorderLayout.EAST);
    }

    @Override
    public void onPropertyUpdate(ResourceProperties properties) {

    }
}
