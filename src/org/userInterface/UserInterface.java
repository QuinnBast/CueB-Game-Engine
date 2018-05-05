package org.userInterface;

import org.userInterface.window.Window;

/**
 * Created by Quinn on 5/4/2018.
 */
public class UserInterface {

    public static final Window window = new Window();
    //private static ModalController modalController = new ModalController();

    public UserInterface(){
        window.validate();
        window.repaint();
    }

    public Window getWindow(){
        return window;
    }

}
