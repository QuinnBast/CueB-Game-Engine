package org.userInterface.modals.modals;

import org.userInterface.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Quinn on 6/28/2018.
 */
public class ResourceModal extends JDialog {

    private JTextField fileName = new JTextField(20);

    public String getFileName(){
        return fileName.getText();
    }

    public ResourceModal(String resourceType, ActionListener actionListener){
        this.setSize(new Dimension(500,200));
        this.setTitle("New" + resourceType);

        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setSize(new Dimension(200,200));
        panel.add(fileName);
        JButton button = new JButton("Ok");
        button.addActionListener(actionListener);
        panel.add(button);
        this.add(panel);
        this.setVisible(true);
        UserInterface.window.setEnabled(false);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                UserInterface.window.setEnabled(true);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

}
