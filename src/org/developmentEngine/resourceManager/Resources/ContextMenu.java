package org.developmentEngine.resourceManager.Resources;

import org.developmentEngine.DevelopmentEngine;
import org.userInterface.UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/11/2018.
 */
public class ContextMenu extends JPopupMenu {

    Resource selectedResource;

    JMenuItem newSpriteItem = new JMenuItem("Sprite");
    JMenuItem newObjectItem = new JMenuItem("Object");
    JMenuItem newScriptItem = new JMenuItem("Script");
    JMenuItem newRoomItem = new JMenuItem("Room");

    public ContextMenu(Resource selectedItem){

        this.selectedResource = selectedItem;
        JMenu newMenu = new JMenu("New");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        this.add(newMenu);
        this.add(deleteMenu);

        newSpriteItem.addActionListener(spriteListener);
        newObjectItem.addActionListener(objectListener);
        newScriptItem.addActionListener(scriptListener);
        newRoomItem.addActionListener(roomListener);
        deleteMenu.addActionListener(deleteListener);

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

    ActionListener deleteListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(selectedResource != null){
                DevelopmentEngine.resourceManager.removeResource(selectedResource);
            }
        }
    };
}
