package org.userInterface.menuBar;

import org.userInterface.menuBar.buildMenu.BuildMenu;
import org.userInterface.menuBar.editMenu.EditMenu;
import org.userInterface.menuBar.fileMenu.FileMenu;

import javax.swing.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class MenuBar extends JMenuBar {

    public MenuBar(){
        this.add(new FileMenu());
        this.add(new EditMenu());
        this.add(new BuildMenu());
    }
}
