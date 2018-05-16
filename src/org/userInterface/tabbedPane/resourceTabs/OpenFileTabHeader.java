package org.userInterface.tabbedPane.resourceTabs;

import org.userInterface.UserInterface;
import org.userInterface.fileBrowser.Resources.Resource;
import org.userInterface.tabbedPane.OpenFileTabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/11/2018.
 */
public class OpenFileTabHeader extends JPanel {

    private Resource referencedResource;

    public OpenFileTabHeader(Resource resource){
        this.referencedResource = resource;
        this.add(new JLabel(resource.getFilePath()));
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
}
