package org.userInterface.modals.modals;

import org.developmentEngine.resourceManager.resourceTypes.Object;
import org.developmentEngine.resourceManager.resourceTypes.Sprite;
import org.userInterface.UserInterface;
import org.userInterface.fileBrowser.FileBrowserPanel;
import org.userInterface.fileBrowser.Resources.ObjectResource;
import org.userInterface.fileBrowser.Resources.SpriteResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class NewObjectModal extends JDialog implements ActionListener {

    JTextField fileName = new JTextField(20);

    public NewObjectModal(){
        this.setSize(new Dimension(500,200));
        this.setTitle("New Object");

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
            FileBrowserPanel panel = (FileBrowserPanel)(UserInterface.window.getLayers().getLayer("FileBrowser"));
            panel.addResource(new ObjectResource(fileName.getText(), new Object()));
        }
        dispose();
    }
}
