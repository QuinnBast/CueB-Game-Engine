package org.userInterface.menuBar.fileMenu.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class SaveProjectButton extends JMenuItem {

    public SaveProjectButton(){
        this.setText("Save Project");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //On Click Listener
            }
        });
    }
}
