package org.engine.graphics.Screens;

import org.engine.graphics.Cameras.Camera;
import org.engine.graphics.Gui.Gui;

import java.util.ArrayList;

/**
 * Created by Quinn on 4/29/2018.
 */
public class Screen {

    private Gui gui;
    private ArrayList<Camera> screenCameras;
    private String screenName;

    public Screen(String screenName, ArrayList<Camera> cameras, Gui gui){
        this.screenName = screenName;
        this.gui = gui;
        this.screenCameras = cameras;
        ScreenManager.addScreen(this);
    }

    public void addCamera(Camera c){
        this.screenCameras.add(c);
    }

    public void removeCamera(Camera c){
        this.screenCameras.remove(c);
    }

    public Gui getGui() {
        return this.gui;
    }

    public String getScreenName(){
        return this.screenName;
    }

    public void setScreenName(String name){
        this.screenName = name;
    }

    public void disableCameras(){
        for(Camera c : screenCameras){
            c.setInactive();
        }
    }

    public void enableCameras(){
        for(Camera c : screenCameras){
            c.setActive();
        }
    }

}
