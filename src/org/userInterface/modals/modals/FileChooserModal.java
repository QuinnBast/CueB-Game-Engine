package org.userInterface.modals.modals;

import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.geom.Rectangle2D;

import static org.userInterface.UserInterface.window;

/**
 * Created by Quinn on 7/14/2018.
 */
public class FileChooserModal {

    public FileChooserModal(){

    }

    public String fileChooser(FileNameExtensionFilter filter){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int returnOption = fileChooser.showOpenDialog(null);
        if(returnOption == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return "";
        }
    }

    public String directoryChooser(){
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnOption = fileChooser.showSaveDialog(null);
        if(returnOption == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return "";
        }
    }
}
