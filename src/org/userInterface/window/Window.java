package org.userInterface.window;

import org.developmentEngine.resourceManager.Resources.Resource;
import org.userInterface.UserInterface;
import org.userInterface.window.navBar.MenuBar;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class Window extends JFrame {

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

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(750, 500));
        this.setJMenuBar(new MenuBar());
        this.setTitle("QB2D Engine");
        this.add(this.layers = new WindowLayers(), BorderLayout.CENTER);
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
