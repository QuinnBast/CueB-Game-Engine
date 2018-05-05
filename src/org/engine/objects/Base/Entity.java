package org.engine.objects.Base;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Quinn on 11/29/2017.
 */
public abstract class Entity extends Sprite {


    protected int health;
    protected int movementSpeed;
    protected boolean isCollidable;     //If the object can be collided with
    protected boolean canMove;          //If the object can move
    protected boolean canDisplace;      //If the object can be displaced
    protected boolean canRotate;        //Determine if the object should be rotated on rendering

    public Entity(float posX, float posY, String image, boolean isCollidable, boolean canMove, boolean canDisplace, boolean canRotate) {
        super(posX, posY, image);

        this.isCollidable = isCollidable;
        this.canMove = canMove;
        this.canDisplace = canDisplace;
        this.canRotate = canRotate;
    }
    public Entity(float posX, float posY, float width, float height, boolean isCollidable, boolean canMove, boolean canDisplace, boolean canRotate) {
        super(posX, posY, width, height);

        this.isCollidable = isCollidable;
        this.canMove = canMove;
        this.canDisplace = canDisplace;
        this.canRotate = canRotate;
    }
    public boolean isColliidng(Entity object, float deltaTime){
        //Determine object in question's extreme bounding box points.
        Rectangle2D boundingbox = object.getBoundingBox();
        if(this.boundingBox.contains(boundingbox) || this.boundingBox.intersects(boundingbox)){
            this.collisionResolution(object, deltaTime);
            return true;
        }
        return false;
    }
    public abstract void collisionResolution(Entity object, float deltaTime);
    public Point2D getCollisionPoint(Entity object) {
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
        if(this.image != null){
            this.boundingBox = new Rectangle2D.Double(posX - this.image.getWidth()/2, posY - this.image.getHeight()/2, this.image.getWidth(), this.image.getHeight());
        } else {
            this.boundingBox = new Rectangle2D.Double(posX - this.boundingBox.getWidth() / 2, posY - this.boundingBox.getHeight() / 2, this.boundingBox.getWidth(), this.boundingBox.getHeight());
        }
    }
    public void setCanMove(boolean value){
        this.canMove = value;
    }
    public boolean canMove(){
        return this.canMove;
    }
    public void setCanDisplace(boolean value){
        this.canDisplace = value;
    }
    public boolean canDisplace(){
        return this.canDisplace;
    }
    public void setCanRotate(boolean value){
        this.canRotate = value;
    }
    public boolean canRotate(){
        return this.canRotate;
    }
}
