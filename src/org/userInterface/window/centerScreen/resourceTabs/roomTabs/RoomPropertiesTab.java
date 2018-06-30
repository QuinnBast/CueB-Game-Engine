package org.userInterface.window.centerScreen.resourceTabs.roomTabs;

import net.miginfocom.swing.MigLayout;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.Resources.RoomResource;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;
import org.userInterface.window.centerScreen.resourceTabs.Tab;
import org.developmentEngine.resourceManager.Resources.Resource;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

/**
 * Created by Quinn on 5/11/2018.
 */
public class RoomPropertiesTab extends Tab {

    //Width and height adjustables
    private JSpinner widthSpinner = new JSpinner();
    private JSpinner heightSpinner = new JSpinner();

    private JColorChooser backgroundColorChooser = new JColorChooser();
    private JSpinner framerateSpinner = new JSpinner();
    private JTextField roomNameLabel = new JTextField(20);
    private JComboBox backgroundTypeSelector;

    private RoomResource referencedRoom ;
    private RoomProperties roomProperties;

    public RoomPropertiesTab(Resource r) {
        super(r);
        r.getProperties().addPropertyObserver(this);
        //Instantiate locals
        this.referencedRoom = (RoomResource) r;
        this.roomProperties = this.referencedRoom.getProperties();

        JPanel propertiesPanel = new JPanel(new MigLayout("fill", "[]"));
        propertiesPanel.add(new JLabel("Name"));

        roomNameLabel.setText(referencedRoom.getFilePath());
        roomNameLabel.getDocument().addDocumentListener(textListener);

        propertiesPanel.add(roomNameLabel, "wrap, growx");
        propertiesPanel.add(new JLabel("Framerate"));
        propertiesPanel.add(framerateSpinner, "wrap, growx");

        JPanel dimensionPanel = new JPanel(new MigLayout("fill", "[][]"));
        dimensionPanel.setBorder(BorderFactory.createTitledBorder("Size"));
        propertiesPanel.add(dimensionPanel, "wrap, span2, growx");

        SpinnerNumberModel widthModel = new SpinnerNumberModel((int)roomProperties.getSize().getWidth(), 0, 9999, 1);
        SpinnerNumberModel heightModel = new SpinnerNumberModel((int)roomProperties.getSize().getHeight(), 0, 9999, 1);
        widthSpinner.setModel(widthModel);
        heightSpinner.setModel(heightModel);

        widthSpinner.addChangeListener(changeListener);
        heightSpinner.addChangeListener(changeListener);

        SpinnerNumberModel framerateModel = new SpinnerNumberModel(roomProperties.getDesiredFramerate(), 0, 60, 1);
        framerateSpinner.setModel(framerateModel);

        dimensionPanel.add(new JLabel("Width:"), "");
        dimensionPanel.add(widthSpinner, "wrap, growx");
        dimensionPanel.add(new JLabel("Height"), "");
        dimensionPanel.add(heightSpinner, "wrap, growx");

        JPanel backgroundColorPanel = new JPanel(new MigLayout("fill", "[][]"));
        backgroundColorPanel.setBorder(BorderFactory.createTitledBorder("Background"));

        String[] backgroundTypes = {"Solid Color", "Gradient", "Image"};
        backgroundTypeSelector = new JComboBox(backgroundTypes);

        backgroundColorPanel.add(new JLabel("Background Type:"));
        backgroundColorPanel.add(backgroundTypeSelector, "wrap, growx");

        JPanel backgroundSelector = new JPanel();
        JLabel backgroundLabel = new JLabel("Background Color");
        backgroundSelector.add(backgroundLabel);
        backgroundSelector.add(backgroundColorChooser);
        backgroundColorChooser.getSelectionModel().addChangeListener(changeListener);
        backgroundColorPanel.add(backgroundSelector, "span2");

        propertiesPanel.add(backgroundColorPanel);

        this.add(propertiesPanel);
    }

    private void updateProperties(){
        referencedRoom.setFilePath(roomNameLabel.getText());
        roomProperties.setSize(new Rectangle2D.Double(0, 0, ((Integer)widthSpinner.getValue()).doubleValue(), ((Integer)heightSpinner.getValue()).doubleValue()));
        roomProperties.setBackgroundColor(backgroundColorChooser.getColor());
        roomProperties.setDesiredFramerate((int)framerateSpinner.getValue());
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

    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            updateProperties();
        }
    };
}
