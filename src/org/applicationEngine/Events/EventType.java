package org.applicationEngine.Events;

/**
 * Created by Quinn on 6/29/2018.
 */
public enum EventType {

    onCreate ("onCreate", "This event is fired when an object is created."),
    onMove ("onMove", "This event is fired when an object moves."),
    onUpdate ("onUpdate", "This event is fired every frame."),
    onCollision ("onCollision", "This event is fired when an object collides with another object."),
    onDestroy ("onDestroy", "This event is fired when an object is destroyed."),
    onAnimationEnd ("onAnimationEnd", "This event is fired when an object's animation ends."),
    onAnimationStart ("onAnimationStart", "This event is fired when an animation on an object begins."),
    onKeyPress ("onKeyPress", "This event is fired every frame while an observed key is held down."),
    onKeyDown ("onKeyDown", "This event is fired once when an observed key is pressed."),
    onKeyUp ("onKeyUp", "This event is fired once when an observed key is released."),
    onMouseUp ("onMouseUp", "This event is fired when the mouse button is released on top of the object."),
    onMouseDown ("onMouseDown", "This event is fired when the mouse button is pressed on top of the object."),
    onMouseEnter ("onMouseEnter", "This event is fired when the mouse enters the bounds of the object."),
    onMouseLeave ("onMouseLeave", "This event is fired when the mouse leaves the bounds of the object."),
    onDrawBegin ("onDrawBegin", "This event is fired before the object is drawn to the screen."),
    onDrawEnd ("onDrawEnd", "This event is fired after the object is drawn to the screen."),
    onRoomEnter ("onRoomEnter", "This event is fired when an object enters a room."),
    onRoomLeave ("onRoomLeave", "This event is fired when an object leaves a room."),
    onTimerStart ("onTimerStart", "This event is fired when a timer begins."),
    onTimerEnd ("onTimerEnd", "This event is fired when a timer ends."),
    onRoomUpdate ("onRoomUpdate", "This event is fired when anything within a room changes.");

    private final String strValue;
    private final String description;

    EventType(String str, String description){
        this.strValue = str;
        this.description = description;
    }

    public String toString(){
        return this.strValue;
    }
}
