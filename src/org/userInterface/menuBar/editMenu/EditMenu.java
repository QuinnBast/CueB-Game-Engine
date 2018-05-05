package org.userInterface.menuBar.editMenu;

import org.userInterface.menuBar.editMenu.Buttons.NewObjectButton;
import org.userInterface.menuBar.editMenu.Buttons.NewRoomButton;
import org.userInterface.menuBar.editMenu.Buttons.NewScriptButton;
import org.userInterface.menuBar.editMenu.Buttons.NewSpriteButton;

import javax.swing.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class EditMenu extends JMenu {

    public EditMenu(){
        this.setText("Edit");
        this.add(new NewSpriteButton());
        this.add(new NewObjectButton());
        this.add(new NewRoomButton());
        this.add(new NewScriptButton());
    }
}
