package org.engine.game;

import org.engine.objects.Base.Entity;
import org.engine.objects.Player;

/**
 * Created by Quinn on 11/29/2017.
 */
public class EntityFactory {

    public EntityFactory(){

    }

    public Entity createEntity(String entityType, int x, int y, String image){
        if(entityType == null){
            return null;
        } else if(entityType.equalsIgnoreCase("PLAYER")){
            return new Player(x, y, image);
        }
        return null;
    }
}
