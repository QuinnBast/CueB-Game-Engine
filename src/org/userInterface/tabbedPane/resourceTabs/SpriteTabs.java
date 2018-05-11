package org.userInterface.tabbedPane.resourceTabs;

import org.userInterface.tabbedPane.resourceTabs.spriteTabs.SpriteEditorTab;
import org.userInterface.tabbedPane.resourceTabs.spriteTabs.SpritePropertiesTab;

import javax.swing.*;

/**
 * Created by Quinn on 5/11/2018.
 */
public class SpriteTabs extends JTabbedPane {

    public SpriteTabs() {
        this.addTab("Properties", null, new SpritePropertiesTab());
        this.addTab("Sprite Editor", null, new SpriteEditorTab());
    }

}
