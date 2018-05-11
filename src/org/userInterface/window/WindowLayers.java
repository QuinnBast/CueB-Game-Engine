package org.userInterface.window;

import org.userInterface.tabbedPane.OpenFileTabs;
import org.userInterface.fileBrowser.FileBrowserPanel;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by Quinn on 5/4/2018.
 */
public class WindowLayers extends JLayeredPane {

    private HashMap<String, JComponent> layers = new HashMap<>();

    public WindowLayers(){
        FileBrowserPanel fileBrowserPanel = new FileBrowserPanel();
        OpenFileTabs fileTabs = new OpenFileTabs();
        this.layers.put("FileBrowser", fileBrowserPanel);
        this.layers.put("OpenFileTabs", fileTabs);

        this.add(fileBrowserPanel, DEFAULT_LAYER);
        this.add(fileTabs, DEFAULT_LAYER);
    }

    public JComponent getLayer(String layerName){
        if(this.layers.containsKey(layerName)){
            return this.layers.get(layerName);
        } else {
            return null;
        }
    }

}
