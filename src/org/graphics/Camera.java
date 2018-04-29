package org.graphics;

import org.objects.Entity;
import org.objects.Sprite;
import org.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Quinn on 11/29/2017.
 */
public abstract class Camera {

    Rectangle2D viewingArea = new Rectangle2D.Double();

    public void resize(int width, int height){
        this.viewingArea.setRect(viewingArea.getCenterX(), viewingArea.getCenterY(), width, height);
    }

    public Rectangle2D getViewingArea(){
        return this.viewingArea;
    }

    public void render(Graphics g){
        //Draw camera bounds (Debugging)
        g.setColor(Color.RED);
        g.drawLine((int)(viewingArea.getMaxX()), (int)(viewingArea.getMaxY()), (int)(viewingArea.getMaxX()), (int)(viewingArea.getMinY()));
        g.drawLine((int)(viewingArea.getMaxX()), (int)(viewingArea.getMaxY()), (int)(viewingArea.getMinX()), (int)(viewingArea.getMaxY()));
        g.drawLine((int)(viewingArea.getMinX()), (int)(viewingArea.getMinY()), (int)(viewingArea.getMaxX()), (int)(viewingArea.getMinY()));
        g.drawLine((int)(viewingArea.getMinX()), (int)(viewingArea.getMinY()), (int)(viewingArea.getMinX()), (int)(viewingArea.getMaxY()));

        //Determine the sprites that need to be rendered
        for(Entity entity : World.objects) {
            //If the sprite is within the camera's boundary, draw the sprite to the screen.

            if(this.viewingArea.contains(entity.getBoundingBox()) || this.viewingArea.intersects(entity.getBoundingBox())){
                //Draw the sprite.
                if(entity.getImage() == null){
                    //Determine the location relative to the camera
                    //Draw the bounding box.

                    Point p = this.getRelativeLocation(entity);
                    Rectangle2D bb = entity.getBoundingBox();
                    g.drawRect((int)(p.getX() - bb.getWidth()/2 + viewingArea.getMinX()), (int)(p.getY() - bb.getHeight()/2 + viewingArea.getMinY()), (int)bb.getWidth(), (int)bb.getHeight());
                    return;
                }

                //Rotate the sprite if required
                double rotationRequired = entity.getAngle();
                double locationX = (entity.getImage().getWidth() / 2) - 1;
                double locationY = (entity.getImage().getHeight() / 2) - 1;

                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

                BufferedImage newImage = new BufferedImage(entity.getImage().getWidth(), entity.getImage().getHeight(), entity.getImage().getType());
                op.filter(entity.getImage(), newImage);  //Create the new rotated image.

                Graphics2D g2d = (Graphics2D) g;

                //Determine the location relative to the camera
                Point p = this.getRelativeLocation(entity);

                //Debugging
                Rectangle2D bb = entity.getBoundingBox();
                g.drawRect((int)(p.getX() - entity.getImage().getWidth()/2 + viewingArea.getMinX()), (int)(p.getY() - entity.getImage().getHeight()/2 + viewingArea.getMinY()), entity.getImage().getWidth(), entity.getImage().getHeight());

                //Draw the sprite to the screen
                g.drawImage(newImage, (int)(p.getX() - entity.getImage().getWidth()/2 + viewingArea.getMinX()), (int)(p.getY() - entity.getImage().getHeight()/2 + viewingArea.getMinY()), entity.getImage().getWidth(), entity.getImage().getHeight(), null);
            }
        }
    }

    public Point getRelativeLocation(Sprite s){
        double x = s.getPosX() - viewingArea.getMinX();
        double y = s.getPosY() - viewingArea.getMinY();
        Point p = new Point();
        p.setLocation(x, y);
        return p;
    }
}
