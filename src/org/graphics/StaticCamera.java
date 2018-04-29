package org.graphics;

import org.objects.Sprite;

import java.awt.*;

/**
 * Created by Quinn on 4/27/2018.
 */
public class StaticCamera extends Camera {

    public StaticCamera(int width, int height, float centerX, float centerY) {
        this.viewingArea.setRect(centerX, centerY, width, height);
    }
}
