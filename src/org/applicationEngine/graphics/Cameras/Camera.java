package org.applicationEngine.graphics.Cameras;

import org.applicationEngine.game.Game;
import org.applicationEngine.objects.Base.Object;
import org.applicationEngine.world.Room;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by Quinn on 11/29/2017.
 */
public abstract class Camera<T> {

    protected Rectangle2D roomLocation = new Rectangle2D.Double(); //The area on the map that is visible
    protected Rectangle2D screenLocation = new Rectangle2D.Double(); //The area of the screen the camera takes up
    private double widthRatio;
    private double heightRatio;
    private boolean isActive;

    public Camera(){
        //Add every camera that is created to the world's list of cameras
        Room.addCamera(this);
    }

    public void resizeViewingArea(int width, int height){
        this.roomLocation.setRect(roomLocation.getCenterX(), roomLocation.getCenterY(), width, height);
    }

    public void resizeDisplayArea(int width, int height){
        this.screenLocation.setRect(roomLocation.getCenterX(), roomLocation.getCenterY(), width, height);
    }

    public Rectangle2D getRoomLocation(){
        return this.roomLocation;
    }

    public Rectangle2D getScreenLocation(){
        return this.screenLocation;
    }

    public boolean render(Graphics g){
        if(!this.isActive){
            return false;
        }
        this.widthRatio = this.screenLocation.getWidth() / this.roomLocation.getWidth();
        this.heightRatio = this.screenLocation.getHeight() / this.roomLocation.getHeight();

        //Draw camera bounds (Debugging)
        g.setColor(Color.RED);
        g.drawLine((int)(screenLocation.getMaxX()), (int)(screenLocation.getMaxY()), (int)(screenLocation.getMaxX()), (int)(screenLocation.getMinY()));
        g.drawLine((int)(screenLocation.getMaxX()), (int)(screenLocation.getMaxY()), (int)(screenLocation.getMinX()), (int)(screenLocation.getMaxY()));
        g.drawLine((int)(screenLocation.getMinX()), (int)(screenLocation.getMinY()), (int)(screenLocation.getMaxX()), (int)(screenLocation.getMinY()));
        g.drawLine((int)(screenLocation.getMinX()), (int)(screenLocation.getMinY()), (int)(screenLocation.getMinX()), (int)(screenLocation.getMaxY()));

        //Determine the sprites that need to be rendered
        //Sprites that need to be rendered will be inside or intersecting the viewing area.
        for(Object object : Game.roomManager.getActiveRoom().getObjects()) {
            //If the sprite is within the camera's viewing area, the sprite should be drawn.
            if(this.roomLocation.contains(object.getBoundingBox()) || this.roomLocation.intersects(object.getBoundingBox())){

                //Determine if there is an image to display for the object.
                if(object.getImage() == null){
                    //If there is no image to display, draw the boudning box inside the DISPLAY area at the relative position.


                    Point p = this.getRelativeViewingLocation(object); //Determine the location relative to the camera
                    Rectangle2D bb = object.getBoundingBox();   //Get the bounding box

                    //multiply any widths by the width ratio and any heights by the height ratio.
                    g.drawRect((int)((p.getX() - bb.getWidth()/2)*widthRatio + screenLocation.getMinX()), (int)((p.getY() - bb.getHeight()/2)*heightRatio + screenLocation.getMinY()), (int)(bb.getWidth()*widthRatio), (int)(bb.getHeight()*heightRatio));
                    return true;
                }


                //Start rendering the sprite to the relative location on the screen.
                Graphics2D g2d = (Graphics2D) g;

                //Determine the location relative to the camera
                Point relativeViewingLocation = this.getRelativeViewingLocation(object);
                Point relativeDisplayLocation = this.getRelativeDisplayLocation(object, relativeViewingLocation);


                //Rotate the sprite if required
                BufferedImage spriteImage;
                if(object.getObjectProperties().canRotate()) {
                    spriteImage = rotateToMouse(object, relativeDisplayLocation);
                } else {
                    spriteImage = object.getImage();
                }

                //Debugging
                Rectangle2D bb = object.getBoundingBox();
                //Draw line from entity to mouse
                //g.drawLine((int)(relativeDisplayLocation.getX() + entity.getImage().getWidth()*widthRatio/2), (int)(relativeDisplayLocation.getY() + entity.getImage().getHeight()*heightRatio/2), (int)(MouseInfo.getPointerInfo().getLocation().getX()), (int)MouseInfo.getPointerInfo().getLocation().getY());
                //Draw entity's bounding box
                g.drawRect((int)relativeDisplayLocation.getX(), (int)relativeDisplayLocation.getY(), (int)(bb.getWidth()*widthRatio), (int)(bb.getHeight()*heightRatio));

                //Draw the sprite to the screen
                g.drawImage(spriteImage, (int)relativeDisplayLocation.getX(), (int)relativeDisplayLocation.getY(), (int)(object.getObjectProperties().getLinkedSprite().getProperties().getSize().getWidth()*widthRatio), (int)(object.getObjectProperties().getLinkedSprite().getProperties().getSize().getHeight()*heightRatio), null);
            }
        }
        return true;
    }

