package org.objects;

import org.input.PlayerControls;
import org.input.userInput;
import org.objects.Base.Entity;

import java.awt.geom.Point2D;

/**
 * Created by Quinn on 11/29/2017.
 */
public class Player extends Entity {

    private PlayerControls controls;

    public Player(float posX, float posY, String image) {
        super(posX, posY, image, true, true, true, true);
        controls = new PlayerControls();
    }

    public Player(float posX, float posY, String image, PlayerControls controls) {
        super(posX, posY, image, true, true, true, true);
        this.controls = controls;
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if(userInput.isPressed(controls.getControls().get(0))) {
            posY -= 100 * deltaTime;
        }
        if(userInput.isPressed(controls.getControls().get(1))) {
            posY += 100 * deltaTime;
        }
        if(userInput.isPressed(controls.getControls().get(2))) {
            posX -= 100 * deltaTime;
        }
        if(userInput.isPressed(controls.getControls().get(3))) {
            posX += 100 * deltaTime;
        }
    }

    @Override
    public void collisionResolution(Entity object, float deltaTime) {
        if(object instanceof Wall){
            Point2D point = getCollisionPoint(object);
            if(this.boundingBox.getMinX() == point.getX()){
                //Left edge touched Wall's right edge. Move back right.
                posX += 100 * deltaTime;
            } else if (this.boundingBox.getMaxX() == point.getX()){
                //Right edge touched wall's left. Move back left.
                posX -= 100 * deltaTime;
            } else if (this.boundingBox.getMinY() == point.getY()){
                //Bottom edge touched wall's top. Move back up.
                posY += 100 * deltaTime;
            } else if (this.boundingBox.getMaxY() == point.getY()) {
                //Top edge touched wall's bottom. Move back down.
                posY -= 100 * deltaTime;
            }
        }
    }
}
