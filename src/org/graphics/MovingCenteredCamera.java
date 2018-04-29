package org.graphics;

import org.objects.Base.Sprite;

import java.awt.*;

/**
 * Created by Quinn on 4/27/2018.
 */
public class MovingCenteredCamera extends Camera {

    private Sprite centerOn;

    public MovingCenteredCamera(int width, int height, Sprite centerOn) {
        this.viewingArea.setRect(centerOn.getPosX() - width/2, centerOn.getPosY() - height/2, width, height);
        this.displayArea = this.viewingArea;
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
        this.displayArea = this.viewingArea;
        super.render(g);
    }
}
