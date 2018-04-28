package org.graphics;

import org.objects.Sprite;

import java.awt.*;

/**
 * Created by Quinn on 4/27/2018.
 */
public class StaticCamera extends Camera {

    public StaticCamera(int width, int height, float centerX, float centerY) {
        this.width = width;
        this.height = height;
        this.x = centerX;
        this.y = centerY;
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
