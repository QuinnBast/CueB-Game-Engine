package org.applicationEngine.Events;

import org.applicationEngine.objects.Base.Object;
import org.developmentEngine.resourceManager.Resources.Instance;

import java.util.ArrayList;

/**
 * Created by Quinn on 6/29/2018.
 */
public interface EventHandler {

    void onCreate();
    void onCollision(ArrayList<Object> involvedObjects);
    void onMove();
    void onUpdate();
    void onAnimationEnd();
    void onAnimationStart();
    void onKeyPressed();
    void onKeyDown();
    void onKeyUp();
    void onMouseUp();
    void onMouseDown();
    void onMouseEnter();
    void onMouseLeave();
    void onDestroy();
    void onDrawBegin();
    void onDrawEnd();
    void onRoomEnter();
    void onRoomLeave();
    void onTimerStart();
    void onTimerEnd();
    void onRoomUpdate();

}