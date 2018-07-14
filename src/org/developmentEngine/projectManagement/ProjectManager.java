package org.developmentEngine.projectManagement;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.Resource;

import java.io.IOException;

/**
 * Created by Quinn on 7/14/2018.
 */
public class ProjectManager {

    FileManager fileManager = new FileManager();

    public void loadProject(){

    }

    public void saveProject() throws IOException {
        for(Resource r : DevelopmentEngine.resourceManager.getAllResources()){
            fileManager.saveObject(r);
        }
    }
}
