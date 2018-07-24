package org.applicationEngine.world;

import org.applicationEngine.graphics.Cameras.Camera;
import org.applicationEngine.graphics.Renderer;
import org.applicationEngine.input.userInput;
import org.applicationEngine.objects.Base.Object;
import org.developmentEngine.resourceManager.Resources.RoomResource;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Quinn on 11/26/2017.
 */
public class Room {

    private static ArrayList<Object> objects = new ArrayList<Object>();
    private static long lastTime = System.nanoTime();   //Last time we checked for an update.
    public static ArrayList<Camera> cameras = new ArrayList<Camera>();

    private RoomResource referencedRoom;

    public ArrayList<Object> getObjects(){
        return this.objects;
    }

    public void addObject(Object obj){
        this.objects.add(obj);
    }

    public Room(RoomResource room){
        this.referencedRoom = new RoomResource(room); //Copy constructor
    }

    public RoomProperties getRoomProperties(){
        return this.referencedRoom.getProperties();
    }

    public static void render(Graphics g){
        //Loop through all camera views and render them
        if(cameras.size() > 0) {
            for(Camera c : cameras){
                c.render(g);
            }
        }
    }

    public static void update(){
        if(userInput.isPressed(KeyEvent.VK_ESCAPE)){
            Renderer.stopGame();
        }
        float deltaTime = (System.nanoTime() - lastTime)/1000000000.0f;     //Puts the time since the last update in seconds
        lastTime = System.nanoTime();
        for(Object object : objects){
            object.update(deltaTime);
            for(Object others : objects){
                if(others != object){
                    //Check and resolve collisions.
                    object.isColliidng(others, deltaTime);
                }
            }
        }
    }

    public static void addCamera(Camera c) {
        cameras.add(c);
    }

}
