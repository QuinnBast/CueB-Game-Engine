package org.userInterface.fileBrowser;

import org.userInterface.fileBrowser.Resources.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by Quinn on 5/4/2018.
 */
public class FileBrowserPanel extends JPanel {

    //Map that references all objects the tree points to.
    private HashMap<String, Resource> resources = new HashMap<>();


    private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Resources");
    private DefaultMutableTreeNode spriteNode = new DefaultMutableTreeNode("Sprites");
    private DefaultMutableTreeNode scriptNode = new DefaultMutableTreeNode("Scripts");
    private DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode("Objects");
    private DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode("Rooms");


    public FileBrowserPanel(){
        this.setSize(new Dimension(200, 800));
        this.setVisible(true);

        constructTree(rootNode);

        JTree fileTree = new JTree(rootNode);
        JScrollPane scrollPane = new JScrollPane(fileTree);
        scrollPane.setPreferredSize(new Dimension(200, 800));
        this.add(scrollPane);
    }

    public void addResource(Resource resource){
        this.resources.put(resource.getFilePath(), resource);
        if(resource instanceof SpriteResource) {
            this.spriteNode.add(new DefaultMutableTreeNode(resource.getFilePath()));
        } else if(resource instanceof ObjectResource){
            this.objectNode.add(new DefaultMutableTreeNode(resource.getFilePath()));
        } else if (resource instanceof ScriptResource){
            this.scriptNode.add(new DefaultMutableTreeNode(resource.getFilePath()));
        } else if (resource instanceof RoomResource){
            this.roomNode.add(new DefaultMutableTreeNode(resource.getFilePath()));
        }
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

    private void constructTree(DefaultMutableTreeNode rootNode){
        rootNode.add(spriteNode);
        rootNode.add(scriptNode);
        rootNode.add(objectNode);
        rootNode.add(roomNode);

        //Check user project files and load into tree here.
    }

}