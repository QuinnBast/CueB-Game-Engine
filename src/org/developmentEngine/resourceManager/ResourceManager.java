package org.developmentEngine.resourceManager;

import org.developmentEngine.resourceManager.Resources.*;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;

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
        this.notifyNewResource(newItem);
    }

    public void removeResource(Resource removeItem){
        if(removeItem instanceof ObjectResource){
            ObjectResource resource = (ObjectResource) removeItem;
            this.objectList.remove(resource);
        } else if (removeItem instanceof SpriteResource){
            SpriteResource resource = (SpriteResource) removeItem;
            this.spriteList.remove(resource);
        } else if (removeItem instanceof RoomResource){
            RoomResource resource = (RoomResource) removeItem;
            this.roomList.remove(resource);
        } else if (removeItem instanceof ScriptResource){
            ScriptResource resource = (ScriptResource) removeItem;
            this.scriptList.remove(resource);
        }
        this.notifyRemoveResource(removeItem);
    }

    public void notifyNewResource(Resource r){
        for(ResourceObserver ro : resourceObservers){
            ro.onResourceAdd(r);
        }
    }

    public void notifyRemoveResource(Resource r){
        for(ResourceObserver ro : resourceObservers){
            ro.onResourceRemove(r);
        }
    }

    public void addInstance(Instance instance, RoomResource room){
        if(this.roomList.contains(room)){
            ((RoomProperties)room.getProperties()).addInstance(instance);
        }
    }

    public void removeInstance(ObjectResource obj, RoomResource room){
        if(this.roomList.contains(room)){
            if(((RoomProperties)room.getProperties()).getInstances().contains(obj)){
                ((RoomProperties)room.getProperties()).getInstances().remove(obj);
            }
        }
    }

    public void addResourceObserver(ResourceObserver ro){
        this.resourceObservers.add(ro);
    }

    public void removeResourceObserver(ResourceObserver ro){
        this.resourceObservers.remove(ro);
    }

    public ArrayList<Resource> getAllResources(){
        ArrayList<Resource> collection = new ArrayList<>();
        collection.addAll(spriteList);
        collection.addAll(objectList);
        collection.addAll(roomList);
        collection.addAll(scriptList);
        return collection;
    }
}
