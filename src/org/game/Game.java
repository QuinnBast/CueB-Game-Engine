package org.game;

import org.graphics.*;
import org.objects.Player;
import org.objects.Wall;
import org.world.World;

import java.io.IOException;

/**
 * Created by Quinn on 11/26/2017.
 */
public class Game {

    public static void main(String[] args) throws InterruptedException, IOException {
        World.currentWorld = new World();
        Renderer.init();                //Must be initialized first so it can load the graphics for the game.
        SpriteLoader.init();            //Loads all the required images


        Player player = new Player(100, 100, "Test.png");
        World.currentWorld.objects.add(player);
        //World.addCamera(new StaticCamera(200, 200, 100,100));
        //World.addCamera(new StaticCamera(200, 200, 400,100));
        World.addCamera(new CenteredCamera(200, 200, player));
        World.currentWorld.objects.add(new Wall(50, 50, 75, 20));
    }

    public static void quit(){
        System.exit(1);
    }
}
