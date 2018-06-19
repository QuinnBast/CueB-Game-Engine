package org.developmentEngine.resourceManager.resourceProperties;

import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.Resources.SpriteResource;

import java.awt.geom.Point2D;

/**
 * Created by Quinn on 5/19/2018.
 */
public class ObjectProperties extends ResourceProperties implements PropertyObserver {

    private String name = "";
    private SpriteResource linkedSprite = null;
    private Point2D position = new Point2D.Double(0, 0);
    private boolean isCollidable = true;
    private boolean canMove = true;
    private boolean isVisible = true;
    private int zIndex = 1;
    private ObjectResource parentObject = null;
    private boolean canDisplace;      //If the object can be displaced
    private boolean canRotate;        //Determine if the object should be rotated on rendering

    public void setName(String name) {
        this.name = name;
        this.notifyUpdate(this);
    }

    public void setLinkedSprite(SpriteResource linkedSprite) {
        this.linkedSprite = linkedSprite;
        if(this.linkedSprite != null) {
            this.linkedSprite.getProperties().addPropertyObserver(this);
        }
        this.notifyUpdate(this);
    }

    public void setPosition(Point2D position) {
        this.position = position;
        this.notifyUpdate(this);
    }

    public void setCollidable(boolean collidable) {
        isCollidable = collidable;
        this.notifyUpdate(this);
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
        this.notifyUpdate(this);
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
        this.notifyUpdate(this);
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
        this.notifyUpdate(this);
    }

    public void setParentObject(ObjectResource parentObject) {
        this.parentObject = parentObject;
        this.notifyUpdate(this);
    }

    public String getName() {

        return name;
    }

    public SpriteResource getLinkedSprite() {
        return linkedSprite;
    }

    public Point2D getPosition() {
        return position;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public boolean canMove() {
        return canMove;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public int getzIndex() {
        return zIndex;
    }

    public ObjectResource getParentObject() {
        return parentObject;
    }

    public ObjectProperties(){

    }

    @Override
    public void onResourceUpdate(ResourceProperties properties) {
        //If this object's sprite is updated, this object needs to notify all of its watchers that a link has been updated!
        this.notifyUpdate(this);
    }

    public boolean canDisplace() {
        return canDisplace;
    }

    public void setCanDisplace(boolean canDisplace) {
        this.canDisplace = canDisplace;
    }

    public boolean canRotate() {
        return canRotate;
    }

    public void setCanRotate(boolean canRotate) {
        this.canRotate = canRotate;
    }
}
