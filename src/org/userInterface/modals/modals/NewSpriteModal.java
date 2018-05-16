package org.userInterface.modals.modals;

import org.developmentEngine.DevelopmentEngine;
import org.userInterface.UserInterface;
import org.userInterface.fileBrowser.FileBrowserPanel;
import org.userInterface.fileBrowser.Resources.ScriptResource;
import org.userInterface.fileBrowser.Resources.SpriteResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class NewSpriteModal extends JDialog implements ActionListener {

    JTextField fileName = new JTextField(20);

    public NewSpriteModal(){
        this.setSize(new Dimension(500,200));
        this.setTitle("New Sprite");

        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setSize(new Dimension(200,200));
        panel.add(fileName);
        JButton button = new JButton("Ok");
        button.addActionListener(this);
        panel.add(button);
        this.add(panel);
        this.setVisible(true);
        UserInterface.window.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserInterface.window.setEnabled(true);
        if(!fileName.getText().isEmpty()){
            FileBrowserPanel panel = UserInterface.window.getLayers().getFileBrowser();
            DevelopmentEngine.resourceManager.addResource(new SpriteResource(fileName.getText()));
        }
        dispose();
    }
}
