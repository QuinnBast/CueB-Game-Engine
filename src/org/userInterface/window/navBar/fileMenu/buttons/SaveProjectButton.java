package org.userInterface.window.navBar.fileMenu.buttons;

import org.developmentEngine.DevelopmentEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Quinn on 5/4/2018.
 */
public class SaveProjectButton extends JMenuItem {

    public SaveProjectButton(){
        this.setText("Save Project");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DevelopmentEngine.projectManager.saveProject();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
