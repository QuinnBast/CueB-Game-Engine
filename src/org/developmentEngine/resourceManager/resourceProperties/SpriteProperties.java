package org.developmentEngine.resourceManager.resourceProperties;

import org.applicationEngine.graphics.Renderer;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
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
        if(this.size.contains(origin)) {
            this.origin = origin;
        }
        this.notifyUpdate(this);
    }

    public ArrayList<String> getFilepaths() {
        return filepaths;
    }

    public void setFilepath(String filepath) {
        this.filepaths.set(0, filepath);
        ImageIcon icon = this.getImageIcon();
        if(icon != null){
            this.boundingBox = new Rectangle2D.Double(0, 0, icon.getIconWidth(), icon.getIconHeight());
        }
        this.notifyUpdate(this);
    }

    public void addFilePath(String filepath){
        this.filepaths.add(filepath);
        this.notifyUpdate(this);
    }

    public void removeFilePath(String filepath){
        this.filepaths.remove(filepath);
        this.notifyUpdate(this);
    }

    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle2D boundingBox) {
        this.boundingBox = boundingBox;
        this.notifyUpdate(this);
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
        this.notifyUpdate(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifyUpdate(this);
    }

    public Rectangle2D getSize() {
        return size;
    }

    public void setSize(Rectangle2D.Double size) {
        //Scale the origin by any new sizes that have been set.
        double yOrigin = this.origin.getY();
        double xOrigin = this.origin.getX();
        double heightScale = this.size.getHeight() / size.getHeight();
        double widthScale = this.size.getWidth() / size.getWidth();
        this.origin = new Point2D.Double(xOrigin / widthScale, yOrigin / heightScale);
        this.size = size;
        this.boundingBox = new Rectangle2D.Double(0, 0, this.size.getWidth(), this.size.getHeight());
        this.notifyUpdate(this);
    }

    public boolean isAnimated() {
        return isAnimated;
    }

    public void setAnimated(boolean animated) {
        isAnimated = animated;
        this.notifyUpdate(this);
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
        this.notifyUpdate(this);
    }

    public SpriteProperties(){
        this.filepaths.add("");
    }

    public ImageIcon getImageIcon(){
        return new ImageIcon(this.filepaths.get(0));
    }

}
