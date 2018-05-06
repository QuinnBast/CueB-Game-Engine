package org.userInterface.menuBar.editMenu.Buttons;

import org.developmentEngine.resourceManager.resourceTypes.Room;
import org.developmentEngine.resourceManager.resourceTypes.Sprite;
import org.userInterface.UserInterface;
import org.userInterface.fileBrowser.FileBrowserPanel;
import org.userInterface.fileBrowser.Resources.RoomResource;
import org.userInterface.fileBrowser.Resources.SpriteResource;

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

                FileBrowserPanel panel = (FileBrowserPanel)(UserInterface.window.getLayers().getLayer("FileBrowser"));
                panel.addResource(new RoomResource("test.png", new Room()));
            }
        });
    }

}
