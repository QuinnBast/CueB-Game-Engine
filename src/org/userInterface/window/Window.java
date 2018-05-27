package org.userInterface.window;

import org.developmentEngine.resourceManager.Resources.Resource;
import org.userInterface.window.navBar.MenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class Window extends JFrame {

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(1900,1080);
    private WindowLayers layers;

    public Window(){
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        this.layers = new WindowLayers();

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

    public JComponent getOpenFileTab(){
        return this.getLayers().getOpenFileTabs().getActiveTab();
    }

    public Resource getOpenFileResource(){
        return this.getLayers().getOpenFileTabs().getActiveResource();
    }

}
