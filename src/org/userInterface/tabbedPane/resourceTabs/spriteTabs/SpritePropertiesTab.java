package org.userInterface.tabbedPane.resourceTabs.spriteTabs;

import org.userInterface.UserInterface;
import org.userInterface.fileBrowser.Resources.SpriteResource;
import org.userInterface.tabbedPane.resourceTabs.SpriteTabs;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/11/2018.
 */
public class SpritePropertiesTab extends JComponent {

    public SpritePropertiesTab(){
        this.setLayout(new BorderLayout());
        JButton fileChooser = new JButton("Select Image");
        fileChooser.setVisible(true);
        fileChooser.addActionListener(al);
        this.add(fileChooser, BorderLayout.CENTER);
        this.setVisible(true);
    }

    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "gif", "png");
            fileChooser.setFileFilter(filter);
            int returnOption = fileChooser.showOpenDialog(null);
            if(returnOption == JFileChooser.APPROVE_OPTION) {
                SpriteTabs spt = (SpriteTabs)UserInterface.window.getLayers().getOpenFileTabs().getActiveTab();
                SpritePropertiesTab sptt = (SpritePropertiesTab) spt.getSelectedComponent();
                JLabel label = new JLabel(fileChooser.getSelectedFile().getAbsolutePath());
                label.setVisible(true);
                sptt.add(label, BorderLayout.SOUTH);
                sptt.revalidate();
            }
        }
    };
}
