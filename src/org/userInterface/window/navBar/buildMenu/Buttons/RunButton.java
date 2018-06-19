package org.userInterface.window.navBar.buildMenu.Buttons;

import org.applicationEngine.game.Game;
import org.developmentEngine.gameManagement.InstantiationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class RunButton extends JMenuItem {

    public RunButton(){
        this.setText("Run");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstantiationManager im = new InstantiationManager();
                im.instantiate();
            }
        });
    }
}
