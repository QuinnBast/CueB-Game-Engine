package org.applicationEngine.Events;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.applicationEngine.objects.Base.Object;

/**
 * Created by Quinn on 6/29/2018.
 */
public class EventObserver {

    private Object observingObject;
    private EventType watchType;
    private boolean isWatchingSelf;

    public EventObserver(Object objectResource){
        this.observingObject = objectResource;
        this.isWatchingSelf = false;
    }

    public EventObserver(Object objectResource, EventType watchedEvent){
        this.observingObject = objectResource;
        this.watchType = watchedEvent;
        this.isWatchingSelf = true;
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

    public boolean isWatchingSelf(){
        return this.isWatchingSelf;
    }

}
