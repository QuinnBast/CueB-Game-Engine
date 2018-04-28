package org.graphics;

import org.objects.Sprite;

import java.awt.*;

/**
 * Created by Quinn on 4/27/2018.
 */
public class StaticCamera extends Camera {

    public StaticCamera(int width, int height, float x, float y) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public float getMaxX() {
        return (int)(this.x + this.width/2);
    }
    public float getMinX(){
        return (int)(this.x - this.width/2);
    }
    public float getMaxY(){
        return (int)(this.y + this.height/2);
    }
    public float getMinY(){
        return (int)(this.y - this.height/2);
    }
}
