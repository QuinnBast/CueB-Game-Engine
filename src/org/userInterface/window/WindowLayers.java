package org.userInterface.window;

import org.userInterface.fileBrowser.FileBrowserPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Quinn on 5/4/2018.
 */
public class WindowLayers extends JLayeredPane {

    private HashMap<String, JComponent> layers = new HashMap<>();

    public WindowLayers(){
        FileBrowserPanel fileBrowserPanel = new FileBrowserPanel();
        this.layers.put("FileBrowser", fileBrowserPanel);


        this.add(fileBrowserPanel);
    }

    public JComponent getLayer(String layerName){
        if(this.layers.containsKey(layerName)){
            return this.layers.get(layerName);
        } else {
            return null;
        }
    }

}
