package org.applicationEngine.Events;

import java.util.ArrayList;

/**
 * Created by Quinn on 6/29/2018.
 */
public class EventListener {

    ArrayList<EventObserver> eventObservers = new ArrayList<>();

    public EventListener(){

    }

    public void addEventObserver(EventObserver observer){
        this.eventObservers.add(observer);
    }

    public void removeEventObserver(EventObserver observer){
        if(this.eventObservers.contains(observer)) {
            this.eventObservers.remove(observer);
        }
    }

    public void notifyEvent(Event e){
        for(EventObserver eo : eventObservers){
            if(eo.isWatchingSelf()) {
                if (e.getInvolvedObjects().contains(eo.getWatchedObject())) {
                    eo.notifyObject(e);
                }
            } else {
                if (e.getEventType() == eo.getWatchedEvent()) {
                    if (e.getInvolvedObjects().contains(eo.getWatchedObject())) {
                        eo.notifyObject(e);
                    }
                }
            }
        }
    }
}
