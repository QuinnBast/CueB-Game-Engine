package org.graphics;

import org.objects.Sprite;
import org.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Quinn on 11/29/2017.
 */
public abstract class Camera {

    protected int width;
    protected int height;
    protected float x;
    protected float y;



    public void resize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public float getCamX(){
        return this.x;
    }
    public float getCamY(){
        return this.y;
    }
    public abstract float getMaxX();
    public abstract float getMinX();
    public abstract float getMaxY();
    public abstract float getMinY();

    public void render(Graphics g){
        //Draw camera bounds (Debugging)
        g.setColor(Color.RED);
        g.drawLine((int)(getMaxX()), (int)(getMaxY()), (int)(getMaxX()), (int)(getMinY()));
        g.drawLine((int)(getMaxX()), (int)(getMaxY()), (int)(getMinX()), (int)(getMaxY()));
        g.drawLine((int)(getMinX()), (int)(getMinY()), (int)(getMaxX()), (int)(getMinY()));
        g.drawLine((int)(getMinX()), (int)(getMinY()), (int)(getMinX()), (int)(getMaxY()));

        //Determine the sprites that need to be rendered
        for(Sprite s : World.sprites) {
            //If the sprite is within the camera's boundary, draw the sprite to the screen.
            if(s.getPosX() <= this.getMaxX() && s.getPosX() >= getMinX() && s.getPosY() <= getMaxY() && s.getPosY() >= getMinY()){
                //Draw the sprite.
                if(s.getImage() == null){
                    return;
                }

                //Rotate the sprite if required
                double rotationRequired = s.getAngle();
                double locationX = (s.getImage().getWidth() / 2) - 1;
                double locationY = (s.getImage().getHeight() / 2) - 1;

                AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

                BufferedImage newImage = new BufferedImage(s.getImage().getWidth(), s.getImage().getHeight(), s.getImage().getType());
                op.filter(s.getImage(), newImage);  //Create the new rotated image.

                Graphics2D g2d = (Graphics2D) g;

                g.setColor(Color.RED);
                g.drawString("posX:" + (s.getPosX()), 100,190);
                g.drawString("posY:" + (s.getPosY()), 100,210);

                //Determine the location relative to the camera
                Point p = this.getRelativeLocation(s);


                g.drawString("pointX:" + (p.getX()), 100,130);
                g.drawString("pointY:" + (p.getY()), 100,160);

                //Debugging
                g.drawLine((int)(p.getX() + this.getMinX()), (int)(p.getY() + this.getMinY()), (int)(MouseInfo.getPointerInfo().getLocation().getX()), (int)(MouseInfo.getPointerInfo().getLocation().getY()));

                //Draw the sprite to the screen
                g.drawImage(newImage, (int)(p.getX() - s.getImage().getWidth()/2 + this.getMinX()), (int)(p.getY() - s.getImage().getHeight()/2 + this.getMinY()), s.getImage().getWidth(), s.getImage().getHeight(), null);
            }
        }
    }

    public Point getRelativeLocation(Sprite s){
        double x = s.getPosX() - this.getMinX();
        double y = s.getPosY() - this.getMinY();
        Point p = new Point();
        p.setLocation(x, y);
        return p;
    }
}
