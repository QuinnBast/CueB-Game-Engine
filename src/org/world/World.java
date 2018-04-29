package org.world;

import org.graphics.Camera;
import org.objects.Base.Entity;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Quinn on 11/26/2017.
 */
public class World {

    public static World currentWorld = null;
    public static ArrayList<Entity> objects = new ArrayList<Entity>();
    private static long lastTime = System.nanoTime();   //Last time we checked for an update.
    public static ArrayList<Camera> cameras = new ArrayList<Camera>();

    public static void render(Graphics g){
        //Loop through all camera views and render them
        if(cameras != null) {
            for(Camera c : cameras){
                c.render(g);
            }
        }
    }

    public static void update(){
        float deltaTime = (System.nanoTime() - lastTime)/1000000000.0f;     //Puts the time since the last update in seconds
        lastTime = System.nanoTime();
        for(Entity entity : objects){
            entity.update(deltaTime);
            for(Entity others : objects){
                if(others != entity){
                    //Check and resolve collisions.
                    entity.isColliidng(others, deltaTime);
                }
            }
        }
    }

    public static void addCamera(Camera c) {
        cameras.add(c);
    }

}
