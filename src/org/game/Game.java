package org.game;

import org.graphics.*;
import org.objects.Player;
import org.objects.Sprite;
import org.test.TestSprite;
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


        TestSprite player = new TestSprite(100, 100, "Test.png");
        World.currentWorld.sprites.add(player);
        World.addCamera(new StaticCamera(200, 200, 100,100));
    }

    public static void quit(){
        System.exit(1);
    }
}
