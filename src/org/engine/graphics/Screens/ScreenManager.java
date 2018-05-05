package org.engine.graphics.Screens;

import java.util.ArrayList;

/**
 * Created by Quinn on 4/29/2018.
 */
public class ScreenManager {

    private static Screen activeScreen;
    private static ArrayList<Screen> screens = new ArrayList<>();
    public static ScreenManager screenManager;

    public ScreenManager(ArrayList<Screen> screens, Screen activeScreen){
        if(this.screenManager == null) {
            this.screenManager = this;
        }
        this.screens = screens;
        this.activeScreen = activeScreen;
        this.updateCameras();
    }

    public ScreenManager(ArrayList<Screen> screens, String activeScreen){
        if(this.screenManager == null) {
            this.screenManager = this;
        }
        this.screens = screens;
        this.setActive(activeScreen);
        this.updateCameras();
    }

    public ScreenManager(){
        if(this.screenManager == null) {
            this.screenManager = this;
        }
    }

    public static void setActive(String screenName){
        for(Screen s : screens){
            if(s.getScreenName().equals(screenName)){
                s.enableCameras();
                activeScreen = s;
            } else {
                s.disableCameras();
            }
        }
    }

    public static void setActive(Screen screen){
        for(Screen s : screens){
            if(s == screen){
                s.enableCameras();
                activeScreen = s;
            } else {
                s.disableCameras();
            }
        }
    }

    public static void updateCameras(){
        for(Screen s : screens){
            if(s == activeScreen){
                s.enableCameras();
            } else {
                s.disableCameras();
            }
        }
    }

    public static void addScreen(Screen s){
        screens.add(s);
    }
}
