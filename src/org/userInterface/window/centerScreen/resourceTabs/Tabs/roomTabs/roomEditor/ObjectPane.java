package org.userInterface.window.centerScreen.resourceTabs.Tabs.roomTabs.roomEditor;

import org.applicationEngine.objects.ObjectType;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;
import org.userInterface.window.fileBrowser.Resources.ObjectResource;
import org.userInterface.window.fileBrowser.Resources.Resource;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by Quinn on 5/15/2018.
 */
public class ObjectPane extends JPanel implements ResourceObserver, PropertyObserver {

    private HashMap<ObjectResource, JPanel> objectList = new HashMap<>();

    public ObjectPane(){
        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(200,this.getPreferredSize().height));
        loadObjects();
        this.setVisible(true);

        //Concurrent modification error.
        //Trying to add an observer while the onNotify event is being fired.
        DevelopmentEngine.resourceManager.addResourceObserver(this);
    }

    private void loadObjects(){
        for(ObjectResource obj : DevelopmentEngine.resourceManager.getObjectList()){
            this.addResource(obj);
        }
    }

    private void addResource(ObjectResource r){
        JPanel resourcePanel = new JPanel();
        JLabel objectName = new JLabel(r.getFilePath());
        ObjectProperties op = (ObjectProperties) r.getProperties();
        op.addPropertyObserver(this);
        if(op.getLinkedSprite() != null) {
            SpriteProperties sp = (SpriteProperties) op.getLinkedSprite().getProperties();
            ImageIcon originalImage = sp.getImageIcon();
            ImageIcon imgIcon = new ImageIcon(originalImage.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            objectName.setIcon(imgIcon);
        }
        resourcePanel.add(objectName);
        resourcePanel.setVisible(true);
        this.objectList.put(r, resourcePanel);
        this.add(resourcePanel);
    }

    private void removeResource(ObjectResource r){
        if(this.objectList.containsKey(r)){
            this.remove(this.objectList.get(r));
            this.objectList.remove(r);
            ObjectProperties op = (ObjectProperties) r.getProperties();
            op.removePropertyObserver(this);
        }
    }

    @Override
    public void onResourceAdd(Resource r) {
        if(r.getObjectType() == ObjectType.OBJECT) {
            this.addResource((ObjectResource)r);
        }
    }

    @Override
    public void onResourceRemove(Resource r) {
        if(r.getObjectType() == ObjectType.OBJECT) {
            this.removeResource((ObjectResource)r);
        }
    }

    private JPanel getPanelFromProperty(ObjectProperties properties){
        return this.objectList.get(properties.getParentObject());
    }

    @Override
    public void onResourceUpdate(ResourceProperties properties) {
        ObjectProperties op = (ObjectProperties) properties;
        JPanel panel = this.getPanelFromProperty(op);
        if(op.getLinkedSprite() != null) {
            SpriteProperties sp = (SpriteProperties) op.getLinkedSprite().getProperties();
            ImageIcon originalImage = sp.getImageIcon();
            ImageIcon imgIcon = new ImageIcon(originalImage.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            JLabel jl = (JLabel)(panel.getComponent(0));
            jl.setIcon(imgIcon);
        }
    }
}
