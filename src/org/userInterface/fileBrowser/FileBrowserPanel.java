package org.userInterface.fileBrowser;

import org.applicationEngine.objects.Base.Sprite;
import org.applicationEngine.objects.Room;
import org.developmentEngine.resourceManager.ResourceManager;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Quinn on 5/4/2018.
 */
public class FileBrowserPanel extends JPanel {

    private HashMap<String, Resource> resources = new HashMap<>();

    public FileBrowserPanel(){
        this.setSize(new Dimension(200, 1000));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setVisible(true);
    }

    public void addResource(Resource resource){
        this.add(resource); //Add the object to display on the gui
        this.resources.put(resource.getFilePath(), resource);
    }

    //Function to remove an item from the resource list.
    public void removeResource(Resource resource){
        if(this.resources.containsKey(resource.getFilePath())){
            this.remove(resource);
            this.resources.remove(resource.getFilePath());
        }
    }

    public Resource getResource(String filePath){
        if(this.resources.containsKey(filePath)){
            return this.resources.get(filePath);
        }
        return null;
    }

}
