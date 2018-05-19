package org.userInterface.window.navBar.fileMenu.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class NewProjectButton extends JMenuItem {

    public NewProjectButton(){
        this.setText("New Project");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //On Click Action
            }
        });
    }
}
