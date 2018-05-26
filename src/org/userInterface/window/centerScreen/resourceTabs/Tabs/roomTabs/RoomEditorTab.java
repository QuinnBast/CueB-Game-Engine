package org.userInterface.window.centerScreen.resourceTabs.Tabs.roomTabs;

import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.Tab;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.roomTabs.roomEditor.ObjectPane;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.roomTabs.roomEditor.RoomEditorCanvas;
import org.userInterface.window.fileBrowser.Resources.Resource;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class RoomEditorTab extends Tab {

    RoomEditorCanvas roomEditor = new RoomEditorCanvas();

    public RoomEditorTab(Resource r){
        super(r);
        this.setLayout(new BorderLayout());
        this.add(roomEditor, BorderLayout.CENTER);
        this.add(new ObjectPane(), BorderLayout.EAST);
    }

    @Override
    public void onResourceUpdate(ResourceProperties properties) {

    }
}
