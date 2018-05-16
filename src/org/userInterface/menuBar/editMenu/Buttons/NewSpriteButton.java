package org.userInterface.menuBar.editMenu.Buttons;

import org.userInterface.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class NewSpriteButton extends JMenuItem {

    public NewSpriteButton(){
        this.setText("New Sprite");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open new sprite Dialogue
                UserInterface.modalController.displayNewSpriteModal();
            }
        });
    }
}
