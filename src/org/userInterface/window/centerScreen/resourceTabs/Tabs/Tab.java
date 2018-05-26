package org.userInterface.window.centerScreen.resourceTabs.Tabs;

import org.developmentEngine.DevelopmentEngine;
import org.developmentEngine.resourceManager.ResourceObserver;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.userInterface.window.fileBrowser.Resources.Resource;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/20/2018.
 */
public abstract class Tab extends JComponent implements PropertyObserver {

    private Resource referencedResource;

    public Tab(Resource r){
        this.referencedResource = r;
        this.setLayout(new FlowLayout());
    }

    public Resource getReferencedResource(){
        return this.referencedResource;
    }
}
