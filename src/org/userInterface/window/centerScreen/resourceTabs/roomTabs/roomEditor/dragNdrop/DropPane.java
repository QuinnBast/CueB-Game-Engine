package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.dragNdrop;

import javax.swing.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

/**
 * Created by Quinn on 5/26/2018.
 */
public abstract class DropPane extends JPanel {

    private DropTarget dropTarget;
    private DropHandler dropHandler;

    @Override
    public void addNotify(){
        super.addNotify();
        dropHandler = new DropHandler();
        dropTarget = new DropTarget(this, DnDConstants.ACTION_MOVE, dropHandler, true);
    }

    @Override
    public void removeNotify(){
        super.removeNotify();
        dropTarget.removeDropTargetListener(dropHandler);
    }

}
