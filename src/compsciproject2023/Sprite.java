/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 10848
 */
public class Sprite {
    
    public Rectangle rect;
    Color color;
    
    private int XSprite, YSprite, XSpeed, YSpeed, Range;
    private int Alive;
    public Image Up1, Up2, Down1, Down2, Left1, Left2, Right1, Right2; 
    public String direction;
    

    public Sprite(int width, int height, Color color) {
        
        rect = new Rectangle(width,height,color);
        
    }

    
    
    public int getXSprite() {
        return XSprite;
    }

    public void setXSprite(int XSprite) {
        this.XSprite = XSprite;
    }

    public int getYSprite() {
        return YSprite;
    }

    public void setYSprite(int YSprite) {
        this.YSprite = YSprite;
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
