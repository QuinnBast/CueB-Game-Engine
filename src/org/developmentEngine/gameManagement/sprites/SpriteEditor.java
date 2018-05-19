package org.developmentEngine.gameManagement.sprites;

import org.applicationEngine.objects.Base.SpriteObject;

/**
 * Created by Quinn on 5/4/2018.
 */
public class SpriteEditor {

    private SpriteObject selectedSprite;

    public SpriteEditor(SpriteObject sprite){
        this.selectedSprite = sprite;
    }

    public SpriteObject getSelectedSprite(){
        return this.selectedSprite;
    }
}
