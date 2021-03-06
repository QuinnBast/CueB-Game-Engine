package org.applicationEngine.graphics.Cameras;

import org.applicationEngine.graphics.Renderer;
import org.applicationEngine.objects.Base.Object;

import java.awt.*;

/**
 * Created by Quinn on 4/28/2018.
 */
public class StaticCenteredCamera extends Camera {

    private Object centerOn;

    /**
     *
     * @param sceenCenterX
     * @param screenCenterY
     * @param screenDisplayWidth
     * @param screenDisplayHeight
     * @param centerOn
     * @param roomWidth
     * @param roomHeight
     */
    public StaticCenteredCamera(float sceenCenterX, float screenCenterY, int screenDisplayWidth, int screenDisplayHeight, Object centerOn, int roomWidth, int roomHeight) {
        super();
        this.roomLocation.setRect(centerOn.getObjectProperties().getPosition().getX() - roomWidth/2, centerOn.getObjectProperties().getPosition().getY() - roomHeight/2, roomWidth, roomHeight);
        this.screenLocation.setRect(sceenCenterX - screenDisplayWidth/2, screenCenterY - screenDisplayHeight/2, screenDisplayWidth, screenDisplayHeight);
        this.centerOn = centerOn;
    }

    /**
     *
     * @param centerOn
     * @param roomWidth
     * @param roomHeight
     */
    public StaticCenteredCamera(Object centerOn, int roomWidth, int roomHeight){
        super();
        this.roomLocation.setRect(centerOn.getObjectProperties().getPosition().getX() - roomWidth/2, centerOn.getObjectProperties().getPosition().getY() - roomHeight/2, roomWidth, roomHeight);
        this.screenLocation.setRect(0,0, Renderer.getCanvasWidth(), Renderer.getCanvasHeight());
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

    public Object getCenteredOn(){ return this.centerOn; }

    @Override
    public boolean render(Graphics g){
        //If the camera is a centered camera, ensure that the camera is centering on the object before rending.
        this.roomLocation.setRect(centerOn.getObjectProperties().getPosition().getX() - this.roomLocation.getWidth()/2, centerOn.getObjectProperties().getPosition().getY() - this.roomLocation.getHeight()/2, this.roomLocation.getWidth(), this.roomLocation.getHeight());
        return super.render(g);
    }

}
