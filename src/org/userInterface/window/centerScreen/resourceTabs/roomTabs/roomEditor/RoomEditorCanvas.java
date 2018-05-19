package org.userInterface.window.centerScreen.resourceTabs.roomTabs.roomEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Quinn on 5/14/2018.
 */
public class RoomEditorCanvas extends JPanel{

    Rectangle2D rectangle = new Rectangle2D.Double(0, 0, 0, 0);

    public RoomEditorCanvas(){
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionAdapter);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.draw(rectangle);
        g2d.drawString("This is some text!", 100, 100);
        g2d.dispose();
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
            rectangle.setRect(rectangle.getX(), rectangle.getY(), rectangle.getX() - e.getX(), rectangle.getY() - e.getY());
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
}
