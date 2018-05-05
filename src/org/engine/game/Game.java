package org.engine.game;

import org.engine.graphics.*;
import org.engine.graphics.Cameras.Camera;
import org.engine.graphics.Cameras.MovingCenteredCamera;
import org.engine.graphics.Cameras.StaticCamera;
import org.engine.graphics.Cameras.StaticCenteredCamera;
import org.engine.graphics.Screens.Screen;
import org.engine.graphics.Screens.ScreenManager;
import org.engine.input.PlayerControls;
import org.engine.objects.Player;
import org.engine.objects.Wall;
import org.engine.world.World;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Quinn on 11/26/2017.
 */
public class Game {

    public Game(){
        World.currentWorld = new World();
        Renderer.init();                //Must be initialized first so it can load the graphics for the game.
        SpriteLoader.init();            //Loads all the required images
        ScreenManager.screenManager = new ScreenManager();


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
        ArrayList<Camera> staticScreenCameras = new ArrayList<Camera>();
        staticScreenCameras.add(new StaticCamera(50, 50, 100,100));
        staticScreenCameras.add(new StaticCamera(200, 200, 400,100));
        Screen staticCamScreen = new Screen("StaticCameras", staticScreenCameras, null);

        //Centered Camera around player
        ArrayList<Camera> playerCenteredCam = new ArrayList<Camera>();
        playerCenteredCam.add(new MovingCenteredCamera(200, 200, player1));
        Screen playerCenteredScreen = new Screen("PlayerCentered", playerCenteredCam, null);

        //Center on Player Full Screen
        ArrayList<Camera> fullPlayerCenter = new ArrayList<Camera>();
        fullPlayerCenter.add(new StaticCenteredCamera(player1, 200, 200));
        Screen fullPlayerCenteredScreen = new Screen("PlayerCenteredFull", fullPlayerCenter, null);

        //Split Screen Camera
        ArrayList<Camera> splitScreenCameras = new ArrayList<Camera>();
        splitScreenCameras.add(new StaticCenteredCamera(Renderer.getCanvasWidth()/4, Renderer.getCanvasHeight()/2, Renderer.getCanvasWidth()/2, Renderer.getCanvasHeight(), player1, 200, 200));
        splitScreenCameras.add(new StaticCenteredCamera(Renderer.getCanvasWidth()*3/4, Renderer.getCanvasHeight()/2, Renderer.getCanvasWidth()/2, Renderer.getCanvasHeight(), player2, 200, 200));
        Screen splitScreen = new Screen("SplitScreen", splitScreenCameras, null);

        //Centered Mini Map on Player bottom Right
        ArrayList<Camera> centeredMiniMapCameras = new ArrayList<Camera>();
        centeredMiniMapCameras.add(new StaticCenteredCamera((int)(Renderer.getCanvasWidth()*0.95), (int)(Renderer.getCanvasHeight()*0.95), (int)(Renderer.getCanvasWidth()*0.1), (int)(Renderer.getCanvasWidth()*0.1), player1, 500, 500));
        Screen miniMap = new Screen("CenteredMiniMap", centeredMiniMapCameras, null);

        //Static Mini Map Bottom Right
        ArrayList<Camera> staticMiniMapCameras = new ArrayList<Camera>();
        staticMiniMapCameras.add(new StaticCamera(100, 100, 500, 500, (int)(Renderer.getCanvasWidth()*0.95), (int)(Renderer.getCanvasHeight()*0.95), (int)(Renderer.getCanvasWidth()*0.1), (int)(Renderer.getCanvasHeight()*0.1)));
        Screen staticMiniMap = new Screen("StaticMiniMap", staticMiniMapCameras, null);

        ScreenManager.setActive("CenteredMiniMap");
    }

    public static void quit(){
        System.exit(1);
    }
}
