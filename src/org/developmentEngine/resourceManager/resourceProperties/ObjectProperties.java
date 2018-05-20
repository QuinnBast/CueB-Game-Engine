package org.developmentEngine.resourceManager.resourceProperties;

import org.userInterface.window.fileBrowser.Resources.ObjectResource;
import org.userInterface.window.fileBrowser.Resources.SpriteResource;

import java.awt.geom.Point2D;

/**
 * Created by Quinn on 5/19/2018.
 */
public class ObjectProperties extends ResourceProperties {

    private String name = "";
    private SpriteResource linkedSprite = null;
    private Point2D position = new Point2D.Double(0, 0);
    private boolean isCollidable = true;
    private boolean canMove = true;
    private boolean isVisible = true;
    private int zIndex = 1;
    private ObjectResource parentObject = null;

    public void setName(String name) {
        this.name = name;
    }

    public void setLinkedSprite(SpriteResource linkedSprite) {
        this.linkedSprite = linkedSprite;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setCollidable(boolean collidable) {
        isCollidable = collidable;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public void setParentObject(ObjectResource parentObject) {
        this.parentObject = parentObject;
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

    public boolean isCanMove() {
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
}
