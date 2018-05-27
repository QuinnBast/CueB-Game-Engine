package org.userInterface.window.centerScreen.resourceTabs;

import org.userInterface.window.centerScreen.resourceTabs.spriteTabs.SpriteEditorTab;
import org.userInterface.window.centerScreen.resourceTabs.spriteTabs.SpritePropertiesTab;
import org.developmentEngine.resourceManager.Resources.Resource;

import javax.swing.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class SpriteTabs extends JTabbedPane {

    public SpriteTabs(Resource r) {
        this.addTab("Properties", null, new SpritePropertiesTab(r));
        this.addTab("Sprite Editor", null, new SpriteEditorTab(r));
    }

}
