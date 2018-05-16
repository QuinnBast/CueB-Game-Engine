package org.userInterface.window;

import org.userInterface.tabbedPane.OpenFileTabs;
import org.userInterface.fileBrowser.FileBrowserPanel;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by Quinn on 5/4/2018.
 */
public class WindowLayers extends JLayeredPane {

    private FileBrowserPanel fileBrowserPanel;
    private OpenFileTabs openFileTabs;

    public WindowLayers(){
        this.fileBrowserPanel = new FileBrowserPanel();
        this.openFileTabs = new OpenFileTabs();

        this.add(fileBrowserPanel, DEFAULT_LAYER);
        this.add(openFileTabs, DEFAULT_LAYER);
    }

    public FileBrowserPanel getFileBrowser(){
        return this.fileBrowserPanel;
    }

    public OpenFileTabs getOpenFileTabs(){
        return this.openFileTabs;
    }
}
