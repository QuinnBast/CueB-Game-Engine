package org.userInterface.tabbedPane.resourceTabs.roomTabs;

import org.userInterface.tabbedPane.resourceTabs.roomTabs.roomEditor.RoomEditorCanvas;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class RoomLayoutTab extends JComponent {

    RoomEditorCanvas roomEditor = new RoomEditorCanvas();

    public RoomLayoutTab(){
        this.setLayout(new BorderLayout());
        this.add(roomEditor, BorderLayout.CENTER);
    }

}
