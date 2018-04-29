package org.objects;

import org.input.userInput;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Created by Quinn on 11/29/2017.
 */
public class Player extends Entity {

    public Player(float posX, float posY, String image) {
        super(posX, posY, image, true, true, true);
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
        if(userInput.isPressed(KeyEvent.VK_W)) {
            posY -= 100 * deltaTime;
        }
        if(userInput.isPressed(KeyEvent.VK_S)) {
            posY += 100 * deltaTime;
        }
        if(userInput.isPressed(KeyEvent.VK_A)) {
            posX -= 100 * deltaTime;
        }
        if(userInput.isPressed(KeyEvent.VK_D)) {
            posX += 100 * deltaTime;
        }

        //Determine the angle of rotation towards the mouse
        this.angle = Math.atan((double) ((MouseInfo.getPointerInfo().getLocation().getY() - this.posY) / (MouseInfo.getPointerInfo().getLocation().getX() - this.posX)));
        if(MouseInfo.getPointerInfo().getLocation().getX() <= this.posX){
            this.angle = this.angle - Math.PI;
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
