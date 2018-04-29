package org.game;

import org.objects.Base.Entity;
import org.objects.Player;

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
