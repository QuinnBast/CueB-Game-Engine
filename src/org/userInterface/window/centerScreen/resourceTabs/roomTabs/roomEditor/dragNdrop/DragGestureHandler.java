package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.dragNdrop;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.Serializable;

/**
 * Created by Quinn on 5/25/2018.
 */
public class DragGestureHandler implements DragGestureListener, DragSourceListener, Serializable {

    private JPanel draggedComponent;

    public DragGestureHandler(JPanel component){
        this.draggedComponent = component;
    }

    public JPanel getComponent(){
        return this.draggedComponent;
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {

        //When a drag is recognized, create a transferrable object that referneces the dragged object.
        Transferable transferable = new TransferableComponent(getComponent());

        //Start the drag process
        DragSource ds = dge.getDragSource();
        ds.startDrag(dge, Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR), transferable, this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {

    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {

    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {

    }

    @Override
    public void dragExit(DragSourceEvent dse) {

    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
    }
}
