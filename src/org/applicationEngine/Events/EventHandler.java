package org.applicationEngine.Events;

import org.applicationEngine.objects.Base.Object;
import org.developmentEngine.resourceManager.Resources.Instance;

import java.util.ArrayList;

/**
 * Created by Quinn on 6/29/2018.
 */
public abstract class EventHandler {

    public abstract void onCreate();
    public abstract void onCollision(ArrayList<Object> involvedObjects);
    public abstract void onMove();
    public abstract void onUpdate();
    public abstract void onAnimationEnd();
    public abstract void onAnimationStart();
    public abstract void onKeyPressed();
    public abstract void onKeyDown();
    public abstract void onKeyUp();
    public abstract void onMouseUp();
    public abstract void onMouseDown();
    public abstract void onMouseEnter();
    public abstract void onMouseLeave();
    public abstract void onDestroy();
    public abstract void onDrawBegin();
    public abstract void onDrawEnd();
    public abstract void onRoomEnter();
    public abstract void onRoomLeave();
    public abstract void onTimerStart();
    public abstract void onTimerEnd();
    public abstract void onRoomUpdate();

}