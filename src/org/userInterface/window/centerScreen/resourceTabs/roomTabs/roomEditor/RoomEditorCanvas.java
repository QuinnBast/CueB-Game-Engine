package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor;

import org.applicationEngine.graphics.*;
import org.applicationEngine.graphics.Cameras.StaticCamera;
import org.developmentEngine.resourceManager.Resources.ContextMenu;
import org.developmentEngine.resourceManager.Resources.Instance;
import org.developmentEngine.resourceManager.resourceProperties.*;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.dragNdrop.DropPane;
import org.developmentEngine.resourceManager.Resources.RoomResource;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.abs;

/**
 * Created by Quinn on 5/14/2018.
 */
public class RoomEditorCanvas extends DropPane implements PropertyObserver {

    RoomResource referencedRoom;
    private Rectangle2D originalRoomDimension;
    protected Rectangle2D roomLocation = new Rectangle2D.Double(); //The area on the map that is visible
    private double widthRatio;
    private double heightRatio;
    private Point2D mouseTouch;


    public RoomEditorCanvas(RoomResource rr){
        this.referencedRoom = rr;
        originalRoomDimension = this.referencedRoom.getProperties().getSize();
        this.setVisible(true);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionAdapter);
        this.addMouseWheelListener(mouseWheelListener);
        this.roomLocation = new Rectangle2D.Double(0, 0, referencedRoom.getProperties().getSize().getWidth(), referencedRoom.getProperties().getSize().getHeight());
        this.widthRatio = this.getWidth() / this.roomLocation.getWidth();
        this.heightRatio = this.getHeight() / this.roomLocation.getHeight();
        rr.getProperties().addPropertyObserver(this);
        this.setBackground(referencedRoom.getProperties().getBackgroundColor());
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Draw a rectangle around the room border.
        g2d.setColor(Color.RED);
        g2d.draw(roomLocation);

