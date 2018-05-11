package org.userInterface.fileBrowser;

import org.userInterface.UserInterface;
import org.userInterface.fileBrowser.Resources.*;
import org.userInterface.tabbedPane.OpenFileTabs;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * Created by Quinn on 5/4/2018.
 */
public class FileBrowserPanel extends JPanel {

    //Map that references all objects the tree points to.
    private HashMap<String, Resource> resources = new HashMap<>();


    JTree filetree;

    private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Resources");
    private DefaultMutableTreeNode spriteNode = new DefaultMutableTreeNode("Sprites");
    private DefaultMutableTreeNode scriptNode = new DefaultMutableTreeNode("Scripts");
    private DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode("Objects");
    private DefaultMutableTreeNode roomNode = new DefaultMutableTreeNode("Rooms");


    public FileBrowserPanel() {
        this.setSize(new Dimension(200, 800));
        this.setVisible(true);

        constructTree(rootNode);

        this.filetree = new JTree(rootNode);
        JScrollPane scrollPane = new JScrollPane(filetree);
        scrollPane.setPreferredSize(new Dimension(200, 800));
        this.add(scrollPane);
        filetree.addMouseListener(ml);
    }

    public void addResource(Resource resource) {
        this.resources.put(resource.getFilePath(), resource);
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(resource.getFilePath());
        DefaultTreeModel dtm = (DefaultTreeModel) filetree.getModel();
        if (resource instanceof SpriteResource) {
            this.spriteNode.add(newNode);
            dtm.reload(this.spriteNode);
        } else if (resource instanceof ObjectResource) {
            this.objectNode.add(newNode);
            dtm.reload(this.objectNode);
        } else if (resource instanceof ScriptResource) {
            this.scriptNode.add(newNode);
            dtm.reload(this.scriptNode);
        } else if (resource instanceof RoomResource) {
            this.roomNode.add(newNode);
            dtm.reload(this.roomNode);
        }
    }

    //Function to remove an item from the resource list.
    public void removeResource(Resource resource) {
        if (this.resources.containsKey(resource.getFilePath())) {
            this.remove(resource);
            this.resources.remove(resource.getFilePath());
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
            //If a file is double clicked, we want to add
            if (e.getClickCount() == 2) {
                String fileName = filetree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent().toString();
                FileBrowserPanel fbt = (FileBrowserPanel) UserInterface.window.getLayers().getLayer("FileBrowser");
                if(fbt.getResource(fileName).getObjectType() != null) {
                    OpenFileTabs oft = (OpenFileTabs) UserInterface.window.getLayers().getLayer("OpenFileTabs");
                    oft.addNewTab(fileName, fbt.getResource(fileName).getObjectType());
                }
            }
        }
    };

}