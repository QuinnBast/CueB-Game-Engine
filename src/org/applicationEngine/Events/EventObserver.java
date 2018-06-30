package org.applicationEngine.Events;

import org.developmentEngine.eventManagement.EventType;
import org.developmentEngine.resourceManager.Resources.ObjectResource;

/**
 * Created by Quinn on 6/29/2018.
 */
public class EventObserver {

    ObjectResource observingObject;
    EventType watchType;

    EventObserver(ObjectResource objectResource, EventType watch){
        this.observingObject = objectResource;
        this.watchType = watch;
    }

    public EventType getWatchedEvent(){
        return this.watchType;
    }

    public void notifyObject(Event e){
        observingObject.notifyEvent(e);
    }

    public ObjectResource getWatchedObject(){
        return observingObject;
    }

}