        ArrayList<Instance> instanceInRoom = referencedRoom.getProperties().getInstances();
        for(Instance i : instanceInRoom) {
            //Check if the object is visible
            if (!i.getProperties().getObjectType().getProperties().isVisible()){
                //If thje object is not visible, skip drawing.
                continue;
            }

            //Check if the object has a linked sprite.
            if (i.getProperties().getObjectType().getProperties().getLinkedSprite() == null) {
                //If the object does not have a linked sprite, skip drawing.
                continue;
            }

            //Get the sprite properties of the object. (If applicable.)
            SpriteProperties spriteProperties = (i.getProperties()).getObjectType().getProperties().getLinkedSprite().getProperties();

            //Determine the scale of the width of the room
            double widthScale = this.roomLocation.getWidth() / referencedRoom.getProperties().getSize().getWidth();
            double heightScale = this.roomLocation.getHeight() / referencedRoom.getProperties().getSize().getHeight();

            //Offset the buffered image so that the image is drawn with the origin in the correct location
            //Get the location of the sprite to draw.
            double xOrigin = spriteProperties.getOrigin().getX();
            double yOrigin = spriteProperties.getOrigin().getY();
            double drawXOffset = i.getProperties().getRoomLocation().getX() - xOrigin;   //Offset the center of the sprite to draw the origin properly
            double drawYOffset = i.getProperties().getRoomLocation().getY() - yOrigin;   //Offset the center of the sprite to draw the origin properly

            //Determine if the sprite is within the canvas, if not, we shouldn't waste resources drawing it.
            //Create a bounding box of the sprite and its width and height
            Rectangle2D image = new Rectangle2D.Double((int) (drawXOffset * widthScale + roomLocation.getX()), (int) (drawYOffset * heightScale + roomLocation.getY()), (int) (spriteProperties.getSize().getWidth() * widthScale), (int) (spriteProperties.getSize().getHeight() * heightScale));
            Rectangle2D canvas = new Rectangle2D.Double(0,0,this.getWidth(), this.getHeight());
            if(canvas.intersects(image)){

                //Determine if the linked sprite has an image
                if (spriteProperties.getFilepaths() != null) {
                    //Create a buffered image from the sprite to draw on the canvas.
                    try {
                        //Get the buffered image.
                        BufferedImage rawImage = ImageIO.read(new File(spriteProperties.getFilepaths().get(0)));
                        //Draw the buffered image at the room location.

                        g.drawImage(rawImage, (int) (drawXOffset * widthScale + roomLocation.getX()), (int) (drawYOffset * heightScale + roomLocation.getY()), (int) (spriteProperties.getSize().getWidth() * widthScale), (int) (spriteProperties.getSize().getHeight() * heightScale), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //The object has no linked sprite. Should draw a placeholder sprite? Not sure of object size without sprite though.
                    //Could draw a red exclamation mark warning the person that no sprite is linked.
                }
            }
        }


        g2d.dispose();
    }

    public RoomResource getReferencedRoom(){
        return this.referencedRoom;
    }

    public RoomProperties getRoomProperties(){
        return this.referencedRoom.getProperties();
    }

    public void translateCamera(Point2D mouseLocation){
        Point2D mouseMoved = new Point2D.Double(mouseLocation.getX() - mouseTouch.getX(), mouseLocation.getY() - mouseTouch.getY());
        //Set the room location to the new offset.
        roomLocation.setRect(roomLocation.getX() + mouseMoved.getX(), roomLocation.getY() + mouseMoved.getY(), roomLocation.getWidth(), roomLocation.getHeight());
    }

    public Rectangle2D getRoomLocation(){
        return this.roomLocation;
    }


    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {

            RoomEditorCanvas canvas = (RoomEditorCanvas) e.getSource();
            ArrayList<Instance> clickedPoints = new ArrayList<>();

            //If the press is a right click, the user is trying to delete an object
            if(e.getButton() == e.BUTTON3){
                //Check if the click location is inside of any of the object bounding boxes.
                ArrayList<Instance> instanceInRoom = referencedRoom.getProperties().getInstances();
                for(Instance i : instanceInRoom) {
                    //Check if the object has a linked sprite.
                    if (i.getProperties().getObjectType().getProperties().getLinkedSprite() != null) {
                        //Get the sprite properties of the object. (If applicable.)
                        SpriteProperties spriteProperties = ((SpriteProperties) ((ObjectProperties) ((InstanceProperties) i.getProperties()).getObjectType().getProperties()).getLinkedSprite().getProperties());
                        //Get the scaled bounding box
                        //Get the location of the sprite to draw.
                        double xOrigin = spriteProperties.getOrigin().getX();
                        double yOrigin = spriteProperties.getOrigin().getY();
                        double drawXOffset = i.getProperties().getRoomLocation().getX() - xOrigin;   //Offset the center of the sprite to draw the origin properly
                        double drawYOffset = i.getProperties().getRoomLocation().getY() - yOrigin;   //Offset the center of the sprite to draw the origin properly

                        //Determine the scale of the width of the room
                        double widthScale = roomLocation.getWidth() / referencedRoom.getProperties().getSize().getWidth();
                        double heightScale = roomLocation.getHeight() / referencedRoom.getProperties().getSize().getHeight();

                        Rectangle2D boundingBox = new Rectangle2D.Double((int) (drawXOffset * widthScale + roomLocation.getX()), (int) (drawYOffset * heightScale + roomLocation.getY()), (int) (spriteProperties.getSize().getWidth() * widthScale), (int) (spriteProperties.getSize().getHeight() * heightScale));

                        if(boundingBox.contains(e.getX(), e.getY())){
                            clickedPoints.add(i);
                        }
                    }
                }
                if(clickedPoints.size() == 1){
                    referencedRoom.getProperties().removeInstance(clickedPoints.get(0));
                } else {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenu deleteMenu = new JMenu("Delete");
                    JMenu selectMenu = new JMenu("Select");
                    JMenu propertiesMenu = new JMenu("Properties");
                    JLabel delTopText = new JLabel("Top");
                    JLabel delBottomText = new JLabel("Bottom");
                    JLabel selTopText = new JLabel("Top");
                    JLabel selBottomText = new JLabel("Bottom");
                    JLabel propTopText = new JLabel("Top");
                    JLabel propBottomText = new JLabel("Bottom");

                    deleteMenu.add(delBottomText);
                    selectMenu.add(selBottomText);
                    propertiesMenu.add(propBottomText);

                    for(Instance inst : clickedPoints){
                        JMenuItem deletePointMenu = new JMenuItem(inst.getFilePath());
                        deletePointMenu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                referencedRoom.getProperties().removeInstance(inst);
                            }
                        });
                        JMenuItem selectPointMenu = new JMenuItem(inst.getFilePath());
                        JMenuItem propertiesPointMenu = new JMenuItem(inst.getFilePath());
                        deleteMenu.add(deletePointMenu);
                        selectMenu.add(selectPointMenu);
                        propertiesMenu.add(propertiesPointMenu);
                    }

                    deleteMenu.add(delTopText);
                    selectMenu.add(selTopText);
                    propertiesMenu.add(propTopText);

                    popupMenu.add(deleteMenu);
                    popupMenu.add(selectMenu);
                    popupMenu.add(propertiesMenu);
                    canvas.add(popupMenu);
                    popupMenu.show(canvas, e.getX(), e.getY());
                }
            }
            //If the user left clicks the canvas, we want the camera of the canvas to move while the mouse drags.
            if(e.getButton() == e.BUTTON1){
                //Set the room location to move cooresponding to how much the mouse has moved from it's initial drag location
                mouseTouch = new Point2D.Double(e.getX(), e.getY());
            }
            canvas.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getButton() == e.BUTTON1){
                mouseTouch = null;
            }

            RoomEditorCanvas canvas = (RoomEditorCanvas) e.getSource();
            canvas.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    MouseMotionAdapter motionAdapter = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e)) {
                super.mouseDragged(e);
                translateCamera(new Point2D.Double(e.getX(), e.getY()));
                mouseTouch = new Point2D.Double(e.getX(), e.getY());
                RoomEditorCanvas canvas = (RoomEditorCanvas) e.getSource();
                canvas.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e)) {

                super.mouseMoved(e);
                translateCamera(new Point2D.Double(e.getX(), e.getY()));
                mouseTouch = new Point2D.Double(e.getX(), e.getY());
                RoomEditorCanvas canvas = (RoomEditorCanvas) e.getSource();
                canvas.repaint();
            }
        }
    };

    @Override
    public void onPropertyUpdate(ResourceProperties properties) {
        this.setBackground(referencedRoom.getProperties().getBackgroundColor());

        //If the room dimensions change, update the room that is being drawn to be the size of the new dimensions
        if (this.originalRoomDimension != referencedRoom.getProperties().getSize()) {
            //However, preserve the current zoom level of the map
            //Obtain current zoom level:
            double widthScale = originalRoomDimension.getWidth() / roomLocation.getWidth();
            double heightScale = originalRoomDimension.getHeight() / roomLocation.getHeight();

            //With the height scale, apply the scaling to the new room dimensions
            this.roomLocation = new Rectangle2D.Double(roomLocation.getX(), roomLocation.getY(), referencedRoom.getProperties().getSize().getWidth() * widthScale, referencedRoom.getProperties().getSize().getHeight() * heightScale);

            //Set the original room location to the new room location so that future room size changes do not ruin the aspect ratio
            this.originalRoomDimension = referencedRoom.getProperties().getSize();
        }

        this.invalidate();
        this.repaint();
    }

    MouseWheelListener mouseWheelListener = new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int rotation = e.getWheelRotation();
            //For each rotation, we want to zoom in 5%.
            double widthScale = referencedRoom.getProperties().getSize().getWidth() * 0.05;
            double heightScale = referencedRoom.getProperties().getSize().getHeight() * 0.05;
            double roomXOffset = widthScale / 2;
            double roomYOffset = heightScale / 2;

            //Check what direction we are zooming.
            if((abs(rotation)/rotation) == 1){
                //We are zooming out, we want to move the origin in
                roomLocation.setRect(roomLocation.getX() + roomXOffset, roomLocation.getY() + roomYOffset, roomLocation.getWidth() - (widthScale * rotation), roomLocation.getHeight() - (heightScale * rotation));
            } else {
                //We are zooming in, we want to move the origin out
                roomLocation.setRect(roomLocation.getX() - roomXOffset, roomLocation.getY() - roomYOffset, roomLocation.getWidth() - (widthScale * rotation), roomLocation.getHeight() - (heightScale * rotation));
            }
            RoomEditorCanvas canvas = (RoomEditorCanvas) e.getSource();
            canvas.repaint();
        }
    };
}
