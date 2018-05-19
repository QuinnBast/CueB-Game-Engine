package org.userInterface.window.navBar;

import org.userInterface.window.navBar.buildMenu.BuildMenu;
import org.userInterface.window.navBar.editMenu.EditMenu;
import org.userInterface.window.navBar.fileMenu.FileMenu;

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
