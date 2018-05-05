package org.userinterface.menuBar.fileMenu;

import org.userinterface.menuBar.fileMenu.buttons.NewProjectButton;
import org.userinterface.menuBar.fileMenu.buttons.QuitButton;
import org.userinterface.menuBar.fileMenu.buttons.SaveProjectButton;

import javax.swing.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class FileMenu extends JMenu {
    public FileMenu(){
        this.setText("File");
        this.add(new NewProjectButton());
        this.add(new SaveProjectButton());
        this.add(new QuitButton());
    }
}
