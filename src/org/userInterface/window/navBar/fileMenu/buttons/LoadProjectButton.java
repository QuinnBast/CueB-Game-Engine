package org.userInterface.window.navBar.fileMenu.buttons;

import org.developmentEngine.DevelopmentEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Quinn on 7/14/2018.
 */
public class LoadProjectButton extends JMenuItem {

    public LoadProjectButton(){
        this.setText("Load Project");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DevelopmentEngine.projectManager.loadProject();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
