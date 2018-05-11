package org.userInterface.fileBrowser.Resources;

import org.userInterface.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/11/2018.
 */
public class ContextMenu extends JPopupMenu {

    JMenuItem newSpriteItem = new JMenuItem("Sprite");
    JMenuItem newObjectItem = new JMenuItem("Object");
    JMenuItem newScriptItem = new JMenuItem("Script");
    JMenuItem newRoomItem = new JMenuItem("Room");

    public ContextMenu(){
        JMenu newMenu = new JMenu("New");
        this.add(newMenu);

        newSpriteItem.addActionListener(spriteListener);
        newObjectItem.addActionListener(objectListener);
        newScriptItem.addActionListener(scriptListener);
        newRoomItem.addActionListener(roomListener);

        newMenu.add(newSpriteItem);
        newMenu.add(newObjectItem);
        newMenu.add(newScriptItem);
        newMenu.add(newRoomItem);
    }

    ActionListener spriteListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserInterface.modalController.displayNewSpriteModal();
        }
    };

    ActionListener objectListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserInterface.modalController.displayNewObjecteModal();
        }
    };

    ActionListener scriptListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserInterface.modalController.displayNewScriptModal();
        }
    };

    ActionListener roomListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            UserInterface.modalController.displayNewRoomModal();
        }
    };
}
