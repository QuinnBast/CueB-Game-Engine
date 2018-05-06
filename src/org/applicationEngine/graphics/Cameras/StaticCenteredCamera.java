package org.applicationEngine.graphics.Cameras;

import org.applicationEngine.graphics.Renderer;
import org.applicationEngine.objects.Base.SpriteObject;

import java.awt.*;

/**
 * Created by Quinn on 4/28/2018.
 */
public class StaticCenteredCamera extends Camera {

    private SpriteObject centerOn;

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
    public StaticCenteredCamera(float sceenCenterX, float screenCenterY, int screenDisplayWidth, int screenDisplayHeight, SpriteObject centerOn, int roomWidth, int roomHeight) {
        super();
        this.roomLocation.setRect(centerOn.getPosX() - roomWidth/2, centerOn.getPosY() - roomHeight/2, roomWidth, roomHeight);
        this.screenLocation.setRect(sceenCenterX - screenDisplayWidth/2, screenCenterY - screenDisplayHeight/2, screenDisplayWidth, screenDisplayHeight);
        this.centerOn = centerOn;
    }

    /**
     *
     * @param centerOn
     * @param roomWidth
     * @param roomHeight
     */
    public StaticCenteredCamera(SpriteObject centerOn, int roomWidth, int roomHeight){
        super();
        this.roomLocation.setRect(centerOn.getPosX() - roomWidth/2, centerOn.getPosY() - roomHeight/2, roomWidth, roomHeight);
        this.screenLocation.setRect(0,0, Renderer.getCanvasWidth(), Renderer.getCanvasHeight());
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

    public SpriteObject getCenteredOn(){ return this.centerOn; }

    @Override
    public boolean render(Graphics g){
        //If the camera is a centered camera, ensure that the camera is centering on the object before rending.
        this.roomLocation.setRect(centerOn.getPosX() - this.roomLocation.getWidth()/2, centerOn.getPosY() - this.roomLocation.getHeight()/2, this.roomLocation.getWidth(), this.roomLocation.getHeight());
        return super.render(g);
    }

}
