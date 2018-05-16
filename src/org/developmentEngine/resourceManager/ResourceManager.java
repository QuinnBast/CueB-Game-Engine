package org.developmentEngine.resourceManager;

import org.userInterface.fileBrowser.Resources.*;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Quinn on 5/4/2018.
 */
public class ResourceManager {

    private ArrayList<SpriteResource> spriteList = new ArrayList<SpriteResource>();
    private ArrayList<RoomResource> roomList = new ArrayList<RoomResource>();
    private ArrayList<ScriptResource> scriptList = new ArrayList<ScriptResource>();
    private ArrayList<ObjectResource> objectList = new ArrayList<ObjectResource>();
    private CopyOnWriteArrayList<ResourceObserver> resourceObservers = new CopyOnWriteArrayList<>();


    public ResourceManager(){
        //On instantiation, make sure to load resources from the project files and add them into the collection.
    }

    public ArrayList<SpriteResource> getSpriteList(){
        return this.spriteList;
    }

    public ArrayList<RoomResource> getRoomList() {
        return roomList;
    }

    public ArrayList<ScriptResource> getScriptList() {
        return scriptList;
    }

    public ArrayList<ObjectResource> getObjectList() {
        return objectList;
    }

    public void addResource(Resource newItem){
        if(newItem instanceof ObjectResource){
            ObjectResource resource = (ObjectResource) newItem;
            this.objectList.add(resource);
        } else if (newItem instanceof SpriteResource){
            SpriteResource resource = (SpriteResource) newItem;
            this.spriteList.add(resource);
        } else if (newItem instanceof RoomResource){
            RoomResource resource = (RoomResource) newItem;
            this.roomList.add(resource);
        } else if (newItem instanceof ScriptResource){
            ScriptResource resource = (ScriptResource) newItem;
            this.scriptList.add(resource);
        }
        this.notifyObserversNewResource(newItem);
    }

    public void notifyObserversNewResource(Resource r){
        for(ResourceObserver ro : resourceObservers){
            ro.onResourceAdd(r);
        }
    }

    public void addResourceObserver(ResourceObserver ro){
        this.resourceObservers.add(ro);
    }

    public void removeResourceObserver(ResourceObserver ro){
        this.resourceObservers.remove(ro);
    }
}
