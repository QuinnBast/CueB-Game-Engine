package org.userInterface;

import org.userInterface.modals.ModalFactory;
import org.userInterface.window.Window;

import java.awt.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class UserInterface {

    public static final Window window = new Window();
    public static ModalFactory modalController = new ModalFactory();

    public UserInterface(){
        window.validate();
        window.repaint();
    }

    public Window getWindow(){
        return window;
    }

}
