package org.userInterface.modals.modals;

import net.miginfocom.swing.MigLayout;
import org.applicationEngine.Events.EventType;
import org.userInterface.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Quinn on 6/30/2018.
 */
public class ObjectEventModal extends JDialog {

    public ObjectEventModal(){
        this.setTitle("Add Event");

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        this.setLayout(new MigLayout());
        JPanel creationPanel = new JPanel(new MigLayout("wrap 1", "[grow]"));
        creationPanel.setBorder(BorderFactory.createTitledBorder("Creation"));
        creationPanel.add(new JButton(EventType.onCreate.toString()), "growx");
        creationPanel.add(new JButton(EventType.onDestroy.toString()), "growx");
        creationPanel.add(new JButton(EventType.onRoomEnter.toString()), "growx");
        creationPanel.add(new JButton(EventType.onRoomLeave.toString()), "growx");

        JPanel updatePanel = new JPanel(new MigLayout("wrap 1", "[grow]"));
        updatePanel.setBorder(BorderFactory.createTitledBorder("Updates"));
        updatePanel.add(new JButton(EventType.onUpdate.toString()), "growx");
        updatePanel.add(new JButton(EventType.onDrawBegin.toString()), "growx");
        updatePanel.add(new JButton(EventType.onDrawEnd.toString()), "growx");
        updatePanel.add(new JButton(EventType.onMove.toString()), "growx");
        updatePanel.add(new JButton(EventType.onRoomUpdate.toString()), "growx");
        updatePanel.add(new JButton(EventType.onCollision.toString()), "growx");


        JPanel animationPanel = new JPanel(new MigLayout("wrap 1", "[]"));
        animationPanel.setBorder(BorderFactory.createTitledBorder("Animation"));
        animationPanel.add(new JButton(EventType.onAnimationStart.toString()), "growx");
        animationPanel.add(new JButton(EventType.onAnimationEnd.toString()), "growx");

        JPanel timerPanel = new JPanel(new MigLayout("wrap 1", "[]"));
        timerPanel.setBorder(BorderFactory.createTitledBorder("Timers"));
        timerPanel.add(new JButton(EventType.onTimerStart.toString()), "growx");
        timerPanel.add(new JButton(EventType.onTimerEnd.toString()), "growx");

        JPanel keyboardPanel = new JPanel(new MigLayout("wrap 1", "[]"));
        keyboardPanel.setBorder(BorderFactory.createTitledBorder("Keyboard/Mouse"));
        keyboardPanel.add(new JButton(EventType.onKeyDown.toString()), "growx");
        keyboardPanel.add(new JButton(EventType.onKeyPress.toString()), "growx");
        keyboardPanel.add(new JButton(EventType.onKeyUp.toString()), "growx");
        keyboardPanel.add(new JButton(EventType.onMouseDown.toString()), "growx");
        keyboardPanel.add(new JButton(EventType.onMouseEnter.toString()), "growx");
        keyboardPanel.add(new JButton(EventType.onMouseLeave.toString()), "growx");
        keyboardPanel.add(new JButton(EventType.onMouseUp.toString()), "growx");

        this.add(creationPanel);
        this.add(updatePanel);
        this.add(animationPanel, "wrap");
        this.add(timerPanel);
        this.add(keyboardPanel);

        this.addWindowListener(windowListener);
        this.setVisible(true);
        this.setMinimumSize(new Dimension(750, 500));
        UserInterface.window.setEnabled(false);
    }

    WindowListener windowListener = new WindowListener() {
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
    };

}
