package org.applicationEngine.objects.Base;

import org.applicationEngine.Events.*;
import org.applicationEngine.game.Game;
import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Quinn on 11/29/2017.
 */
public class Object{

    private ObjectResource objectReference;
    private Rectangle2D boundingBox;
    private BufferedImage sprite;
    private EventHandler eventHandler;

    public Object(ObjectResource objectReference){
        this.objectReference = (ObjectResource)objectReference.deepCopy();
        this.boundingBox = this.objectReference.getProperties().getLinkedSprite().getProperties().getBoundingBox();
        this.sprite = this.objectReference.getProperties().getLinkedSprite().getProperties().getBufferedImage();
        Game.eventListener.addEventObserver(new EventObserver(this));
    }

    public boolean isColliidng(Object object, float deltaTime){
        //Determine object in question's extreme bounding box points.
        Rectangle2D boundingbox = object.getBoundingBox();
        if(this.boundingBox.contains(boundingbox) || this.boundingBox.intersects(boundingbox)){
            ArrayList<Object> involvedObjects = new ArrayList<Object>();
            involvedObjects.add(this);
            involvedObjects.add(object);
            new Event(EventType.onCollision, involvedObjects);
            return true;
        }
        return false;
    }

    public void setEventHandler(EventHandler handler){
        //Get the user compiled event handler and attach it to the object.
        this.eventHandler = handler;
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

    public void notifyEvent(Event e){
        //Handle an event that has fired with this object.
        EventType event = e.getEventType();
        switch(event){
            case onCreate:
                eventHandler.onCreate();
                break;
            case onMove:
                eventHandler.onMove();
                break;
            case onUpdate:
                eventHandler.onUpdate();
                break;
            case onCollision:
                eventHandler.onCollision(e.getInvolvedObjects());
                break;
            case onDestroy:
                eventHandler.onDestroy();
                break;
            case onAnimationEnd:
                eventHandler.onAnimationEnd();
                break;
            case onAnimationStart:
                eventHandler.onAnimationStart();
                break;
            case onKeyPress:
                eventHandler.onKeyPressed();
                break;
            case onKeyDown:
                eventHandler.onKeyDown();
                break;
            case onKeyUp:
                eventHandler.onKeyUp();
                break;
            case onMouseUp:
                eventHandler.onMouseUp();
                break;
            case onMouseDown:
                eventHandler.onMouseDown();
                break;
            case onMouseEnter:
                eventHandler.onMouseEnter();
                break;
            case onMouseLeave:
                eventHandler.onMouseLeave();
                break;
            case onDrawBegin:
                eventHandler.onDrawBegin();
                break;
            case onDrawEnd:
                eventHandler.onDrawEnd();
                break;
            case onRoomEnter:
                eventHandler.onRoomEnter();
                break;
            case onRoomLeave:
                eventHandler.onRoomLeave();
                break;
            case onTimerStart:
                eventHandler.onTimerStart();
                break;
            case onTimerEnd:
                eventHandler.onTimerEnd();
                break;
            case onRoomUpdate:
                eventHandler.onRoomUpdate();
                break;
        }
    }

}
