package org.developmentEngine.gameManagement;

import org.applicationEngine.Events.EventHandler;
import org.applicationEngine.game.Game;
import org.applicationEngine.objects.Base.Object;
import org.applicationEngine.world.Room;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.Instance;
import org.developmentEngine.resourceManager.Resources.RoomResource;

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
            for(Instance inst : (room.getProperties()).getInstances()){
                Object obj = new Object((inst.getProperties()).getParentObject());
                obj.getObjectProperties().setPosition(inst.getProperties().getRoomLocation());
                newRoom.addObject(obj);
                //Compile eventHandlers for the object.
                //Get the UUID of the object.
                EventHandler handler = DevelopmentEngine.compiler.compileEventHandler(inst.getProperties().getParentObject().getUuid().toString().replace("-", ""));
                obj.setEventHandler(handler);
            }
        }
    }
}
