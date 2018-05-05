package org.applicationEngine.fpsChecker;

/**
 * Created by Quinn on 11/28/2017.
 */
public class FPS {

    private static long lastCheck = 0;  //fps Check
    private static int currentFPS = 0;
    private static int totalFrames = 0;

    public static int getFPS() {
        totalFrames = totalFrames + 1;

        if (System.nanoTime() > (lastCheck + 1000000000)) {
            currentFPS = totalFrames;
            totalFrames = 0;
            lastCheck = System.nanoTime();
        }
        return currentFPS;
    }
}
