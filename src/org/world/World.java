package org.world;

import org.graphics.Camera;
import org.objects.Sprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Quinn on 11/26/2017.
 */
public class World {

    public static World currentWorld = null;
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    private static long lastTime = System.nanoTime();   //Last time we checked for an update.

    public static void render(Graphics g, Camera c){
        //Loop through all sprites and render them
        if(currentWorld.sprites != null) {
            for (Sprite sprite : currentWorld.sprites) {
                //for each sprite in the current world
                sprite.render(g, c);
            }
        }
    }

    public static void update(){

        float deltaTime = (System.nanoTime() - lastTime)/1000000000.0f;     //Puts the time since the last update in seconds
        lastTime = System.nanoTime();

        //Update all of the objects in the world and update them
        for(Sprite sprite : currentWorld.sprites){
            //for each sprite in the current world
            //Update the sprite using the amount of time that has elapsed since the last update as a parameter
            sprite.update(deltaTime);
        }
    }

}
