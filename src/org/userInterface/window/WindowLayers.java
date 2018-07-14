package org.userInterface.window;

import net.miginfocom.swing.MigLayout;
import org.userInterface.window.centerScreen.OpenFileTabs;
import org.userInterface.window.fileBrowser.FileBrowserPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class WindowLayers extends JPanel {

    private FileBrowserPanel fileBrowserPanel;
    private OpenFileTabs openFileTabs;

    public WindowLayers(){
        this.setLayout(new BorderLayout());
        JPanel defaultPanel = new JPanel(new MigLayout("fill","[][]"));
        defaultPanel.setVisible(true);

        this.fileBrowserPanel = new FileBrowserPanel();
        this.openFileTabs = new OpenFileTabs();

        defaultPanel.add(fileBrowserPanel, "left, growy, growx");
        defaultPanel.add(openFileTabs, "left, growx, growy, pushx");

        this.add(defaultPanel, BorderLayout.CENTER);
    }

    public FileBrowserPanel getFileBrowser(){
        return this.fileBrowserPanel;
    }

    public OpenFileTabs getOpenFileTabs(){
        return this.openFileTabs;
    }
}
