package org.test;

import org.game.SpriteLoader;
import org.graphics.Renderer;
import org.input.userInput;
import org.objects.Entity;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by Quinn on 11/26/2017.
 */
public class TestSprite extends Entity {

    public TestSprite(float posX, float posY, String image) {
        super(posX, posY, image, null, true, true, true);
    }

    public void update(float deltaTime){

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

        this.angle = Math.atan((this.posY - (double)MouseInfo.getPointerInfo().getLocation().getY())/(this.posX - MouseInfo.getPointerInfo().getLocation().getX()));

    }

}
