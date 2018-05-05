package org.engine.graphics.Cameras;

import org.engine.objects.Base.Sprite;

import java.awt.*;

/**
 * Created by Quinn on 4/27/2018.
 */
public class MovingCenteredCamera extends Camera {

    private Sprite centerOn;

    /**
     *
     * @param width
     * @param height
     * @param centerOn
     */
    public MovingCenteredCamera(int width, int height, Sprite centerOn) {
        super();
        this.roomLocation.setRect(centerOn.getPosX() - width/2, centerOn.getPosY() - height/2, width, height);
        this.screenLocation = this.roomLocation;
        this.centerOn = centerOn;
    }

    /**
     *
     * @param sprite
     */
    public void setCenterOn(Sprite sprite){
        this.centerOn = sprite;
        this.roomLocation.setRect(centerOn.getPosX() - this.roomLocation.getWidth()/2, centerOn.getPosY() - this.roomLocation.getHeight()/2, this.roomLocation.getWidth(), this.roomLocation.getHeight());
    }

    /**
     *
     * @return
     */
    public Sprite getCenteredOn(){ return this.centerOn; }

    @Override
    public boolean render(Graphics g){
        //If the camera is a centered camera, ensure that the camera is centering on the object before rending.
        this.roomLocation.setRect(centerOn.getPosX() - this.roomLocation.getWidth()/2, centerOn.getPosY() - this.roomLocation.getHeight()/2, this.roomLocation.getWidth(), this.roomLocation.getHeight());
        this.screenLocation = this.roomLocation;
        return super.render(g);
    }
}
