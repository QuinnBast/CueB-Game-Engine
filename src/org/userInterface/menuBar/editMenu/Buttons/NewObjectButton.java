package org.userInterface.menuBar.editMenu.Buttons;

import org.developmentEngine.resourceManager.resourceTypes.Object;
import org.developmentEngine.resourceManager.resourceTypes.Sprite;
import org.userInterface.UserInterface;
import org.userInterface.fileBrowser.FileBrowserPanel;
import org.userInterface.fileBrowser.Resources.ObjectResource;
import org.userInterface.fileBrowser.Resources.SpriteResource;

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

                FileBrowserPanel panel = (FileBrowserPanel)(UserInterface.window.getLayers().getLayer("FileBrowser"));
                panel.addResource(new ObjectResource("test.png", new Object()));
            }
        });
    }

}
