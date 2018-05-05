package org.engine.input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Quinn on 4/28/2018.
 */
public class PlayerControls {

    private ArrayList<Integer> controls = new ArrayList<>();

    public PlayerControls(){
        controls.add(KeyEvent.VK_W);
        controls.add(KeyEvent.VK_S);
        controls.add(KeyEvent.VK_A);
        controls.add(KeyEvent.VK_D);
    }

    public PlayerControls(ArrayList<Integer> controls){
        this.controls = controls;
    }

    public ArrayList<Integer> getControls(){
        return this.controls;
    }
}
