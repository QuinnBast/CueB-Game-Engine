package org.userInterface.window.centerScreen.resourceTabs.Tabs.objectTabs;

import org.applicationEngine.objects.ObjectType;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;
import org.userInterface.UserInterface;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.Tab;
import org.userInterface.window.fileBrowser.Resources.Resource;
import org.userInterface.window.fileBrowser.Resources.SpriteResource;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Quinn on 5/11/2018.
 */
public class ObjectPropertiesTab extends Tab implements PropertyObserver, ResourceObserver {

    private ObjectProperties referencedProperties;
    private JComboBox spriteSelector;
    private JLabel imageLabel;

    public ObjectPropertiesTab(Resource r) {
        super(r);
        this.referencedProperties = (ObjectProperties)r.getProperties();
        referencedProperties.addPropertyObserver(this);
        DevelopmentEngine.resourceManager.addResourceObserver(this);

        JPanel relationshipPanel = new JPanel();

        JPanel referencedSpritePanel = new JPanel();
        this.imageLabel = new JLabel();
        referencedSpritePanel.add(imageLabel);
        if(referencedProperties.getLinkedSprite() != null) {
            SpriteProperties sp = (SpriteProperties) (referencedProperties.getLinkedSprite().getProperties());
            if (sp.getFilepaths().get(0) != "") {
                ImageIcon originalImage = sp.getImageIcon();

                ImageIcon imgIcon = new ImageIcon(originalImage.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH));
                this.imageLabel.setIcon(imgIcon);
            }
        }


        JLabel relationText = new JLabel("Sprite");
        spriteSelector = new JComboBox(DevelopmentEngine.resourceManager.getSpriteList().toArray());
        spriteSelector.insertItemAt("", 0);
        spriteSelector.addActionListener(comboActionListener);


        relationshipPanel.add(relationText);
        relationshipPanel.add(spriteSelector);

        this.add(referencedSpritePanel);
        this.add(relationshipPanel);
    }

    ActionListener comboActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(spriteSelector.getSelectedItem() != "") {
                referencedProperties.setLinkedSprite((SpriteResource) spriteSelector.getSelectedItem());
            } else {
                referencedProperties.setLinkedSprite(null);
            }
        }
    };

    @Override
    public void onResourceUpdate(ResourceProperties properties) {
        System.out.println("Object Updated");
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
}
