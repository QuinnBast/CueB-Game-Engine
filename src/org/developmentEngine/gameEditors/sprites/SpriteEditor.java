package org.developmentEngine.gameEditors.sprites;

import org.applicationEngine.objects.Base.Sprite;

import java.util.ArrayList;

/**
 * Created by Quinn on 5/4/2018.
 */
public class SpriteEditor {

    private Sprite selectedSprite;

    public SpriteEditor(Sprite sprite){
        this.selectedSprite = sprite;
    }

    public Sprite getSelectedSprite(){
        return this.selectedSprite;
    }
}
