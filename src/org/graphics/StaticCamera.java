package org.graphics;

import org.objects.Sprite;

import java.awt.*;

/**
 * Created by Quinn on 4/27/2018.
 */
public class StaticCamera extends Camera {

    public StaticCamera(float centerX, float centerY, int width, int height) {
        this.viewingArea.setRect(centerX, centerY, width, height);
        this.displayArea = this.viewingArea;
    }
}
