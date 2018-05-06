package org.userInterface.modals.modals;

import org.userInterface.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/4/2018.
 */
public class NewSpriteModal extends JDialog implements ActionListener {

    public NewSpriteModal(){
        this.setSize(new Dimension(500,200));
        this.setTitle("New Sprite");

        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setSize(new Dimension(200,200));
        panel.add(new JTextField(20));
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
        dispose();
    }
}
