package org.userInterface.window.centerScreen.resourceTabs.objectTabs;

import net.miginfocom.swing.MigLayout;
import org.applicationEngine.objects.Base.Object;
import org.applicationEngine.objects.ObjectType;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;
import org.userInterface.window.centerScreen.resourceTabs.Tab;
import org.developmentEngine.resourceManager.Resources.Resource;
import org.developmentEngine.resourceManager.Resources.SpriteResource;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/11/2018.
 */
public class ObjectPropertiesTab extends Tab implements PropertyObserver, ResourceObserver {

    //Referenced Properties
    private ObjectProperties referencedProperties;
    private ObjectResource referencedObject;

    //Linked Sprite Objects
    private JComboBox spriteSelector;
    private JComboBox parentSelector;
    private JLabel imageLabel;

    //Boolean Selectors
    private JCheckBox isCollidableBox = new JCheckBox("Collidable?", true);
    private JCheckBox canMoveBox = new JCheckBox("Moveable?", true);
    private JCheckBox canRotatebox = new JCheckBox("Rotatable?", true);
    private JCheckBox canDisplaceBox = new JCheckBox("Displacable?", true);
    private JCheckBox isVisibleBox = new JCheckBox("Visible?", true);

    //Object name
    private JTextField objectNameText;

    public ObjectPropertiesTab(Resource r) {
        super(r);
        this.referencedObject = (ObjectResource) r;
        this.referencedProperties = (ObjectProperties)r.getProperties();
        objectNameText = new JTextField(referencedObject.getFilePath(), 20);
        referencedProperties.addPropertyObserver(this);
        DevelopmentEngine.resourceManager.addResourceObserver(this);
        objectNameText.getDocument().addDocumentListener(textListener);

        JPanel leftPane = new JPanel(new MigLayout("fill", "[]"));
        leftPane.add(new JLabel("Name:"), "");
        leftPane.add(objectNameText, "wrap, growx");
        leftPane.add(new JLabel("Sprite:"), "");

        spriteSelector = new JComboBox(DevelopmentEngine.resourceManager.getSpriteList().toArray());
        spriteSelector.insertItemAt("", 0); //Insert a null item if there is no linked sprite

        JPanel imagePanel = new JPanel(new BorderLayout());
        this.imageLabel = new JLabel();
        if(referencedProperties.getLinkedSprite() != null) {
            SpriteProperties sp = referencedProperties.getLinkedSprite().getProperties();
            spriteSelector.setSelectedItem(sp.getFilepaths().get(0));
            if (sp.getFilepaths().get(0) != "") {
                ImageIcon originalImage = sp.getImageIcon();

                ImageIcon imgIcon = new ImageIcon(originalImage.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH));
                this.imageLabel.setIcon(imgIcon);
            }
        } else {
            spriteSelector.setSelectedItem("");
        }
        spriteSelector.addActionListener(changeListener);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        leftPane.add(spriteSelector, "wrap, growx");
        leftPane.add(imagePanel, "span 2, growx, wrap");

        leftPane.add(new JLabel("Parent Object:"), "");

        parentSelector = new JComboBox(DevelopmentEngine.resourceManager.getObjectList().toArray());
        parentSelector.insertItemAt("", 0); //Insert a null item if there is no linked object

        leftPane.add(parentSelector, "wrap, growx");

        JPanel rightPane = new JPanel(new MigLayout("fill", "[]"));
        rightPane.setBorder(BorderFactory.createTitledBorder("Properties"));

        isCollidableBox.addActionListener(changeListener);
        canMoveBox.addActionListener(changeListener);
        canRotatebox.addActionListener(changeListener);
        canDisplaceBox.addActionListener(changeListener);
        isVisibleBox.addActionListener(changeListener);

        rightPane.add(isCollidableBox, "wrap");
        rightPane.add(canRotatebox, "wrap");
        rightPane.add(canDisplaceBox, "wrap");
        rightPane.add(canMoveBox, "wrap");
        rightPane.add(isVisibleBox, "wrap");

        this.add(leftPane);
        this.add(rightPane);
        this.updateProperties();
    }

    private void updateProperties(){
        referencedObject.setFilePath(objectNameText.getText());
        referencedProperties.setVisible(isVisibleBox.isSelected());
        referencedProperties.setCanDisplace(canDisplaceBox.isSelected());
        referencedProperties.setCanMove(canMoveBox.isSelected());
        referencedProperties.setCanRotate(canRotatebox.isSelected());
        referencedProperties.setCollidable(isCollidableBox.isSelected());

        if(spriteSelector.getSelectedItem() != "") {
            referencedProperties.setLinkedSprite((SpriteResource) spriteSelector.getSelectedItem());
        } else {
            referencedProperties.setLinkedSprite(null);
        }
    }

    ActionListener changeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateProperties();
        }
    };

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

    @Override
    public void onPropertyUpdate(ResourceProperties properties) {
        if(referencedProperties.getLinkedSprite() != null) {
            SpriteProperties sp = (SpriteProperties) (referencedProperties.getLinkedSprite().getProperties());
            if (sp.getFilepaths().get(0) != "") {
                ImageIcon originalImage = sp.getImageIcon();

                ImageIcon imgIcon = new ImageIcon(originalImage.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
                this.imageLabel.setIcon(imgIcon);
            }
        }
    }

    @Override
    public void onResourceAdd(Resource r) {
        if(r.getObjectType() == ObjectType.SPRITE){
            spriteSelector.insertItemAt(r, spriteSelector.getItemCount());
        }
    }

    @Override
    public void onResourceRemove(Resource r) {
        if(r.getObjectType() == ObjectType.SPRITE){
            spriteSelector.removeItem(r);
        }
    }

    @Override
    public void onResourceUpdate(Resource r) {
        //Dont care
    }
}
