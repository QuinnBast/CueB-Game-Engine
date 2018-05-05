package org.userInterface.fileBrowser;

import org.applicationEngine.objects.Base.Sprite;
import org.applicationEngine.objects.ObjectType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/4/2018.
 */
public class Resource extends JPanel {

    private String filePath;
    private Sprite referencedObject;
    private JLabel label = new JLabel();

    public Resource(String path, ObjectType type){
        this.filePath = path;
        this.label.setText(this.filePath);
        this.add(label);
        this.setSize(new Dimension(300,20));
        this.setVisible(true);
        this.setBackground(Color.GREEN);
    }

    public String getFilePath(){
        return this.filePath;
    }

    public Sprite getReferencedObject(){
        return this.referencedObject;
    }

}
