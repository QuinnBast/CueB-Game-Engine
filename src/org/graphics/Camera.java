package org.graphics;

import org.objects.Sprite;

import java.awt.*;

/**
 * Created by Quinn on 11/29/2017.
 */
public class Camera {

    public static Sprite centerOn;
    private int width;
    private int height;

    public  Camera(int width, int height, Sprite centerOn){
        this.width = width;
        this.height = height;
        this.centerOn = centerOn;
    }

    public int getCamX(){
        return (int)centerOn.posX;
    }

    public int getCamY(){
        return (int)centerOn.posY;
    }

    public void setCenterOn(Sprite sprite){
        this.centerOn = sprite;
    }

    public void resize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void fixOnCamera(Graphics g){
        g.translate((int)this.centerOn.posX, (int)this.centerOn.posY);
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getMaxX(){
        return (int)(centerOn.posX + this.width/2);
    }
    public int getMinX(){
        return (int)(centerOn.posX - this.width/2);
    }
    public int getMaxY(){
        return (int)(centerOn.posY + this.height/2);
    }
    public int getMinY(){
        return (int)(centerOn.posY - this.height/2);
    }

    public Dimension getRelativePosition(Sprite objectRelative){
        int relativeX = (int)(centerOn.posX - objectRelative.posX);
        int relativeY = (int)(centerOn.posY - objectRelative.posY);
        return new Dimension(Renderer.getCanvasWidth()/2 + relativeX, Renderer.getCanvasHeight()/2 + relativeY);
    }

}
