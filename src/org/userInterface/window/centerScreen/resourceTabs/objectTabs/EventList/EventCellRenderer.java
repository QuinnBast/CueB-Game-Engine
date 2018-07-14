package org.userInterface.window.centerScreen.resourceTabs.objectTabs.EventList;

import org.applicationEngine.Events.EventType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 7/11/2018.
 */
public class EventCellRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0, 128, 240);

    public EventCellRenderer(){

    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(isSelected){
            setBackground(HIGHLIGHT_COLOR);
        }

        EventType event = (EventType) value;
        JLabel textLabel = new JLabel(event.toString());
        this.add(textLabel);
        return this;
    }
}
