package org.userInterface.window.centerScreen.resourceTabs.roomTabs;

import net.miginfocom.swing.MigLayout;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;
import org.userInterface.window.centerScreen.resourceTabs.Tab;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.ObjectPane;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.RoomEditorCanvas;
import org.developmentEngine.resourceManager.Resources.Resource;
import org.developmentEngine.resourceManager.Resources.RoomResource;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class RoomEditorTab extends Tab {

    RoomEditorCanvas roomEditor;
    private RoomProperties roomProperties;
    private JSpinner gridWidth = new JSpinner();
    private JSpinner gridHeight = new JSpinner();

    public RoomEditorTab(Resource r){
        super(r);
        this.setLayout(new BorderLayout());
        this.roomProperties = ((RoomResource)r).getProperties();
        this.roomEditor = new RoomEditorCanvas((RoomResource)r);
        roomEditor.setMinimumSize(new Dimension(700,500));

        JPanel roomEditorSettingsPanel = new JPanel();
        roomEditorSettingsPanel.setMinimumSize(new Dimension(700, 100));
        JCheckBox showGridBox = new JCheckBox("Show Gridlines");
        gridWidth.setModel(new SpinnerNumberModel(0,0,(int)roomProperties.getSize().getWidth(), 1));
        gridHeight.setModel(new SpinnerNumberModel(0,0,(int)roomProperties.getSize().getHeight(), 1));
        JCheckBox snapToGrid = new JCheckBox("Snap to Grid");

        roomEditorSettingsPanel.add(showGridBox);
        roomEditorSettingsPanel.add(new JLabel("Grid Width:"));
        roomEditorSettingsPanel.add(gridWidth);
        roomEditorSettingsPanel.add(new JLabel("Grid Height:"));
        roomEditorSettingsPanel.add(gridHeight);
        roomEditorSettingsPanel.add(snapToGrid);

        this.add(roomEditor, BorderLayout.CENTER);
        this.add(new ObjectPane(), BorderLayout.WEST);
        this.add(roomEditorSettingsPanel, BorderLayout.SOUTH);
    }

    @Override
    public void onPropertyUpdate(ResourceProperties properties) {

    }
}
