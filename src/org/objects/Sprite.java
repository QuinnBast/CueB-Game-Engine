package org.objects;

import org.game.SpriteLoader;
import org.graphics.Camera;
import org.graphics.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by Quinn on 11/26/2017.
 */
public class Sprite {
    public float posX = 0;
    public float posY = 0;
    private float screenX = -1;
    private float screenY = -1;
    public double angle = 0;

    public BufferedImage image = null;

    public Sprite(float posX, float posY, String image){
        this.posX = posX;
        this.posY = posY;
        this.image = SpriteLoader.getImage(image);
    }

    public void render(Graphics g, Camera c){
        if(image == null){
            return;
        }

        //if(this.angle != 0){
            AffineTransform tx = AffineTransform.getRotateInstance(Math.toDegrees(this.angle), this.screenX, this.screenY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

            Graphics2D g2d = (Graphics2D) g;
            // Drawing the rotated image at the required drawing locations
            g.setColor(Color.RED);
            g.drawString("A:" + angle, 100,100);
            g.drawString("mouseX:" + (MouseInfo.getPointerInfo().getLocation().getX()), 100,130);
            g.drawString("mouseY:" + (MouseInfo.getPointerInfo().getLocation().getY()), 100,160);
            g.drawString("posX:" + (this.screenX), 100,190);
            g.drawString("posY:" + (this.screenY), 100,210);
            g.drawLine((int)(this.screenX), (int)(this.screenY), (int)(MouseInfo.getPointerInfo().getLocation().getX()), (int)(MouseInfo.getPointerInfo().getLocation().getY()));

            //TheFuck?
            g.drawLine((int)(c.getRelativePosition(this).width), (int)(c.getRelativePosition(this).height), (int)(MouseInfo.getPointerInfo().getLocation().getX()), (int)(MouseInfo.getPointerInfo().getLocation().getY()));

            //g2d.drawImage(op.filter(image, null), c.getRelativePosition(this).width, c.getRelativePosition(this).height, image.getWidth(), image.getHeight(), null);
            g.drawImage(image, c.getRelativePosition(this).width, c.getRelativePosition(this).height, image.getWidth(), image.getHeight(), null);
        //} else {
//            g.drawImage(image, c.getRelativePosition(this).width, c.getRelativePosition(this).height, image.getWidth(), image.getHeight(), null);
//        }
    }

    public void update(float deltaTime){
        Point2D point = Renderer.getScreenPos(new Point2D.Float(this.posX, this.posY));
        if(point != null) {
            this.screenX = (float) point.getX();
            this.screenY = (float) point.getY();
        }
    }
}
