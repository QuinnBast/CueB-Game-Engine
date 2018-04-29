package org.graphics;

import org.objects.Base.Sprite;

import java.awt.*;

/**
 * Created by Quinn on 4/28/2018.
 */
public class StaticCenteredCamera extends Camera {

    private Sprite centerOn;

    public StaticCenteredCamera(float centerX, float centerY, int displayWidth, int displayHeight, Sprite centerOn, int viewWidth, int viewHeight) {
        this.viewingArea.setRect(centerOn.getPosX() - viewWidth/2, centerOn.getPosY() - viewHeight/2, viewWidth, viewHeight);
        this.displayArea.setRect(centerX - displayWidth/2, centerY - displayHeight/2, displayWidth, displayHeight);
        this.centerOn = centerOn;
    }

    public StaticCenteredCamera(Sprite centerOn, int viewWidth, int viewHeight){
        this.viewingArea.setRect(centerOn.getPosX() - viewWidth/2, centerOn.getPosY() - viewHeight/2, viewWidth, viewHeight);
        this.displayArea.setRect(0,0, Renderer.getCanvasWidth(), Renderer.getCanvasHeight());
        this.centerOn = centerOn;
    }

    public void setCenterOn(Sprite sprite){
        this.centerOn = sprite;
        this.viewingArea.setRect(centerOn.getPosX() - this.viewingArea.getWidth()/2, centerOn.getPosY() - this.viewingArea.getHeight()/2, this.viewingArea.getWidth(), this.viewingArea.getHeight());
    }

    public Sprite getCenteredOn(){ return this.centerOn; }

    @Override
    public void render(Graphics g){
        //If the camera is a centered camera, ensure that the camera is centering on the object before rending.
        this.viewingArea.setRect(centerOn.getPosX() - this.viewingArea.getWidth()/2, centerOn.getPosY() - this.viewingArea.getHeight()/2, this.viewingArea.getWidth(), this.viewingArea.getHeight());
        super.render(g);
    }

}
