package org.userInterface.window.centerScreen.resourceTabs.spriteTabs;

import net.miginfocom.swing.MigLayout;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.developmentEngine.resourceManager.resourceProperties.SpriteProperties;
import org.userInterface.window.centerScreen.resourceTabs.Tab;
import org.developmentEngine.resourceManager.Resources.Resource;
import org.developmentEngine.resourceManager.Resources.SpriteResource;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static org.userInterface.UserInterface.window;

/**
 * Created by Quinn on 5/11/2018.
 */
public class SpritePropertiesTab extends Tab  {

    JLabel filePathText;
    private SpriteProperties spriteProperties;
    private SpriteResource spriteResource;
    JLabel imageLabel = new JLabel();
    JTextField spriteNameText = new JTextField(20);
    JSpinner originX = new JSpinner();
    JSpinner originY = new JSpinner();
    JSpinner height = new JSpinner();
    JSpinner width = new JSpinner();

    public SpritePropertiesTab(Resource r){
        super(r);
        this.setLayout(new MigLayout("fill", "[][]"));
        r.getProperties().addPropertyObserver(this);
        this.spriteResource = (SpriteResource) r;
        this.spriteProperties = ((SpriteResource) r).getProperties();
        spriteNameText.setText(this.spriteResource.getFilePath());
        spriteNameText.getDocument().addDocumentListener(textListener);

        //Get the resource name.
        if(spriteProperties.getFilepaths().get(0) != null) {
            filePathText = new JLabel(spriteProperties.getFilepaths().get(0));
        } else {
            filePathText = new JLabel("No Image Selected.");
        }

        //Create the container for the left side of the interface
        JPanel spritePropertiesPanel = new JPanel(new MigLayout("fill", "[]"));
        spritePropertiesPanel.add(new JLabel("Name:"), "");
        spritePropertiesPanel.add(spriteNameText, "wrap, growx");
        spritePropertiesPanel.add(new JLabel("Image:"), "");

        JButton fileChooser = new JButton("Select Image");
        fileChooser.setVisible(true);
        fileChooser.addActionListener(al);

        spritePropertiesPanel.add(fileChooser,"wrap, growx");
        JPanel dimensionPanel = new JPanel(new MigLayout("fill", "[][]"));
        dimensionPanel.setBorder(BorderFactory.createTitledBorder("Dimensions"));

        SpinnerNumberModel originXModel;
        SpinnerNumberModel originYModel;
        SpinnerNumberModel heightModel;
        SpinnerNumberModel widthModel;

        if(spriteProperties.getFilepaths().get(0) != "") {
            originXModel = new SpinnerNumberModel(spriteProperties.getOrigin().getX(), 0,spriteProperties.getSize().getWidth(), 1);
            originYModel = new SpinnerNumberModel(spriteProperties.getOrigin().getY(), 0, spriteProperties.getSize().getHeight(), 1);
            widthModel = new SpinnerNumberModel(spriteProperties.getSize().getWidth(), 0, 9999, 1);
            heightModel = new SpinnerNumberModel(spriteProperties.getSize().getHeight(), 0, 9999, 1);
        } else {
            originXModel = new SpinnerNumberModel(0, 0, 0, 1);
            originYModel = new SpinnerNumberModel(0, 0, 0, 1);
            heightModel = new SpinnerNumberModel(0, 0, 9999, 1);
            widthModel = new SpinnerNumberModel(0, 0, 9999, 1);
        }

        originX.setModel(originXModel);
        originY.setModel(originYModel);
        width.setModel(widthModel);
        height.setModel(heightModel);

        originX.addChangeListener(changeListener);
        originY.addChangeListener(changeListener);
        width.addChangeListener(changeListener);
        height.addChangeListener(changeListener);

        dimensionPanel.add(new JLabel("Width:"), "");
        dimensionPanel.add(width, "wrap, growx");
        dimensionPanel.add(new JLabel("Height:"), "");
        dimensionPanel.add(height, "wrap, growx");

        spritePropertiesPanel.add(dimensionPanel,"wrap, span 2, growx");

        JPanel originPanel = new JPanel(new MigLayout("fill", "[][]"));
        originPanel.setBorder(BorderFactory.createTitledBorder("Origin"));
        originPanel.add(new JLabel("X Origin:"), "");
        originPanel.add(originX, "wrap, growx");
        originPanel.add(new JLabel("Y Origin:"), "");
        originPanel.add(originY, "wrap, growx");

        spritePropertiesPanel.add(originPanel, "wrap, span 2, growx");

        JPanel centerOriginPanel = new JPanel();
        JButton centerOriginButton = new JButton("Center Origin");
        centerOriginPanel.add(centerOriginButton);
        centerOriginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spriteProperties.setOrigin(new Point2D.Double(spriteProperties.getSize().getWidth()/2, spriteProperties.getSize().getHeight() / 2));
            }
        });

        //Display the image
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.PAGE_AXIS));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        SpritePropertyCanvas spc = new SpritePropertyCanvas(spriteProperties);
        imagePanel.add(spc, BorderLayout.CENTER);
        imagePanel.add(filePathText);
        imagePanel.setPreferredSize(new Dimension(500, 500));

        this.add(spritePropertiesPanel, "top, growx");
        this.add(imagePanel, "top growx");

        this.setVisible(true);
    }

    private void updateProperties(){
        spriteResource.getProperties().removePropertyObserver(this);

        spriteResource.setFilePath(spriteNameText.getText());
        if(originX.getValue() instanceof Double) {
            spriteProperties.setOrigin(new Point2D.Double((Double) originX.getValue(), (Double) originY.getValue()));
            spriteProperties.setSize(new Rectangle2D.Double(0, 0, (Double) width.getValue(), (Double) height.getValue()));
        }
        updateSpinners();

        spriteResource.getProperties().addPropertyObserver(this);
    }

    private void updateSpinners(){
        //Remove change listeners on the spinners until the update is over
        height.removeChangeListener(changeListener);
        width.removeChangeListener(changeListener);
        originY.removeChangeListener(changeListener);
        originX.removeChangeListener(changeListener);

        if(spriteProperties.getFilepaths().get(0) != "") {
            ImageIcon originalImage = spriteProperties.getImageIcon();

            ImageIcon imgIcon = new ImageIcon(originalImage.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH));
            imageLabel.setIcon(imgIcon);

            width.setModel(new SpinnerNumberModel(spriteProperties.getSize().getWidth(), 0, Integer.MAX_VALUE, 1));
            height.setModel(new SpinnerNumberModel(spriteProperties.getSize().getHeight(), 0, Integer.MAX_VALUE, 1));
            originX.setModel(new SpinnerNumberModel(spriteProperties.getOrigin().getX(), 0, spriteProperties.getSize().getWidth(), 1));
            originY.setModel(new SpinnerNumberModel(spriteProperties.getOrigin().getY(), 0, spriteProperties.getSize().getHeight(), 1));
        } else {
            originX.setModel(new SpinnerNumberModel(0, 0, 0, 1));
            originY.setModel(new SpinnerNumberModel(0, 0, 0, 1));
            height.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
            width.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        }

        //Add the change listeners back
        originX.addChangeListener(changeListener);
        originY.addChangeListener(changeListener);
        width.addChangeListener(changeListener);
        height.addChangeListener(changeListener);
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
                spriteProperties.setSize(new Rectangle2D.Double(0, 0, spriteProperties.getImageIcon().getIconWidth(), spriteProperties.getImageIcon().getIconHeight()));
                spriteProperties.setFilepath(fileChooser.getSelectedFile().getAbsolutePath());
                updateSpinners();
            }
        }
    };

    ChangeListener changeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            updateProperties();
        }
    };

    @Override
    public void onPropertyUpdate(ResourceProperties properties) {
        this.filePathText.setText(spriteProperties.getFilepaths().get(0));
        updateSpinners();
    }

    DocumentListener textListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateProperties();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateProperties();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateProperties();
        }
    };
}
