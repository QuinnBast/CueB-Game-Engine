package org.developmentEngine.resourceManager.resourceProperties;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Quinn on 5/19/2018.
 */
public class SpriteProperties extends ResourceProperties {

    private Point2D origin = new Point2D.Double(0, 0);
    private ArrayList<String> filepaths = new ArrayList<>();
    private Rectangle2D boundingBox = new Rectangle2D.Double(0, 0, 0, 0);
    private int transparency = 1;
    private String name = "";
    private Rectangle2D size = new Rectangle2D.Double(0, 0, 0, 0);
    private boolean isAnimated = false;
    private int imageIndex = 0;


    public Point2D getOrigin() {
        return origin;
    }

    public void setOrigin(Point2D origin) {
        this.origin = origin;
        this.notifyUpdate();
    }

    public ArrayList<String> getFilepaths() {
        return filepaths;
    }

    public void setFilepath(String filepath) {
        this.filepaths.set(0, filepath);
        this.notifyUpdate();
    }

    public void addFilePath(String filepath){
        this.filepaths.add(filepath);
        this.notifyUpdate();
    }

    public void removeFilePath(String filepath){
        this.filepaths.remove(filepath);
        this.notifyUpdate();
    }

    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle2D boundingBox) {
        this.boundingBox = boundingBox;
        this.notifyUpdate();
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
        this.notifyUpdate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifyUpdate();
    }

    public Rectangle2D getSize() {
        return size;
    }

    public void setSize(Rectangle2D size) {
        this.size = size;
        this.notifyUpdate();
    }

    public boolean isAnimated() {
        return isAnimated;
    }

    public void setAnimated(boolean animated) {
        isAnimated = animated;
        this.notifyUpdate();
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
        this.notifyUpdate();
    }

    public SpriteProperties(){
        this.filepaths.add("");
    }

}
