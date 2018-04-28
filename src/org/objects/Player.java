package org.objects;

import java.awt.image.BufferedImage;

/**
 * Created by Quinn on 11/29/2017.
 */
public class Player extends Entity {


    public Player(float posX, float posY, String image) {
        super(posX, posY, image, null, true, true, true);
    }

    @Override
    public void update(float deltaTime) {

    }
}
