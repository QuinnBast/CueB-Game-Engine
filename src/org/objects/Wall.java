package org.objects;

import org.objects.Base.Entity;

/**
 * Created by Quinn on 4/27/2018.
 */
public class Wall extends Entity {

    public Wall(float posX, float posY, float width, float height) {
        super(posX, posY, width, height, true, false, false, false);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void collisionResolution(Entity object, float deltaTime) {

    }
}
