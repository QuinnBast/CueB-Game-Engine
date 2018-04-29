package org.graphics;

import org.world.Room;

/**
 * Created by Quinn on 4/27/2018.
 */
public class StaticCamera extends Camera {

    /**
     *
     * @param centerX
     * @param centerY
     * @param width
     * @param height
     */
    public StaticCamera(int centerX, int centerY, int width, int height) {
        this.roomLocation.setRect(centerX, centerY, width, height);
        this.screenLocation = this.roomLocation;
    }

    /**
     *
     * @param centerRoomx
     * @param centerRoomY
     * @param roomWidth
     * @param roomHeight
     * @param screenCenterX
     * @param screenCenterY
     * @param screenDisplayWidth
     * @param screenDisplayHeight
     */
    public StaticCamera(int centerRoomx, int centerRoomY, int roomWidth, int roomHeight, int screenCenterX, int screenCenterY, int screenDisplayWidth, int screenDisplayHeight) {
        this.roomLocation.setRect(centerRoomx - roomWidth/2, centerRoomY - roomHeight/2, roomWidth, roomHeight);
        this.screenLocation.setRect(screenCenterX - screenDisplayWidth/2, screenCenterY - screenDisplayHeight/2, screenDisplayWidth, screenDisplayHeight);
    }

    /**
     *
     * @param room
     */
    public StaticCamera(Room room){
        this.roomLocation.setRect(room.getWidth()/2, room.getHeight()/2, room.getWidth(), room.getHeight()); //View the whole room
        this.screenLocation.setRect(0,0, Renderer.getCanvasWidth(), Renderer.getCanvasHeight()); //Display to the whole screen
    }
}
