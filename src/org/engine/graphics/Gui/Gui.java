package org.engine.graphics.Gui;

import org.engine.objects.Base.Sprite;

import java.util.ArrayList;

/**
 * Created by Quinn on 4/29/2018.
 */
public class Gui {

    ArrayList<Sprite> guiComponents = new ArrayList<Sprite>();

    public Gui(ArrayList<Sprite> sprites){
        this.guiComponents = sprites;
    }

}