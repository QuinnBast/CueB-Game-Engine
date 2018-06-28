package org.userInterface.window.centerScreen.resourceTabs.spriteTabs;

import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Quinn on 5/21/2018.
 */
public class SpritePropertyCanvas extends JPanel implements PropertyObserver {

    protected SpriteProperties referencedProperties;
    Double imageToCanvasHeightRatio;
    Double imageToCanvasWidthRatio;

    public SpritePropertyCanvas(SpriteProperties properties){
        this.setVisible(true);
        this.setSize(new Dimension(300, 300));
        this.referencedProperties = properties;
        referencedProperties.addPropertyObserver(this);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(motionAdapter);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage rawImage = null;
        try {
            if(referencedProperties.getFilepaths().get(0) != "") {
                File file = new File(referencedProperties.getFilepaths().get(0));
                if (file != null) {
                    rawImage = ImageIO.read(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Point2D scaledOrigin = referencedProperties.getOrigin();

        if(rawImage != null) {
            Image scaledImage = rawImage.getScaledInstance(300, 300, 0);
            g.drawImage(scaledImage, 0, 0, null);

            //Determine scale ratio
            imageToCanvasHeightRatio = referencedProperties.getSize().getHeight()/299;
            imageToCanvasWidthRatio = referencedProperties.getSize().getWidth()/299;

            g2d.setColor(Color.BLUE);
            Rectangle2D boundingBox = referencedProperties.getBoundingBox();
            Rectangle2D scaledBoundingBox = new Rectangle2D.Double(0, 0, boundingBox.getWidth() / imageToCanvasWidthRatio, boundingBox.getHeight() / imageToCanvasHeightRatio);
            g2d.draw(scaledBoundingBox);

            Point2D origin = referencedProperties.getOrigin();
            scaledOrigin = new Point2D.Double(origin.getX() / imageToCanvasWidthRatio, origin.getY() / imageToCanvasHeightRatio);
        }

        Line2D verticalLine = new Line2D.Double((int)scaledOrigin.getX() - 50, (int)scaledOrigin.getY(), (int)scaledOrigin.getX() + 50, (int)scaledOrigin.getY());
        Line2D horizontalLine = new Line2D.Double((int)scaledOrigin.getX(), (int)scaledOrigin.getY() - 50, (int)scaledOrigin.getX(), (int)scaledOrigin.getY()+ 50);
        Rectangle2D.Double point = new Rectangle2D.Double(scaledOrigin.getX() - 3 ,scaledOrigin.getY() - 3, 6, 6);

        g2d.setColor(Color.RED);
        g2d.draw(verticalLine);
        g2d.draw(horizontalLine);
        g2d.draw(point);

        g2d.dispose();
    }


    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(referencedProperties != null) {
                referencedProperties.setOrigin(new Point2D.Double((int) (e.getX() * imageToCanvasWidthRatio), (int) (e.getY() * imageToCanvasHeightRatio)));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(referencedProperties != null) {
                referencedProperties.setOrigin(new Point2D.Double((int) (e.getX() * imageToCanvasWidthRatio), (int) (e.getY() * imageToCanvasHeightRatio)));
            }
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
                if(referencedProperties != null) {
                    super.mouseDragged(e);
                    referencedProperties.setOrigin(new Point2D.Double((int) (e.getX() * imageToCanvasWidthRatio), (int) (e.getY() * imageToCanvasHeightRatio)));
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(SwingUtilities.isLeftMouseButton(e)) {
                if(referencedProperties != null) {
                    super.mouseMoved(e);
                    referencedProperties.setOrigin(new Point2D.Double((int) (e.getX() * imageToCanvasWidthRatio), (int) (e.getY() * imageToCanvasHeightRatio)));
                }
            }
        }
    };

    @Override
    public void onPropertyUpdate(ResourceProperties properties) {
        this.repaint();
    }
}
