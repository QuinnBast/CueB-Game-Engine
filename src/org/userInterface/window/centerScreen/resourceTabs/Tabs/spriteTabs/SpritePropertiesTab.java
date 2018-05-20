package org.userInterface.window.centerScreen.resourceTabs.Tabs.spriteTabs;

import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;
import org.userInterface.UserInterface;
import org.userInterface.window.centerScreen.resourceTabs.SpriteTabs;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.Tab;
import org.userInterface.window.fileBrowser.Resources.Resource;
import org.userInterface.window.fileBrowser.Resources.SpriteResource;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/11/2018.
 */
public class SpritePropertiesTab extends Tab {

    public SpritePropertiesTab(Resource r){
        super(r);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        SpriteProperties properties = (SpriteProperties)((SpriteResource) r).getProperties();

        //Create the file name print-out and the file selector
        JPanel filePanel = new JPanel();
        JLabel selectedFile;
        if(properties.getFilepaths().get(0) != null) {
            selectedFile = new JLabel(properties.getFilepaths().get(0));
        } else {
            selectedFile = new JLabel("");
        }
        JButton fileChooser = new JButton("Select Image");
        fileChooser.setVisible(true);
        fileChooser.addActionListener(al);
        filePanel.add(selectedFile);
        filePanel.add(fileChooser);

        //Create the number selected for the origin's x position
        SpinnerModel originxModel = new SpinnerNumberModel(properties.getOrigin().getX(), 0, properties.getSize().getWidth(), 1);
        JSpinner setOriginX = new JSpinner(originxModel);

        //Create the number selected for the origin's y position
        SpinnerModel originyModel = new SpinnerNumberModel(properties.getOrigin().getY(), 0, properties.getSize().getHeight(), 1);
        JSpinner setOriginY = new JSpinner(originyModel);

        this.add(filePanel);
        this.add(setOriginX);
        this.add(setOriginY);
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

                SpriteProperties spriteProperties = (SpriteProperties) UserInterface.window.getOpenFileResource().getProperties();
                spriteProperties.setFilepath(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
    };
}
