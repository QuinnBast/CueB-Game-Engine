package org.userInterface.window.fileBrowser;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.userInterface.UserInterface;
import org.developmentEngine.resourceManager.Resources.*;
import org.userInterface.window.centerScreen.OpenFileTabs;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * Created by Quinn on 5/4/2018.
 */
public class FileBrowserPanel extends JPanel implements ResourceObserver {

    //Map that references all objects the tree points to.
    private HashMap<String, Resource> resources = new HashMap<>();
    private HashMap<Resource, DefaultMutableTreeNode> linkedNodes = new HashMap<>();
    private JTree filetree;

    private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Resources");
    private DefaultMutableTreeNode spriteNode = new DefaultMutableTreeNode("Sprites");
    private DefaultMutableTreeNode scriptNode = new DefaultMutableTreeNode("Scripts");
    private DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode("Objects");
    private DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode("Rooms");


    public FileBrowserPanel() {
        this.setLayout(new BorderLayout());
        constructTree(rootNode);
        this.filetree = new JTree(rootNode);
        JScrollPane scrollPane = new JScrollPane(filetree);
        scrollPane.setMinimumSize(new Dimension(100, 300));
        scrollPane.setMaximumSize(new Dimension(150, Integer.MAX_VALUE));

        this.add(scrollPane, BorderLayout.CENTER);
        filetree.addMouseListener(ml);
        DevelopmentEngine.resourceManager.addResourceObserver(this);
    }

    public void addResource(Resource resource) {
        this.resources.put(resource.getFilePath(), resource);
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(resource.getFilePath());
        if (resource instanceof SpriteResource) {
            this.spriteNode.add(newNode);
            this.linkedNodes.put(resource, newNode);
        } else if (resource instanceof ObjectResource) {
            this.objectNode.add(newNode);
            this.linkedNodes.put(resource, newNode);
        } else if (resource instanceof ScriptResource) {
            this.scriptNode.add(newNode);
            this.linkedNodes.put(resource, newNode);
        } else if (resource instanceof RoomResource) {
            this.roomNode.add(newNode);
            this.linkedNodes.put(resource, newNode);
        }
        this.reloadTree(resource);
    }

    //Function to remove an item from the resource list.
    public void removeResource(Resource resource) {
        if (this.resources.containsKey(resource.getFilePath())) {
            DefaultMutableTreeNode deleteMe = this.linkedNodes.get(resource);
            if (resource instanceof SpriteResource) {
                this.spriteNode.remove(deleteMe);
            } else if (resource instanceof ObjectResource) {
                this.objectNode.remove(deleteMe);
            } else if (resource instanceof ScriptResource) {
                this.scriptNode.remove(deleteMe);
            } else if (resource instanceof RoomResource) {
                this.roomNode.remove(deleteMe);
            }
            this.resources.remove(resource.getFilePath());
            this.linkedNodes.remove(resource);
            this.reloadTree(resource);
        }
    }

    private void reloadTree(Resource updatedResource){
        DefaultTreeModel dtm = (DefaultTreeModel) filetree.getModel();
        if (updatedResource instanceof SpriteResource) {
            dtm.reload(this.spriteNode);
        } else if (updatedResource instanceof ObjectResource) {
            dtm.reload(this.objectNode);
        } else if (updatedResource instanceof ScriptResource) {
            dtm.reload(this.scriptNode);
        } else if (updatedResource instanceof RoomResource) {
            dtm.reload(this.roomNode);
        }
    }

    public Resource getResource(String filePath) {
        if (this.resources.containsKey(filePath)) {
            return this.resources.get(filePath);
        }
        return null;
    }

    private void constructTree(DefaultMutableTreeNode rootNode) {
        rootNode.add(spriteNode);
        rootNode.add(scriptNode);
        rootNode.add(objectNode);
        rootNode.add(roomNode);

        //Check user project files and load into tree here.
    }

    public HashMap<String, Resource> getResources(){
        return this.resources;
    }

    MouseListener ml = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            //If a file is double clicked, we want to add it to the object viewer pane
            if(SwingUtilities.isRightMouseButton(e)){
                String fileName = filetree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent().toString();
                FileBrowserPanel fbt = UserInterface.window.getLayers().getFileBrowser();
                ContextMenu rightClick = new ContextMenu(fbt.getResource(fileName));
                rightClick.show(e.getComponent(), e.getX(), e.getY());
            } else if (e.getClickCount() == 2) {
                String fileName = filetree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent().toString();
                FileBrowserPanel fbt = UserInterface.window.getLayers().getFileBrowser();
                if(fbt.getResource(fileName) != null) {
                    if (fbt.getResource(fileName).getObjectType() != null) {
                        OpenFileTabs oft = UserInterface.window.getLayers().getOpenFileTabs();
                        oft.addNewTab(fbt.getResource(fileName));
                    }
                }
            }
        }
    };

    @Override
    public void onResourceAdd(Resource r) {
        this.addResource(r);
    }

    @Override
    public void onResourceRemove(Resource r) {
        this.removeResource(r);
    }

    @Override
    public void onResourceUpdate(Resource r) {
        //Dont care
    }

    public JTree getFiletree(){
        return this.filetree;
    }
}