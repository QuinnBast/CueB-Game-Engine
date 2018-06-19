package org.applicationEngine.world;

import org.applicationEngine.graphics.Cameras.StaticCamera;
import org.developmentEngine.resourceManager.resourceProperties.RoomProperties;

import java.util.ArrayList;

/**
 * Created by Quinn on 6/18/2018.
 */
public class RoomManager {

    private ArrayList<Room> roomList = new ArrayList<Room>();
    private Room activeRoom;

    public RoomManager(){

    }

    public void addRoom(Room room){
        this.roomList.add(room);
        //Ensure that a room is always the default
        if(this.activeRoom == null){
            this.activeRoom = room;
        }
        if(this.activeRoom.cameras.size() == 0){
            RoomProperties rp = this.activeRoom.getRoomProperties();
            this.activeRoom.cameras.add(new StaticCamera((int)rp.getSize().getWidth()/2, (int)rp.getSize().getHeight()/2, (int)rp.getSize().getWidth(), (int)rp.getSize().getHeight()));
        }
    }

    public void removeRoom(Room room){
        for(Room r : roomList){
            if(room == r){
                this.roomList.remove(room);
            }
        }
    }

    public void setActiveRoom(Room room){
        this.activeRoom = room;
    }

    public Room getActiveRoom(){
        return this.activeRoom;
    }

}
