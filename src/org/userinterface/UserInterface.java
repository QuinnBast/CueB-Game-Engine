package org.userinterface;

import org.userinterface.menuBar.MenuBar;
import org.userinterface.window.Window;

import javax.swing.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class UserInterface {

    private static final Window window = new Window();
    //private static ModalController modalController = new ModalController();

    public UserInterface(){
        window.updateWindow();
    }

    public Window getWindow(){
        return window;
    }

}
