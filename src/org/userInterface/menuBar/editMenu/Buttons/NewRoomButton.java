package org.userInterface.menuBar.editMenu.Buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class NewRoomButton extends JMenuItem {

    public NewRoomButton(){
        this.setText("New Room");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Create new room dialogue
            }
        });
    }

}
