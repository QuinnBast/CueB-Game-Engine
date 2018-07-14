package org.userInterface.modals;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.Resources.RoomResource;
import org.developmentEngine.resourceManager.Resources.ScriptResource;
import org.developmentEngine.resourceManager.Resources.SpriteResource;
import org.userInterface.UserInterface;
import org.userInterface.modals.modals.*;
import org.userInterface.window.fileBrowser.FileBrowserPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class ModalFactory {

    ResourceModal rm;

    public ModalFactory(){

    }

    public void displayNewSpriteModal(){
        rm = new ResourceModal("Sprite", e -> {
            UserInterface.window.setEnabled(true);
            if(!rm.getFileName().isEmpty()){
                DevelopmentEngine.resourceManager.addResource(new SpriteResource(rm.getFileName()));
            }
            rm.dispose();
        });
    }

    public void displayNewScriptModal(){
        rm = new ResourceModal("Script", e -> {
            UserInterface.window.setEnabled(true);
            if(!rm.getFileName().isEmpty()){
                DevelopmentEngine.resourceManager.addResource(new ScriptResource(rm.getFileName()));
            }
            rm.dispose();
        });
    }

    public void displayNewRoomModal(){
        rm = new ResourceModal("Room", e -> {
            UserInterface.window.setEnabled(true);
            if(!rm.getFileName().isEmpty()){
                DevelopmentEngine.resourceManager.addResource(new RoomResource(rm.getFileName()));
            }
            rm.dispose();
        });
    }

    public void displayNewObjecteModal(){
        rm = new ResourceModal("Object", e -> {
            UserInterface.window.setEnabled(true);
            if(!rm.getFileName().isEmpty()){
                DevelopmentEngine.resourceManager.addResource(new ObjectResource(rm.getFileName()));
            }
            rm.dispose();
        });
    }

    public void displayNewObjectEventModal(){
        new ObjectEventModal();
    }

    public String displayFileChooserModal(FileNameExtensionFilter filter){
        return new FileChooserModal().fileChooser(filter);
    }

    public String displayDirectoryChooserModal(){
        return new FileChooserModal().directoryChooser();
    }



}
