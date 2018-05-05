package org.userInterface.menuBar.editMenu.Buttons;

import org.applicationEngine.objects.ObjectType;
import org.userInterface.UserInterface;
import org.userInterface.fileBrowser.FileBrowserPanel;
import org.userInterface.fileBrowser.Resource;

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

                //Add new item to the FileBrowserPanel
                FileBrowserPanel panel = (FileBrowserPanel)(UserInterface.window.getLayers().getLayer("FileBrowser"));
                panel.addResource(new Resource("test.png", ObjectType.SPRITE));
                panel.revalidate();
                panel.repaint();
            }
        });
    }
}
