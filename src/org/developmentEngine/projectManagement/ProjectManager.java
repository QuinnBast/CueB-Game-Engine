package org.developmentEngine.projectManagement;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.Resource;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.userInterface.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Quinn on 7/14/2018.
 */
public class ProjectManager {

    FileManager fileManager = new FileManager();

    public void newProject(){
        
    }

    public void loadProject(){

    }

    public void saveProject() throws IOException {
        for(Resource r : DevelopmentEngine.resourceManager.getAllResources()){
            //Remove all observers from the object's observers.
            ArrayList<PropertyObserver> observers = new ArrayList<>();
            observers = r.getProperties().getObservers();
            r.getProperties().removeObservers();
            fileManager.saveObject(r);
            r.getProperties().setPropertyObservers(observers);
        }
    }
}
