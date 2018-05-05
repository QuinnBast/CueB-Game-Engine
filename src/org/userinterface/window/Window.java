package org.userinterface.window;

import org.userinterface.menuBar.*;
import org.userinterface.menuBar.MenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class Window extends JFrame {

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(1000,1080);
    private static WindowLayers layers = new WindowLayers();

    public Window(){
        this.setSize(OUTER_FRAME_DIMENSION);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(new MenuBar());
        this.setTitle("QB2D Engine");
        this.add(layers);
        this.setVisible(true);
    }

    public static JLayeredPane getLayers(){
        return layers;
    }

    public static void updateWindow(){
        if(layers != null) {
            layers.updateLayers();
        }
    }

    public void updateMe(){
        layers.updateLayers();
    }

}
