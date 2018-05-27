package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.dragNdrop;

import javax.swing.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;

/**
 * Created by Quinn on 5/25/2018.
 */
public abstract class DragPane extends JPanel {

    private DragGestureHandler dragGestureHandler;
    private DragGestureRecognizer dragGestureRecognizer;

    //When the component is being dragged, it should be notified of events.
    @Override
    public void addNotify(){
        super.addNotify();
        if(dragGestureRecognizer == null){
            dragGestureHandler = new DragGestureHandler(this);
            dragGestureRecognizer = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, dragGestureHandler);
        }
    }

    //When a drag is ended on the component, the component should no longer be notifed of drag events.
    @Override
    public void removeNotify(){
        if(dragGestureRecognizer != null){
            dragGestureRecognizer.removeDragGestureListener(dragGestureHandler);
            dragGestureHandler = null;
        }
        dragGestureRecognizer = null;
        super.removeNotify();
    }

}
