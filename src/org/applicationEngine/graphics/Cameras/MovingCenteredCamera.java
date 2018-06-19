package org.applicationEngine.graphics.Cameras;

import org.applicationEngine.objects.Base.Object;
import org.developmentEngine.resourceManager.Resources.SpriteResource;

import java.awt.*;

/**
 * Created by Quinn on 4/27/2018.
 */
public class MovingCenteredCamera extends Camera {

    private Object centerOn;

    /**
     *
     * @param width
     * @param height
     * @param centerOn
     */
    public MovingCenteredCamera(int width, int height, Object centerOn) {
        super();
        this.roomLocation.setRect(centerOn.getObjectProperties().getPosition().getX() - width/2, centerOn.getObjectProperties().getPosition().getY() - height/2, width, height);
        this.screenLocation = this.roomLocation;
        this.centerOn = centerOn;
    }

    /**
     *
     * @param spriteObject
     */
    public void setCenterOn(Object spriteObject){
        this.centerOn = spriteObject;
        this.roomLocation.setRect(centerOn.getObjectProperties().getPosition().getX() - this.roomLocation.getWidth()/2, centerOn.getObjectProperties().getPosition().getY() - this.roomLocation.getHeight()/2, this.roomLocation.getWidth(), this.roomLocation.getHeight());
    }

    /**
     *
     * @return
     */
    public Object getCenteredOn(){ return this.centerOn; }

    @Override
    public boolean render(Graphics g){
        //If the camera is a centered camera, ensure that the camera is centering on the object before rending.
        this.roomLocation.setRect(centerOn.getObjectProperties().getPosition().getX() - this.roomLocation.getWidth()/2, centerOn.getObjectProperties().getPosition().getY() - this.roomLocation.getHeight()/2, this.roomLocation.getWidth(), this.roomLocation.getHeight());
        this.screenLocation = this.roomLocation;
        return super.render(g);
    }
}
