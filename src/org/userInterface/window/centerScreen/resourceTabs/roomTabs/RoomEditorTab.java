package org.userInterface.window.centerScreen.resourceTabs.roomTabs;

import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.ObjectPane;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.RoomEditorCanvas;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class RoomEditorTab extends JComponent {

    RoomEditorCanvas roomEditor = new RoomEditorCanvas();

    public RoomEditorTab(){
        this.setLayout(new BorderLayout());
        this.add(roomEditor, BorderLayout.CENTER);
        this.add(new ObjectPane(), BorderLayout.EAST);
    }

}
