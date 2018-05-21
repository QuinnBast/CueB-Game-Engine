package org.userInterface.window.centerScreen.resourceTabs.Tabs.spriteTabs;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;
import org.userInterface.UserInterface;
import org.userInterface.window.centerScreen.resourceTabs.SpriteTabs;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.Tab;
import org.userInterface.window.centerScreen.resourceTabs.Tabs.spriteTabs.SpritePropertyEditor.SpritePropertyCanvas;
import org.userInterface.window.fileBrowser.Resources.Resource;
import org.userInterface.window.fileBrowser.Resources.SpriteResource;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.userInterface.UserInterface.window;

/**
 * Created by Quinn on 5/11/2018.
 */
public class SpritePropertiesTab extends Tab implements PropertyObserver {

    JLabel filePathText;
    private SpriteProperties spriteProperties;
    JLabel imageLabel = new JLabel();
    JSpinner originX = new JSpinner();
    JSpinner originY = new JSpinner();
    JLabel imageWidthText = new JLabel("Width: 0px");
    JLabel imageHeightText = new JLabel("Height: 0px");

    public SpritePropertiesTab(Resource r){
        super(r);
        r.getProperties().addPropertyObserver(this);
        this.spriteProperties = (SpriteProperties)((SpriteResource) r).getProperties();

        //Create the file name print-out and the file selector
        JPanel filePanel = new JPanel();
        if(spriteProperties.getFilepaths().get(0) != null) {
            filePathText = new JLabel(spriteProperties.getFilepaths().get(0));
        } else {
            filePathText = new JLabel("");
        }
        JButton fileChooser = new JButton("Select Image");
        fileChooser.setVisible(true);
        fileChooser.addActionListener(al);
        filePanel.add(filePathText);
        filePanel.add(fileChooser);

        //Display the image
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        SpritePropertyCanvas spc = new SpritePropertyCanvas(spriteProperties);
        imagePanel.add(spc, BorderLayout.CENTER);
        imagePanel.setPreferredSize(new Dimension(350, 350));

        SpinnerNumberModel originXModel;
        SpinnerNumberModel originYModel;

        if(spriteProperties.getFilepaths().get(0) != "") {
            originXModel = new SpinnerNumberModel(spriteProperties.getOrigin().getX(), 0, spriteProperties.getImageIcon().getIconWidth(), 1);
            originYModel = new SpinnerNumberModel(spriteProperties.getOrigin().getY(), 0, spriteProperties.getImageIcon().getIconHeight(), 1);
        } else {
            originXModel = new SpinnerNumberModel(0, 0, 0, 1);
            originYModel = new SpinnerNumberModel(0, 0, 0, 1);
        }

        JPanel originXPanel = new JPanel();
        JLabel xoriginText = new JLabel("X Origin");
        originX.setModel(originXModel);
        originXPanel.add(xoriginText);
        originXPanel.add(originX);
        originXPanel.add(imageWidthText);
        originX.addChangeListener(originXChangeListener);

        JPanel originYPanel = new JPanel();
        JLabel yoriginText = new JLabel("Y Origin");
        originY.setModel(originYModel);
        originYPanel.add(yoriginText);
        originYPanel.add(originY);
        originYPanel.add(imageHeightText);
        originY.addChangeListener(originYChangeListener);

        JPanel centerOriginPanel = new JPanel();
        JButton centerOriginButton = new JButton("Center Origin");
        centerOriginPanel.add(centerOriginButton);
        centerOriginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spriteProperties.setOrigin(new Point2D.Double(spriteProperties.getImageIcon().getIconWidth()/2, spriteProperties.getImageIcon().getIconHeight() / 2));
            }
        });

        this.add(filePanel);
        this.add(imagePanel);
        this.add(originXPanel);
        this.add(originYPanel);
        this.add(centerOriginPanel);
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
            }
        }
    };

    ChangeListener originXChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            spriteProperties.setOrigin(new Point2D.Double((double)originX.getValue(), spriteProperties.getOrigin().getY()));
        }
    };

    ChangeListener originYChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            spriteProperties.setOrigin(new Point2D.Double(spriteProperties.getOrigin().getX(), (double)originY.getValue()));
        }
    };

    @Override
    public void onResourceUpdate() {
        this.filePathText.setText(spriteProperties.getFilepaths().get(0));

        if(spriteProperties.getFilepaths().get(0) != "") {
            ImageIcon originalImage = spriteProperties.getImageIcon();

            ImageIcon imgIcon = new ImageIcon(originalImage.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH));
            imageLabel.setIcon(imgIcon);

            originX.setModel(new SpinnerNumberModel(spriteProperties.getOrigin().getX(), 0, spriteProperties.getImageIcon().getIconWidth(), 1));
            originY.setModel(new SpinnerNumberModel(spriteProperties.getOrigin().getY(), 0, spriteProperties.getImageIcon().getIconHeight(), 1));
            imageWidthText.setText("Width: " + Integer.toString(spriteProperties.getImageIcon().getIconWidth()) + "px");
            imageHeightText.setText("Height: " + Integer.toString(spriteProperties.getImageIcon().getIconHeight()) + "px");
        } else {
            originX.setModel(new SpinnerNumberModel(0, 0, 0, 1));
            originY.setModel(new SpinnerNumberModel(0, 0, 0, 1));
            imageWidthText.setText("Width: 0px");
            imageHeightText.setText("Height: 0px");
        }

    }
}
