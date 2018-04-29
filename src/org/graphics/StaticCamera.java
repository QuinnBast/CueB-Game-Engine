package org.graphics;

import org.world.Room;

/**
 * Created by Quinn on 4/27/2018.
 */
public class StaticCamera extends Camera {

    public StaticCamera(float centerX, float centerY, int width, int height) {
        this.viewingArea.setRect(centerX, centerY, width, height);
        this.displayArea = this.viewingArea;
    }

    public StaticCamera(Room room){
        this.viewingArea.setRect(room.getWidth()/2, room.getHeight()/2, room.getWidth(), room.getHeight()); //View the whole room
        this.displayArea.setRect(0,0, Renderer.getCanvasWidth(), Renderer.getCanvasHeight()); //Display to the whole screen
    }
}
