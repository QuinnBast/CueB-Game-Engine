package org.userInterface.window.centerScreen.resourceTabs;

import net.miginfocom.swing.MigLayout;
import org.developmentEngine.resourceManager.resourceProperties.PropertyObserver;
import org.developmentEngine.resourceManager.Resources.Resource;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quinn on 5/20/2018.
 */
public abstract class Tab extends JComponent implements PropertyObserver {

    private Resource referencedResource;

    public Tab(Resource r){
        this.setLayout(new MigLayout());
        this.referencedResource = r;
    }

    public Resource getReferencedResource(){
        return this.referencedResource;
    }
}
