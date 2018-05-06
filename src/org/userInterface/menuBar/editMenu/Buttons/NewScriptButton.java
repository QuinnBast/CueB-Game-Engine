package org.userInterface.menuBar.editMenu.Buttons;

import org.developmentEngine.resourceManager.resourceTypes.Script;
import org.developmentEngine.resourceManager.resourceTypes.Sprite;
import org.userInterface.UserInterface;
import org.userInterface.fileBrowser.FileBrowserPanel;
import org.userInterface.fileBrowser.Resources.ScriptResource;
import org.userInterface.fileBrowser.Resources.SpriteResource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class NewScriptButton extends JMenuItem {

    public NewScriptButton(){
        this.setText("New Script");

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open new script dialogue
                UserInterface.modalController.displayNewScriptModal();
            }
        });
    }
}
