package org.applicationEngine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Quinn on 11/28/2017.
 */
public class userInput implements KeyListener {

    private static boolean[] pressedKeys = new boolean[200];

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = false;
    }

    public static boolean isPressed(int keyCode){
            return pressedKeys[keyCode];
    }
}
