package org.applicationEngine.Events;

import org.applicationEngine.game.Game;
import org.applicationEngine.objects.Base.Object;

import java.util.ArrayList;

/**
 * Created by Quinn on 6/29/2018.
 */
public class Event {

    EventType eventType;
    ArrayList<Object> involvedObjects;

    public Event(EventType eventType){
        this.eventType = eventType;
        Game.eventListener.notifyEvent(this);
    }

    public Event(EventType eventType, ArrayList<Object> involvedObjects){
        this.eventType = eventType;
        this.involvedObjects = involvedObjects;
        Game.eventListener.notifyEvent(this);
    }

    public EventType getEventType(){
        return eventType;
    }

    public ArrayList<Object> getInvolvedObjects(){
        return this.involvedObjects;
    }

}
