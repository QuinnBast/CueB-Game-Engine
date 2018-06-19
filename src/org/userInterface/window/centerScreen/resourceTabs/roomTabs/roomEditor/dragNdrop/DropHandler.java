package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.dragNdrop;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.Instance;
import org.developmentEngine.resourceManager.Resources.RoomResource;
import org.developmentEngine.resourceManager.resourceProperties.InstanceProperties;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.ObjectPanel;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.RoomEditorCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Quinn on 5/25/2018.
 */
public class DropHandler implements DropTargetListener, Serializable {
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        //Check if the component that is being dragged is supported.
        //If the component is not supported, reject the drag.
        if(dtde.isDataFlavorSupported(PanelDataFlavor.panelDataFlavor)){
            dtde.acceptDrag(DnDConstants.ACTION_MOVE);
        } else {
            dtde.rejectDrag();
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent dtde) {

        //This is where we put the dragged object on the canvas.
        boolean success = false;
        if(dtde.isDataFlavorSupported(PanelDataFlavor.panelDataFlavor)){
            //Get the transferable component
            Transferable transferable = dtde.getTransferable();
            try{
                Object data = transferable.getTransferData(PanelDataFlavor.panelDataFlavor);
                if(data instanceof ObjectPanel){
                    //The dragged object has been captured, draw it on the canvas in the dropped location.
                    ObjectPanel draggedPanel = (ObjectPanel) data;

                    //Get the drop location
                    DropTargetContext dtc = dtde.getDropTargetContext();
                    Component component = dtc.getComponent();

                    if(component instanceof RoomEditorCanvas){

                        RoomEditorCanvas rec = (RoomEditorCanvas) component;

                        //Get the current room camera's location and determine if the drop point is inside the room canvas.
                        Rectangle2D roomLocation = rec.getRoomLocation();
                        if(roomLocation.contains(dtde.getLocation().getX(), dtde.getLocation().getY())){
                            //Create an instance of the object inside the room.
                            //Set the instance spawn location to the coordinates of the mouse location relative to the canvas view.
                            Instance objectInstance = new Instance(draggedPanel.getReferencedObject());
                            InstanceProperties instanceProperties = (InstanceProperties)objectInstance.getProperties();

                            //Get the relative location of the camera offset when adding the object to the room.
                            RoomResource referencedRoom = rec.getReferencedRoom();
                            double widthScale = roomLocation.getWidth() / ((RoomProperties)referencedRoom.getProperties()).getSize().getWidth();
                            double heightScale = roomLocation.getHeight() / ((RoomProperties)referencedRoom.getProperties()).getSize().getHeight();

                            //The location of the object is the roomLocation offset subtracted from the scaled width.
                            instanceProperties.setRoomLocation(new Point2D.Double((dtde.getLocation().getX() - roomLocation.getX()) * widthScale, (dtde.getLocation().getY() - roomLocation.getY()) * heightScale));

                            //Add the instance to the room
                            DevelopmentEngine.resourceManager.addInstance(objectInstance, rec.getReferencedRoom());
                        }
                        success = true;
                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                    }
                } else {
                    success = false;
                }
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dtde.dropComplete(success);
    }
}
