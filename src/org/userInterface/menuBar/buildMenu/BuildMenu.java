package org.userInterface.menuBar.buildMenu;

import org.userInterface.menuBar.buildMenu.Buttons.RunButton;

import javax.swing.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class BuildMenu extends JMenu {

    public BuildMenu(){
        this.setText("Build");
        this.add(new RunButton());
    }

}
