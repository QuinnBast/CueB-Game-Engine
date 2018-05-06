package org.applicationEngine.graphics.Cameras;

import org.applicationEngine.objects.Base.SpriteObject;

import java.awt.*;

/**
 * Created by Quinn on 4/27/2018.
 */
public class MovingCenteredCamera extends Camera {

    private SpriteObject centerOn;

    /**
     *
     * @param width
     * @param height
     * @param centerOn
     */
    public MovingCenteredCamera(int width, int height, SpriteObject centerOn) {
        super();
        this.roomLocation.setRect(centerOn.getPosX() - width/2, centerOn.getPosY() - height/2, width, height);
        this.screenLocation = this.roomLocation;
        this.centerOn = centerOn;
    }

    /**
     *
     * @param spriteObject
     */
    public void setCenterOn(SpriteObject spriteObject){
        this.centerOn = spriteObject;
        this.roomLocation.setRect(centerOn.getPosX() - this.roomLocation.getWidth()/2, centerOn.getPosY() - this.roomLocation.getHeight()/2, this.roomLocation.getWidth(), this.roomLocation.getHeight());
    }

    /**
     *
     * @return
     */
    public SpriteObject getCenteredOn(){ return this.centerOn; }

    @Override
    public boolean render(Graphics g){
        //If the camera is a centered camera, ensure that the camera is centering on the object before rending.
        this.roomLocation.setRect(centerOn.getPosX() - this.roomLocation.getWidth()/2, centerOn.getPosY() - this.roomLocation.getHeight()/2, this.roomLocation.getWidth(), this.roomLocation.getHeight());
        this.screenLocation = this.roomLocation;
        return super.render(g);
    }
}
