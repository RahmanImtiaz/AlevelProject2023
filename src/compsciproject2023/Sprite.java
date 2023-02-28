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

    //public Rectangle rect;
    public ImageView Sprite;
    Color color;

    private int XSprite, YSprite, XSpeed, YSpeed, Range;
    private int Alive;
    
    public String direction;

   // public Sprite(int width, int height, Color color) {
//
    //    rect = new Rectangle(width, height, color);
    //    rect.setX(XSprite);
   //     rect.setY(YSprite);
//
    //    Image imgPlay = new Image("Down1.png");
    //    ImageView Player = new ImageView(imgPlay);
   // }

    public Sprite(int width, int height, String imgpath) {

        Image imgSprite = new Image("Down1.png");
        Sprite = new ImageView(imgSprite);
        Sprite.setX(XSprite);
        Sprite.setY(YSprite);
    }

    public int getXSprite() {
        return XSprite;
    }

    public void setXSprite(int XSprite) {
        this.XSprite = XSprite;
        //rect.setX(XSprite);
        Sprite.setX(XSprite);
    }

    public int getYSprite() {
        return YSprite;
    }

    public void setYSprite(int YSprite) {
        this.YSprite = YSprite;
        //rect.setY(YSprite);
        Sprite.setY(YSprite);
    }

    public int getXSpeed() {
        return XSpeed;
    }

    public void setXSpeed(int XSpeed) {
        this.XSpeed = XSpeed;
    }

    public int getYSpeed() {
        return YSpeed;
    }

    public void setYSpeed(int YSpeed) {
        this.YSpeed = YSpeed;
    }

    public int getRange() {
        return Range;
    }

    public void setRange(int Range) {
        this.Range = Range;
    }

    public int getAlive() {
        return Alive;
    }

    public void setAlive(int Alive) {
        this.Alive = Alive;
    }

}
