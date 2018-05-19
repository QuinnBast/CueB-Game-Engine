package org.userInterface.window.navBar.editMenu;

import org.userInterface.window.navBar.editMenu.Buttons.NewObjectButton;
import org.userInterface.window.navBar.editMenu.Buttons.NewRoomButton;
import org.userInterface.window.navBar.editMenu.Buttons.NewScriptButton;
import org.userInterface.window.navBar.editMenu.Buttons.NewSpriteButton;

import javax.swing.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class EditMenu extends JMenu {

    public EditMenu(){
        this.setText("Edit");
        this.add(new NewSpriteButton());
        this.add(new NewScriptButton());
        this.add(new NewObjectButton());
        this.add(new NewRoomButton());
    }
}
