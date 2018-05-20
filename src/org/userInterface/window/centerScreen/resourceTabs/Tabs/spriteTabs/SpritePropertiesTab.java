package org.userInterface.window.centerScreen.resourceTabs.Tabs.spriteTabs;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
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

import static org.userInterface.UserInterface.window;

/**
 * Created by Quinn on 5/11/2018.
 */
public class SpritePropertiesTab extends Tab implements PropertyObserver {

    JLabel filePathText;

    public SpritePropertiesTab(Resource r){
        super(r);
        r.getProperties().addPropertyObserver(this);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        SpriteProperties properties = (SpriteProperties)((SpriteResource) r).getProperties();

        //Create the file name print-out and the file selector
        JPanel filePanel = new JPanel();
        if(properties.getFilepaths().get(0) != null) {
            filePathText = new JLabel(properties.getFilepaths().get(0));
        } else {
            filePathText = new JLabel("");
        }
        JButton fileChooser = new JButton("Select Image");
        fileChooser.setVisible(true);
        fileChooser.addActionListener(al);
        filePanel.add(filePathText);
        filePanel.add(fileChooser);

        //Create the number selected for the origin's x position
        JPanel originXPanel = new JPanel();
        SpinnerModel originxModel = new SpinnerNumberModel(properties.getOrigin().getX(), 0, properties.getSize().getWidth(), 1);
        JSpinner setOriginX = new JSpinner(originxModel);
        originXPanel.add(setOriginX);

        //Create the number selected for the origin's y position
        JPanel originYPanel = new JPanel();
        SpinnerModel originyModel = new SpinnerNumberModel(properties.getOrigin().getY(), 0, properties.getSize().getHeight(), 1);
        JSpinner setOriginY = new JSpinner(originyModel);
        originYPanel.add(setOriginY);

        this.add(filePanel);
        this.add(originXPanel);
        this.add(originYPanel);
        this.setVisible(true);
    }

    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg, .png, .gif, .tiff, .jpeg", "jpg", "gif", "png", "tiff", "jpeg");
            fileChooser.setFileFilter(filter);
            int returnOption = fileChooser.showOpenDialog(null);
            if(returnOption == JFileChooser.APPROVE_OPTION) {
                SpriteProperties spriteProperties = (SpriteProperties) window.getOpenFileResource().getProperties();
                spriteProperties.setFilepath(fileChooser.getSelectedFile().getAbsolutePath());
                window.repaint();
            }
        }
    };

    @Override
    public void onResourceUpdate() {
        SpriteProperties sp = (SpriteProperties)(this.getReferencedResource().getProperties());
        this.filePathText.setText(sp.getFilepaths().get(0));
    }
}
