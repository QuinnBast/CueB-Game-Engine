package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor;

import org.applicationEngine.graphics.*;
import org.developmentEngine.resourceManager.Resources.Instance;
import org.developmentEngine.resourceManager.resourceProperties.*;
import org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor.dragNdrop.DropPane;
import org.developmentEngine.resourceManager.Resources.RoomResource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Quinn on 5/14/2018.
 */
public class RoomEditorCanvas extends DropPane implements PropertyObserver {

    Rectangle2D rectangle = new Rectangle2D.Double(0, 0, 0, 0);
    RoomResource referencedRoom;

    public RoomEditorCanvas(RoomResource rr){
        this.referencedRoom = rr;
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionAdapter);
        rr.getProperties().addPropertyObserver(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        ArrayList<Instance> instanceInRoom = ((RoomProperties)referencedRoom.getProperties()).getInstances();
        for(Instance i : instanceInRoom){
            //Get the sprite properties of the object.
            SpriteProperties spriteProperties = ((SpriteProperties)((ObjectProperties)((InstanceProperties)i.getProperties()).getObjectType().getProperties()).getLinkedSprite().getProperties());
            //Determine if the object has a linked sprite
            if(spriteProperties.getFilepaths() != null){
                //Create a buffered image from the sprite to draw on the canvas.
                try {
                    //Get the buffered image.
                    BufferedImage rawImage = ImageIO.read(new File(spriteProperties.getFilepaths().get(0)));
                    //Draw the buffered image at the room location.
                    //Offset the buffered image so that the image is drawn with the origin in the correct location
                    double xOrigin = spriteProperties.getOrigin().getX();
                    double yOrigin = spriteProperties.getOrigin().getY();
                    double drawXOffset = ((InstanceProperties)i.getProperties()).getRoomLocation().getX() - xOrigin;
                    double drawYOffset = ((InstanceProperties) i.getProperties()).getRoomLocation().getY() - yOrigin;

                    g.drawImage(rawImage, (int)drawXOffset, (int)drawYOffset, ((Double)spriteProperties.getSize().getWidth()).intValue(), ((Double)spriteProperties.getSize().getHeight()).intValue(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }


        g2d.dispose();
    }

    public RoomResource getReferencedRoom(){
        return this.referencedRoom;
    }

    public RoomProperties getRoomProperties(){
        return ((RoomProperties)this.referencedRoom.getProperties());
    }


    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            rectangle.setRect(e.getX(), e.getY(), 0, 0);
            RoomEditorCanvas canvas = (RoomEditorCanvas) e.getSource();
            canvas.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            rectangle.setRect(rectangle.getX(), rectangle.getY(), e.getX() - rectangle.getX(), e.getY() - rectangle.getY());
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
                rectangle.setRect(rectangle.getX(), rectangle.getY(), e.getX() - rectangle.getX(), e.getY() - rectangle.getY());
                RoomEditorCanvas canvas = (RoomEditorCanvas) e.getSource();
                canvas.repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e)) {
                super.mouseMoved(e);
                rectangle.setRect(rectangle.getX(), rectangle.getY(), rectangle.getX() - e.getX(), rectangle.getY() - e.getY());
                RoomEditorCanvas canvas = (RoomEditorCanvas) e.getSource();
                canvas.repaint();
            }
        }
    };

    @Override
    public void onResourceUpdate(ResourceProperties properties) {
        this.invalidate();
        this.repaint();
    }
}