    public Point getRelativeViewingLocation(Object object){
        double x = object.getObjectProperties().getPosition().getX() - roomLocation.getMinX();
        double y = object.getObjectProperties().getPosition().getY() - roomLocation.getMinY();
        Point p = new Point();
        p.setLocation(x, y);
        return p;
    }

    public Point getRelativeDisplayLocation(Object object, Point relativeViewingLocation){

        double x = ((relativeViewingLocation.getX() - object.getObjectProperties().getLinkedSprite().getProperties().getSize().getWidth()/2)*widthRatio + screenLocation.getMinX());
        double y =  ((relativeViewingLocation.getY() - object.getObjectProperties().getLinkedSprite().getProperties().getSize().getHeight()/2)*heightRatio + screenLocation.getMinY());
        Point p = new Point();
        p.setLocation(x, y);
        return p;
    }

    private BufferedImage rotateToMouse(Object object, Point relativeDisplayLocation) {

        double scaledImageWidth = object.getObjectProperties().getLinkedSprite().getProperties().getSize().getWidth()*widthRatio;
        double scaledImageHeight = object.getObjectProperties().getLinkedSprite().getProperties().getSize().getHeight()*heightRatio;
        double angle = Math.PI/-2;
        if(MouseInfo.getPointerInfo().getLocation().getX() == relativeDisplayLocation.getX() + scaledImageWidth/2){
            if(MouseInfo.getPointerInfo().getLocation().getY() < relativeDisplayLocation.getY() + scaledImageHeight/2){
                angle = Math.PI/-2;
            } else {
                angle = Math.PI/2;
            }
            //Avoiding that divide by zero error like a boss.
        } else {
            angle = Math.atan((double) ((MouseInfo.getPointerInfo().getLocation().getY() - (relativeDisplayLocation.getY() + scaledImageHeight / 2)) / (MouseInfo.getPointerInfo().getLocation().getX() - (relativeDisplayLocation.getX() + scaledImageWidth / 2))));
            if (MouseInfo.getPointerInfo().getLocation().getX() <= relativeDisplayLocation.getX() + scaledImageWidth / 2) {
                angle = angle - Math.PI;
            }
        }

        double locationX = (object.getObjectProperties().getLinkedSprite().getProperties().getSize().getWidth() / 2) - 1;
        double locationY = (object.getObjectProperties().getLinkedSprite().getProperties().getSize().getHeight() / 2) - 1;
        AffineTransform tx = AffineTransform.getRotateInstance(angle, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage rotatedImage = new BufferedImage((int)object.getObjectProperties().getLinkedSprite().getProperties().getSize().getWidth(), (int)object.getObjectProperties().getLinkedSprite().getProperties().getSize().getHeight(), object.getImage().getType());
        op.filter(object.getImage(), rotatedImage);  //Create the new rotated image.
        return rotatedImage;
    }

    public void setActive(){
        this.isActive = true;
    }

    public void setInactive(){
        this.isActive = false;
    }

    public boolean isActive(){
        return this.isActive;
    }
}
