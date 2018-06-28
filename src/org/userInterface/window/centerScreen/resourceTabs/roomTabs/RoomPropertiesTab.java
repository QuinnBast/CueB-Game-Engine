package org.userInterface.window.centerScreen.resourceTabs.roomTabs;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.Resources.RoomResource;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;
import org.userInterface.window.centerScreen.resourceTabs.Tab;
import org.developmentEngine.resourceManager.Resources.Resource;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class RoomPropertiesTab extends Tab {

    //Width and height adjustables
    private JSpinner widthSpinner = new JSpinner();
    private JSpinner heightSpinner = new JSpinner();

    private JColorChooser backgroundColorChooser = new JColorChooser();
    private JSpinner framerateSpinner = new JSpinner();
    private JTextField roomNameLabel = new JTextField();

    private RoomResource referencedRoom ;
    private RoomProperties roomProperties;

    public RoomPropertiesTab(Resource r) {
        super(r);
        r.getProperties().addPropertyObserver(this);
        //Instantiate locals
        this.referencedRoom = (RoomResource) r;
        this.roomProperties = this.referencedRoom.getProperties();

        JPanel roomNamePanel = new JPanel();
        roomNameLabel.setText(referencedRoom.getFilePath());
        roomNamePanel.add(roomNameLabel);
        roomNameLabel.getDocument().addDocumentListener(textListener);

        JPanel dimensionPanel = new JPanel();

        JPanel widthPanel = new JPanel();
        JPanel heightPanel = new JPanel();
        JPanel frameratePanel = new JPanel();

        SpinnerNumberModel widthModel = new SpinnerNumberModel((int)roomProperties.getSize().getWidth(), 0, null, 1);
        SpinnerNumberModel heightModel = new SpinnerNumberModel((int)roomProperties.getSize().getHeight(), 0, null, 1);
        widthSpinner.setModel(widthModel);
        heightSpinner.setModel(heightModel);

        SpinnerNumberModel framerateModel = new SpinnerNumberModel(roomProperties.getDesiredFramerate(), 0, 60, 1);
        framerateSpinner.setModel(framerateModel);

        dimensionPanel.add(widthSpinner);
        dimensionPanel.add(heightSpinner);

        JLabel widthText = new JLabel("Width");
        JLabel heightText = new JLabel("Height");
        JLabel frameRateText = new JLabel("Framerate");

        widthPanel.add(widthText);
        widthPanel.add(widthSpinner);

        heightPanel.add(heightText);
        heightPanel.add(heightSpinner);

        frameratePanel.add(framerateSpinner);
        frameratePanel.add(frameRateText);

        dimensionPanel.add(widthPanel);
        dimensionPanel.add(heightPanel);
        dimensionPanel.add(frameratePanel);

        JPanel backgroundColorPanel = new JPanel();
        JLabel backgroundLabel = new JLabel("Background Color");
        backgroundColorPanel.add(backgroundLabel);
        backgroundColorPanel.add(backgroundColorChooser);

        this.add(roomNamePanel);
        this.add(dimensionPanel);
        this.add(backgroundColorPanel);
    }

    private void updateProperties(){
        referencedRoom.setFilePath(roomNameLabel.getText());
    }

    @Override
    public void onPropertyUpdate(ResourceProperties properties) {

    }

    DocumentListener textListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateProperties();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateProperties();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateProperties();
        }
    };
}
