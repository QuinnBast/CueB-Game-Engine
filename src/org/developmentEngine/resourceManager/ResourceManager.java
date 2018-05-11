package org.developmentEngine.resourceManager;

import org.developmentEngine.resourceManager.resourceTypes.Room;
import org.developmentEngine.resourceManager.resourceTypes.Script;
import org.developmentEngine.resourceManager.resourceTypes.Sprite;
import org.developmentEngine.resourceManager.resourceTypes.Object;

import java.util.ArrayList;

/**
 * Created by Quinn on 5/4/2018.
 */
public class ResourceManager {

    public ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
    public ArrayList<Room> roomList = new ArrayList<Room>();
    public ArrayList<Script> scriptList = new ArrayList<Script>();
    public ArrayList<Object> objectList = new ArrayList<Object>();

    public ResourceManager(){
        //On instantiation, make sure to load resources from the project files and add them into the collection.
    }
}
