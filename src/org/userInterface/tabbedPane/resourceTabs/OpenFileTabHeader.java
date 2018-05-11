package org.userInterface.tabbedPane.resourceTabs;

import org.userInterface.UserInterface;
import org.userInterface.tabbedPane.OpenFileTabs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Quinn on 5/11/2018.
 */
public class OpenFileTabHeader extends JPanel {

    String fileName;
    int index;

    public OpenFileTabHeader(String tabName, int index){
        this.fileName = tabName;
        this.add(new JLabel(tabName));
        JButton closeButton = new JButton("X");
        closeButton.addActionListener(buttonClosedEvent);
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        this.add(closeButton);
    }

    ActionListener buttonClosedEvent = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            OpenFileTabs tabs = (OpenFileTabs) UserInterface.window.getLayers().getLayer("OpenFileTabs");
            tabs.removeTab(index, fileName);
        }
    };
}
