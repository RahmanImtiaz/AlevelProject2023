/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 10848
 */
public class Sprite {

   
    public ImageView Sprite;
    Color color;
    private double XSprite, YSprite, XSpeed, YSpeed;
    private int Range;
    private Boolean Alive,  collision;
    public String direction;

 
    public Sprite(int width, int height, String imgpath) {
        Image imgSprite = new Image(imgpath);
        Sprite = new ImageView(imgSprite);
        this.Alive = true;
        Sprite.setX(XSprite);
        Sprite.setY(YSprite);
       
    }

    public double getXSprite() {
        return XSprite;
    }

    public void setXSprite(double XSprite) {
        this.XSprite = XSprite;
        Sprite.setX(XSprite);
    }

    public double getYSprite() {
        return YSprite;
    }

    public void setYSprite(double YSprite) {
        this.YSprite = YSprite;
        Sprite.setY(YSprite);
    }

    public double getXSpeed() {
        return XSpeed;
    }

    public void setXSpeed(double XSpeed) {
        this.XSpeed = XSpeed;
    }

    public double getYSpeed() {
        return YSpeed;
    }

    public void setYSpeed(double YSpeed) {
        this.YSpeed = YSpeed;
    }

    public int getRange() {
        return Range;
    }

    public void setRange(int Range) {
        this.Range = Range;
    }

    public boolean isAlive() {
        return Alive;
    }

    public void setAlive(boolean Alive) {
        this.Alive = Alive;
    }

    public Boolean getCollision() {
        return collision;
    }

    public void setCollision(Boolean collision) {
        this.collision = collision;
    }
   
   

}