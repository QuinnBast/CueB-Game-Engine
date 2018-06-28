package org.userInterface.window.centerScreen.resourceTabs;

import org.developmentEngine.resourceManager.ResourceObserver;
import org.userInterface.UserInterface;
import org.developmentEngine.resourceManager.Resources.Resource;
import org.userInterface.window.centerScreen.OpenFileTabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/11/2018.
 */
public class OpenFileTabHeader extends JPanel implements ResourceObserver {

    private Resource referencedResource;
    private JLabel fileLabel;

    public OpenFileTabHeader(Resource resource){
        this.referencedResource = resource;
        this.referencedResource.addResourceObserver(this);
        fileLabel = new JLabel(resource.getFilePath());
        this.add(fileLabel);
        JButton closeButton = new JButton("X");
        closeButton.addActionListener(buttonClosedEvent);
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(closeButton);
    }

    ActionListener buttonClosedEvent = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OpenFileTabs tabs = UserInterface.window.getLayers().getOpenFileTabs();
            tabs.removeTab(referencedResource);
        }
    };

    @Override
    public void onResourceAdd(Resource r) {
        //Dont care
    }

    @Override
    public void onResourceRemove(Resource r) {
        //Don't care
    }

    @Override
    public void onResourceUpdate(Resource r) {
        //Update the filename if there is any updates
        fileLabel.setText(r.getFilePath());
    }
}
