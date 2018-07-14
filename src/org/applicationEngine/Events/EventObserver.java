package org.applicationEngine.Events;

import org.applicationEngine.objects.Base.Object;

/**
 * Created by Quinn on 6/29/2018.
 */
public class EventObserver {

    Object observingObject;
    EventType watchType;

    EventObserver(Object objectResource, EventType watch){
        this.observingObject = objectResource;
        this.watchType = watch;
    }

    public EventType getWatchedEvent(){
        return this.watchType;
    }

    public void notifyObject(Event e){
        observingObject.notifyEvent(e);
    }

    public Object getWatchedObject(){
        return observingObject;
    }

}
