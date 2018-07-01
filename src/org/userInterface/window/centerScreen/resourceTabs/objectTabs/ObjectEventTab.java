package org.userInterface.window.centerScreen.resourceTabs.objectTabs;

import net.miginfocom.swing.MigLayout;
import org.applicationEngine.Events.EventType;
import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.userInterface.UserInterface;
import org.userInterface.window.centerScreen.resourceTabs.Tab;
import org.developmentEngine.resourceManager.Resources.Resource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by Quinn on 5/11/2018.
 */
public class ObjectEventTab extends Tab {

    ObjectProperties referencedProperties;
    ObjectResource referencedObject;
    DefaultListModel<JPanel> listModel = new DefaultListModel<>();
    HashMap<EventType, JPanel> enabledEvents = new HashMap<>();
    EventType activeEvent = null;

    public ObjectEventTab(Resource r) {
        super(r);
        this.referencedObject = (ObjectResource) r;
        this.referencedProperties = this.referencedObject.getProperties();
        this.referencedProperties.addPropertyObserver(this);

        JPanel eventPanel = new JPanel(new MigLayout("", "[][][]"));
        JList<JPanel> eventList = new JList<>();
        eventList.setLayoutOrientation(JList.VERTICAL);
        eventList.setModel(listModel);

        JScrollPane listScroller = new JScrollPane();
        listScroller.add(eventList);    //Add the eventList to the scroll pane

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> UserInterface.modalController.displayNewObjectEventModal());

        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePanelFromList(activeEvent);
            }
        });


        eventPanel.add(listScroller, "span 3, wrap");
        eventPanel.add(addButton, "");
        eventPanel.add(editButton, "");
        eventPanel.add(deleteButton, "");




        JPanel codePanel = new JPanel(new MigLayout("fill", "[]"));
        RSyntaxTextArea syntaxTextArea = new RSyntaxTextArea(20, 60);
        syntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        syntaxTextArea.setCodeFoldingEnabled(true);
        RTextScrollPane scrollPane = new RTextScrollPane(syntaxTextArea);
        codePanel.add(scrollPane);

        this.add(eventPanel, "growy");
        this.add(codePanel, "growx");
    }

    @Override
    public void onPropertyUpdate(ResourceProperties properties) {

    }

    private JPanel makePanel(String string){
        JPanel panel = new JPanel();
        JLabel label = new JLabel(string);
        panel.add(label);
        return panel;
    }

    private void addPanelToList(EventType eventType){
        if(!this.enabledEvents.containsKey(eventType)){
            JPanel panel = this.makePanel(eventType.toString());
            listModel.addElement(panel);
            enabledEvents.put(eventType, panel);
            this.activeEvent=eventType;
        }
    }

    private void removePanelFromList(EventType eventType){
        if(this.enabledEvents.containsKey(eventType)){
            listModel.removeElement(this.enabledEvents.get(eventType));
            this.enabledEvents.remove(eventType);
        }
    }

    private void setActivePanel(EventType eventType){
        this.activeEvent=eventType;
    }

}
