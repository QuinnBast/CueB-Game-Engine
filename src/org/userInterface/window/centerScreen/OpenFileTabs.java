package org.userInterface.window.centerScreen;

import org.applicationEngine.objects.ObjectType;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.userInterface.window.fileBrowser.Resources.Resource;
import org.userInterface.window.centerScreen.resourceTabs.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Quinn on 5/11/2018.
 */
public class OpenFileTabs extends JPanel implements ResourceObserver {

    private HashMap<Resource, JComponent> openTabs = new HashMap<>();
    JTabbedPane tabbedPane = new JTabbedPane();
    //ArrayList<String> openTabs = new ArrayList<String>();

    public OpenFileTabs(){
        this.setLocation(200,0);
        this.setSize(new Dimension(1400, 800));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(tabbedPane);
        DevelopmentEngine.resourceManager.addResourceObserver(this);
    }

    public void addNewTab(Resource r){
        String filename = r.getFilePath();
        ObjectType type = r.getObjectType();
        if(!isOpen(r)){
            switch(type){
                case ROOM:
                    RoomTabs newRoom = new RoomTabs();
                    tabbedPane.addTab(filename, null, newRoom);
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new OpenFileTabHeader(r));
                    openTabs.put(r, newRoom);
                    return;
                case OBJECT:
                    ObjectTabs newObject = new ObjectTabs();
                    tabbedPane.addTab(filename, null, newObject);
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new OpenFileTabHeader(r));
                    openTabs.put(r, newObject);
                    return;
                case SCRIPT:
                    ScriptTabs newScript = new ScriptTabs();
                    tabbedPane.addTab(filename, null, newScript);
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new OpenFileTabHeader(r));
                    openTabs.put(r, newScript);
                    return;
                case SPRITE:
                    SpriteTabs newSprite = new SpriteTabs();
                    tabbedPane.addTab(filename, null, newSprite);
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new OpenFileTabHeader(r));
                    openTabs.put(r, newSprite);
                    return;
                default:
                    return;
            }
        }
    }

    public boolean isOpen(Resource r){
        if(openTabs.containsKey(r)){
            return true;
        }
        return false;
    }

    public void removeTab(Resource r){
        if(openTabs.containsKey(r)){
            JComponent removeMe = openTabs.get(r);
            this.tabbedPane.remove(removeMe);
            openTabs.remove(r);
        }
    }

    public JTabbedPane getTabbedPane(){
        return this.tabbedPane;
    }


    @Override
    public void onResourceAdd(Resource r) {
        this.addNewTab(r);
    }

    @Override
    public void onResourceRemove(Resource r) {
        this.removeTab(r);
    }

    @Override
    public void onResourceUpdate(Resource r) {

    }

    public Resource getActiveResource(){
        JComponent searchFor = (JComponent) tabbedPane.getSelectedComponent();
        Iterator it = openTabs.entrySet().iterator();
        while(it.hasNext()){
            HashMap.Entry pair = (HashMap.Entry)it.next();
            Resource key = (Resource) pair.getKey();
            JComponent tab = (JComponent) pair.getValue();
            if(tab == searchFor){
                return key;
            }
        }
        return null;
    }

    public JComponent getActiveTab(){
        return (JComponent)tabbedPane.getSelectedComponent();
    }
}
