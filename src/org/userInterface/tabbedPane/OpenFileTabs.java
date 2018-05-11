package org.userInterface.tabbedPane;

import org.applicationEngine.objects.ObjectType;
import org.userInterface.tabbedPane.resourceTabs.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Quinn on 5/11/2018.
 */
public class OpenFileTabs extends JPanel {

    JTabbedPane tabbedPane = new JTabbedPane();
    ArrayList<String> openTabs = new ArrayList<String>();

    public OpenFileTabs(){
        this.setLocation(200,0);
        this.setSize(new Dimension(1400, 800));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(tabbedPane);
    }

    public void addNewTab(String filename, ObjectType type){
        if(!isOpen(filename)){
            this.openTabs.add(filename);
            switch(type){
                case ROOM:
                    tabbedPane.addTab(filename, null, new RoomTabs());
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new OpenFileTabHeader(filename, tabbedPane.getTabCount()));
                    return;
                case OBJECT:
                    tabbedPane.addTab(filename, null, new ObjectTabs());
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new OpenFileTabHeader(filename, tabbedPane.getTabCount()));
                    return;
                case SCRIPT:
                    tabbedPane.addTab(filename, null, new ScriptTabs());
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new OpenFileTabHeader(filename, tabbedPane.getTabCount()));
                    return;
                case SPRITE:
                    tabbedPane.addTab(filename, null, new SpriteTabs());
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new OpenFileTabHeader(filename, tabbedPane.getTabCount()));
                    return;
                default:
                    return;
            }
        }
    }

    public boolean isOpen(String filename){
        for(String file : openTabs){
            if(file.equals(filename)){
                return true;
            }
        }
        return false;
    }

    public void removeTab(int tabIndex, String fileName){
        this.tabbedPane.remove(tabIndex);
        for(String file : openTabs){
            if(file.equals(fileName)){
                this.openTabs.remove(file);
                return;
            }
        }
    }

    public JTabbedPane getTabbedPane(){
        return this.tabbedPane;
    }

}
