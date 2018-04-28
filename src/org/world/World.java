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
    public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();
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
        for(Sprite s : sprites){
            s.update(deltaTime);
        }
    }

    public static void addCamera(Camera c) {
        cameras.add(c);
    }

}
