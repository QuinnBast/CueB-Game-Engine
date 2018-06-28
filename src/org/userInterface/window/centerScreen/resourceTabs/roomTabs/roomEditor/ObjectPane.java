package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor;

import org.applicationEngine.objects.ObjectType;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.Resources.Resource;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by Quinn on 5/15/2018.
 */
public class ObjectPane extends JPanel implements ResourceObserver {

    private HashMap<ObjectResource, JPanel> objectList = new HashMap<>();

    public ObjectPane(){
        this.setBackground(Color.GRAY);
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
        ObjectPanel objectPanel = new ObjectPanel(r);
        ObjectProperties op = (ObjectProperties) r.getProperties();
        this.objectList.put(r, objectPanel);
        this.add(objectPanel);
    }

    private void removeResource(ObjectResource r){
        if(this.objectList.containsKey(r)){
            this.remove(this.objectList.get(r));
            this.objectList.remove(r);
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

    @Override
    public void onResourceUpdate(Resource r) {
        //dont care
    }

    private JPanel getPanelFromProperty(ObjectProperties properties){
        return this.objectList.get(properties.getParentObject());
    }
}
