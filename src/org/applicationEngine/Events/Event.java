package org.applicationEngine.Events;

import org.applicationEngine.game.Game;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.ObjectResource;

import java.util.ArrayList;

/**
 * Created by Quinn on 6/29/2018.
 */
public abstract class Event {

    EventType eventType;
    ArrayList<ObjectResource> involvedObjects;

    public Event(EventType eventType){
        this.eventType = eventType;
        Game.eventListener.notifyEvent(this);
    }

    public Event(EventType eventType, ArrayList<ObjectResource> involvedObjects){
        this.eventType = eventType;
        this.involvedObjects = involvedObjects;
        Game.eventListener.notifyEvent(this);
    }

    public EventType getEventType(){
        return eventType;
    }

    public ArrayList<ObjectResource> getInvolvedObjects(){
        return this.involvedObjects;
    }

    public abstract void fire();

}
