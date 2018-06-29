package org.developmentEngine.compiler.ObjectInterfaces;

import org.developmentEngine.resourceManager.Resources.Instance;

/**
 * Created by Quinn on 6/29/2018.
 */
public interface ObjectInterface {

    void onCreate();
    void onCollision(Instance collisionType);
    void onDraw();
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

}