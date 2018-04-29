package org.game;

import org.graphics.*;
import org.input.PlayerControls;
import org.objects.Player;
import org.objects.Wall;
import org.world.World;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Quinn on 11/26/2017.
 */
public class Game {

    public static void main(String[] args) throws InterruptedException, IOException {
        World.currentWorld = new World();
        Renderer.init();                //Must be initialized first so it can load the graphics for the game.
        SpriteLoader.init();            //Loads all the required images


        Player player1 = new Player(100, 100, "Test.png");
        World.currentWorld.objects.add(player1);
        World.currentWorld.objects.add(new Wall(50, 50, 75, 20));
        ArrayList<Integer> keys = new ArrayList<>();
        keys.add(KeyEvent.VK_UP);
        keys.add(KeyEvent.VK_DOWN);
        keys.add(KeyEvent.VK_LEFT);
        keys.add(KeyEvent.VK_RIGHT);
        Player player2 = new Player(200, 200, "Test.png", new PlayerControls(keys));
        World.currentWorld.objects.add(player2);

        //Static Cameras in random locations
        //World.addCamera(new StaticCamera(50, 50, 100,100));
        //World.addCamera(new StaticCamera(200, 200, 400,100));

        //Centered Camera around player
        //World.addCamera(new CenteredCamera(200, 200, player1));

        //Center on Player Full Screen
        World.addCamera(new StaticCenteredCamera(player1, 200, 200));

        //Split Screen Camera
        //World.addCamera(new StaticCenteredCamera(Renderer.getCanvasWidth()/4, Renderer.getCanvasHeight()/2, Renderer.getCanvasWidth()/2, Renderer.getCanvasHeight(), player1, 200, 200));
        //World.addCamera(new StaticCenteredCamera(Renderer.getCanvasWidth()*3/4, Renderer.getCanvasHeight()/2, Renderer.getCanvasWidth()/2, Renderer.getCanvasHeight(), player2, 200, 200));

        //Centered Mini Map on Player bottom Right
        World.addCamera(new StaticCenteredCamera((int)(Renderer.getCanvasWidth()*0.95), (int)(Renderer.getCanvasHeight()*0.95), (int)(Renderer.getCanvasWidth()*0.1), (int)(Renderer.getCanvasWidth()*0.1), player1, 500, 500));

        //Static Mini Map Bottom Right
        //World.addCamera(new StaticCamera(100, 100, 500, 500, (int)(Renderer.getCanvasWidth()*0.95), (int)(Renderer.getCanvasHeight()*0.95), (int)(Renderer.getCanvasWidth()*0.1), (int)(Renderer.getCanvasHeight()*0.1)));
    }

    public static void quit(){
        System.exit(1);
    }
}
