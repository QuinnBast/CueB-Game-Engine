package org.developmentEngine.gameManagement;

import org.applicationEngine.game.Game;
import org.applicationEngine.objects.Base.Object;
import org.applicationEngine.world.Room;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.Instance;
import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.Resources.RoomResource;
import org.developmentEngine.resourceManager.resourceProperties.InstanceProperties;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;

/**
 * Created by Quinn on 5/17/2018.
 */

//This class is used to handle object instantiation in the game engine.

public class InstantiationManager {

    public InstantiationManager(){
    }

    public void instantiate(){
        Game game = new Game();
        for (RoomResource room : DevelopmentEngine.resourceManager.getRoomList()){
            Room newRoom = new Room(room);
            game.roomManager.addRoom(newRoom);   //Create an instance of a game room from the development room instance.
            //Populate the objects in the room with their default initiali state.
            for(Instance obj : ((RoomProperties)room.getProperties()).getInstances()){
                newRoom.addObject(new Object(((InstanceProperties)obj.getProperties()).getObjectType()));
            }
        }
    }
}
