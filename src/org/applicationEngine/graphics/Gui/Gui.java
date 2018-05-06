package org.applicationEngine.graphics.Gui;

import org.applicationEngine.objects.Base.SpriteObject;

import java.util.ArrayList;

/**
 * Created by Quinn on 4/29/2018.
 */
public class Gui {

    ArrayList<SpriteObject> guiComponents = new ArrayList<SpriteObject>();

    public Gui(ArrayList<SpriteObject> sprites){
        this.guiComponents = sprites;
    }

}
