package org.userInterface.menuBar.editMenu.Buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class NewObjectButton extends JMenuItem {

    public NewObjectButton(){
        this.setText("New Object");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open new object dialogue
            }
        });
    }

}
