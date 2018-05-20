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
        this.notifyUpdate();
    }

    public void setLinkedSprite(SpriteResource linkedSprite) {
        this.linkedSprite = linkedSprite;
        this.notifyUpdate();
    }

    public void setPosition(Point2D position) {
        this.position = position;
        this.notifyUpdate();
    }

    public void setCollidable(boolean collidable) {
        isCollidable = collidable;
        this.notifyUpdate();
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
        this.notifyUpdate();
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
        this.notifyUpdate();
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
        this.notifyUpdate();
    }

    public void setParentObject(ObjectResource parentObject) {
        this.parentObject = parentObject;
        this.notifyUpdate();
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
