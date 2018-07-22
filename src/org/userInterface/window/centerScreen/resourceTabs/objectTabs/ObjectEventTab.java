package org.userInterface.window.centerScreen.resourceTabs.objectTabs;

import net.miginfocom.swing.MigLayout;
import org.applicationEngine.Events.EventType;
import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.userInterface.UserInterface;
import org.userInterface.window.centerScreen.resourceTabs.Tab;
import org.developmentEngine.resourceManager.Resources.Resource;
import org.userInterface.window.centerScreen.resourceTabs.objectTabs.EventList.EventCellRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Quinn on 5/11/2018.
 */
public class ObjectEventTab extends Tab {

    ObjectProperties referencedProperties;
    ObjectResource referencedObject;
    HashMap<EventType, String> enabledEvents = new HashMap<EventType, String>();
    DefaultListModel<EventType> listModel = new DefaultListModel<>();
    EventType activeEvent = null;
    JComboBox eventCombo = new JComboBox();
    RSyntaxTextArea syntaxTextArea = new RSyntaxTextArea();

    public ObjectEventTab(Resource r) {
        super(r);


        this.setLayout(new BorderLayout());
        this.referencedObject = (ObjectResource) r;
        this.referencedProperties = this.referencedObject.getProperties();
        this.referencedProperties.addPropertyObserver(this);

        JPanel eventPanel = new JPanel(new MigLayout("", "[][]"));
        eventPanel.setMinimumSize(new Dimension(100, 100));

        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        JButton addButton = new JButton("Add");
        DefaultComboBoxModel eventModel = new DefaultComboBoxModel<EventType>(EventType.onCollision.getDeclaringClass().getEnumConstants());
        eventCombo.setEditable(true);
        eventCombo.setModel(eventModel);

        addButton.addActionListener(e -> addPanelToList((EventType) eventCombo.getSelectedItem()));
        deleteButton.addActionListener(e -> removePanelFromList(activeEvent));

        JList eventList = new JList(listModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.setLayoutOrientation(JList.VERTICAL);
        eventList.setFixedCellHeight(50);
        JScrollPane scrollPane = new JScrollPane(eventList);
        scrollPane.setMinimumSize(new Dimension(100, 300));

        eventPanel.add(scrollPane, "growx, growy, span 2, wrap");
        eventPanel.add(editButton, "growx");
        eventPanel.add(deleteButton, "growx, wrap");
        eventPanel.add(eventCombo, "growx");
        eventPanel.add(addButton, "growx");

        JPanel codePanel = new JPanel(new BorderLayout());
        syntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        syntaxTextArea.setCodeFoldingEnabled(true);
        RTextScrollPane codescrollPane = new RTextScrollPane(syntaxTextArea);
        codePanel.add(codescrollPane, BorderLayout.CENTER);

        syntaxTextArea.getDocument().addDocumentListener(documentListener);
        eventList.addListSelectionListener(listSelectionListener);

        this.add(eventPanel, BorderLayout.WEST);
        this.add(codePanel, BorderLayout.CENTER);
    }

    DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                DevelopmentEngine.projectManager.objectWriter.updateEvent(referencedObject, activeEvent, e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                DevelopmentEngine.projectManager.objectWriter.updateEvent(referencedObject, activeEvent, e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            try {
                DevelopmentEngine.projectManager.objectWriter.updateEvent(referencedObject, activeEvent, e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }

    };

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                activeEvent = (EventType)((JList)e.getSource()).getSelectedValue();
                try {
                    syntaxTextArea.setText(DevelopmentEngine.projectManager.objectWriter.getEvent(referencedObject, activeEvent));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        private void addPanelToList(EventType eventType) {
            if (!this.enabledEvents.containsKey(eventType)) {
                listModel.addElement(eventType);
                enabledEvents.put(eventType, "");
                this.activeEvent = eventType;
            }
        }

        private void removePanelFromList(EventType eventType) {
            if (this.enabledEvents.containsKey(eventType)) {
                listModel.removeElement(eventType);
                enabledEvents.remove(eventType);
                this.activeEvent = null;
            }
        }

        private void setActivePanel(EventType eventType) {
            this.activeEvent = eventType;
        }

    @Override
    public void onPropertyUpdate(ResourceProperties properties) {

    }
}