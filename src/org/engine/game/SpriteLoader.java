package org.engine.game;

import org.engine.graphics.Renderer;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Quinn on 11/29/2017.
 */
public class SpriteLoader {

    public static SpriteLoader spriteLoader;

    protected SpriteLoader(){
    }

    public static SpriteLoader init(){
        if(spriteLoader == null){
            spriteLoader = new SpriteLoader();
        }
        return spriteLoader;
    }

    public static BufferedImage getImage(String path){

        BufferedImage image = null;
        try {
            image = Renderer.loadImage("/resources/images/" + path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(image != null){
            return image;
        } else {
            return null;
        }
    }
}
