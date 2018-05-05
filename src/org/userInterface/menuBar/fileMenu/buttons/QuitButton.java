package org.userInterface.menuBar.fileMenu.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class QuitButton extends JMenuItem {

    public QuitButton(){
        this.setText("Quit");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //On Click listener
            }
        });
    }

}
