package org.graphics;

import org.objects.Sprite;

import java.awt.*;

/**
 * Created by Quinn on 4/27/2018.
 */
public class CenteredCamera extends Camera {

    private Sprite centerOn;

    public CenteredCamera(int width, int height, Sprite centerOn) {
        this.width = width;
        this.height = height;
        this.centerOn = centerOn;
    }

    public void setCenterOn(Sprite sprite){
        this.centerOn = sprite;
        this.x = centerOn.getPosX();
        this.y = (int)(centerOn.getPosY());
    }

    public Sprite getCenteredOn(){ return this.centerOn; }
    public float getMaxX() {
        return (int)(centerOn.getPosX() + this.width/2);
    }
    public float getMinX(){
        return (int)(centerOn.getPosX() - this.width/2);
    }
    public float getMaxY(){
        return (int)(centerOn.getPosY() + this.height/2);
    }
    public float getMinY(){
        return (int)(centerOn.getPosY() - this.height/2);
    }

    @Override
    public void render(Graphics g){
        //If the camera is a centered camera, ensure that the camera is centering on the object before rending.
        if(this instanceof CenteredCamera){
            CenteredCamera cc = (CenteredCamera) this;
            this.x = cc.getCenteredOn().getPosX();
            this.y = cc.getCenteredOn().getPosY();
        }
        super.render(g);
    }
}
