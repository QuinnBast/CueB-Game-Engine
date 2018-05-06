package org.userInterface.modals;

import org.userInterface.UserInterface;
import org.userInterface.modals.modals.NewObjectModal;
import org.userInterface.modals.modals.NewRoomModal;
import org.userInterface.modals.modals.NewScriptModal;
import org.userInterface.modals.modals.NewSpriteModal;

import javax.swing.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class ModalFactory {

    public ModalFactory(){

    }

    public void displayNewSpriteModal(){
        NewSpriteModal nsm = new NewSpriteModal();
    }

    public void displayNewScriptModal(){
        NewScriptModal nsm = new NewScriptModal();
    }

    public void displayNewRoomModal(){
        NewRoomModal nsm = new NewRoomModal();
    }

    public void displayNewObjecteModal(){
        NewObjectModal nsm = new NewObjectModal();
    }



}
