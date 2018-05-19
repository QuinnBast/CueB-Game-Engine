package org.userInterface.window;

import org.userInterface.window.navBar.MenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class Window extends JFrame {

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(1900,1080);
    private WindowLayers layers = new WindowLayers();

    public Window(){
        this.setSize(OUTER_FRAME_DIMENSION);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(new MenuBar());
        this.setTitle("QB2D Engine");
        this.add(layers);
        this.setVisible(true);
        revalidate();
        repaint();
    }

    public WindowLayers getLayers(){
        return layers;
    }

}
