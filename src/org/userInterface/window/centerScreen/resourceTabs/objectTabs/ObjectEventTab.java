package org.userInterface.window.centerScreen.resourceTabs.objectTabs;

import org.developmentEngine.resourceManager.Resources.ObjectResource;
import org.developmentEngine.resourceManager.resourceProperties.ObjectProperties;
import org.developmentEngine.resourceManager.resourceProperties.ResourceProperties;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.userInterface.window.centerScreen.resourceTabs.Tab;
import org.developmentEngine.resourceManager.Resources.Resource;

import javax.swing.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class ObjectEventTab extends Tab {

    ObjectProperties referencedProperties;
    ObjectResource referencedObject;

    public ObjectEventTab(Resource r) {
        super(r);
        this.referencedObject = (ObjectResource) r;
        this.referencedProperties = this.referencedObject.getProperties();
        this.referencedProperties.addPropertyObserver(this);

        JPanel[] eventPanels = {};
        JList<JPanel> eventList = new JList<>();
        eventList.setLayoutOrientation(JList.VERTICAL);
        DefaultListModel<JPanel> listModel = new DefaultListModel<>();
        eventList.setModel(listModel);


        JScrollPane listScroller = new JScrollPane();
        listScroller.add(eventList);    //Add the eventList to the scroll pane



        JPanel eventPanel = new JPanel();
        RSyntaxTextArea syntaxTextArea = new RSyntaxTextArea(20, 60);
        syntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        syntaxTextArea.setCodeFoldingEnabled(true);
        RTextScrollPane scrollPane = new RTextScrollPane(syntaxTextArea);
        eventPanel.add(scrollPane);
        this.add(eventPanel);
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

    private void addPanelToList(String eventName){

    }

}
