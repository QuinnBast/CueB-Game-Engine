package org.applicationEngine.objects.Base;

import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Quinn on 11/29/2017.
 */
public class Object{

    private ObjectResource objectReference;
    private Rectangle2D boundingBox;
    private BufferedImage sprite;

    public Object(ObjectResource objectReference){
        this.objectReference = (ObjectResource)objectReference.deepCopy();
        this.boundingBox = this.objectReference.getProperties().getLinkedSprite().getProperties().getBoundingBox();
        this.sprite = this.objectReference.getProperties().getLinkedSprite().getProperties().getBufferedImage();
    }

    public boolean isColliidng(Object object, float deltaTime){
        //Determine object in question's extreme bounding box points.
        Rectangle2D boundingbox = object.getBoundingBox();
        if(this.boundingBox.contains(boundingbox) || this.boundingBox.intersects(boundingbox)){
            this.collisionResolution(object, deltaTime);
            return true;
        }
        return false;
    }
    public void collisionResolution(Object object, float deltaTime){
        //TO DO -- Load with user code for collision resolutions.
    }

    public Point2D getCollisionPoint(Object object) {
        //Assuming rectangle bounding boxes for all entities.

        //Determine closest edge
        double leftRightDiff = Math.abs(this.boundingBox.getMinX() - object.boundingBox.getMaxX());
        double rightLeftDiff = Math.abs(this.boundingBox.getMaxX() - object.boundingBox.getMinX());
        double topBottomDiff = Math.abs(this.boundingBox.getMaxY() - object.boundingBox.getMinY());
        double bottomTopDiff = Math.abs(this.boundingBox.getMinY() - object.boundingBox.getMaxY());

        double smallest = leftRightDiff;
        String closest = "LeftRight";

        if (rightLeftDiff < smallest){
            smallest = rightLeftDiff;
            closest = "RightLeft";
        }
        if (topBottomDiff < smallest){
            smallest = topBottomDiff;
            closest = "TopBottom";
        }
        if(bottomTopDiff < smallest){
            smallest = bottomTopDiff;
            closest = "BottomTop";
        }

        switch(closest){
            case "LeftRight":
                return new Point2D.Double(this.boundingBox.getMinX(), this.boundingBox.getCenterY());
            case "RightLeft":
                return new Point2D.Double(this.boundingBox.getMaxX(), this.boundingBox.getCenterY());
            case "TopBottom":
                return new Point2D.Double(this.boundingBox.getCenterX(), this.boundingBox.getMaxY());
            case "BottomTop":
                return new Point2D.Double(this.boundingBox.getCenterX(), this.boundingBox.getMinY());
            default:
                return null;
        }
    }
    public void update(float deltaTime){
        if(this.sprite != null){
            this.boundingBox = new Rectangle2D.Double(this.objectReference.getProperties().getPosition().getX() - this.sprite.getWidth()/2, this.objectReference.getProperties().getPosition().getY() - this.sprite.getHeight()/2, this.sprite.getWidth(), this.sprite.getHeight());
        } else {
            this.boundingBox = new Rectangle2D.Double(this.objectReference.getProperties().getPosition().getX() - this.boundingBox.getWidth() / 2, this.objectReference.getProperties().getPosition().getY() - this.boundingBox.getHeight() / 2, this.boundingBox.getWidth(), this.boundingBox.getHeight());
        }
    }

    public ObjectProperties getObjectProperties(){
        return this.objectReference.getProperties();
    }

    public BufferedImage getImage(){
        return this.sprite;
    }

    public Rectangle2D getBoundingBox(){
        return this.boundingBox;
    }

}
