package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor;

import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.dragNdrop.DragPane;
import org.developmentEngine.resourceManager.Resources.Resource;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/26/2018.
 */
public class ObjectPanel extends DragPane implements PropertyObserver {

    private JLabel objectName;
    private ObjectResource referencedObject;

    public ObjectPanel(Resource r){
        super();
        this.referencedObject = (ObjectResource) r;
        this.objectName = new JLabel(r.getFilePath());
        ObjectProperties op = (ObjectProperties) r.getProperties();
        op.addPropertyObserver(this);
        if(op.getLinkedSprite() != null) {
            SpriteProperties sp = (SpriteProperties) op.getLinkedSprite().getProperties();
            ImageIcon originalImage = sp.getImageIcon();
            ImageIcon imgIcon = new ImageIcon(originalImage.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            objectName.setIcon(imgIcon);
        }
        this.add(objectName);
        this.setVisible(true);
    }

    public ObjectResource getReferencedObject(){
        return this.referencedObject;
    }

    @Override
    public void onResourceUpdate(ResourceProperties properties) {
        ObjectProperties op = (ObjectProperties) properties;
        if(op.getLinkedSprite() != null) {
            SpriteProperties sp = (SpriteProperties) op.getLinkedSprite().getProperties();
            ImageIcon originalImage = sp.getImageIcon();
            ImageIcon imgIcon = new ImageIcon(originalImage.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            this.objectName.setIcon(imgIcon);
        }
    }
}
