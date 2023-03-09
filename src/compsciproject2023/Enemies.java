/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author 10848
 */
public class Enemies extends Sprite {
    int StrengthE, Health;
    double speedE = 3;
    Circle radiuscircleE;
    
    
    public Enemies(int width, int height, String imgpath, int stength, int health, double x, double y, int Range) {
        super(width, height, imgpath);
        
        this.StrengthE = stength;
        this.Health = health;
        setRange(Range);
        setXSprite(x);
        setYSprite(y);
        radiuscircleE = new Circle(getXSprite(),getYSprite(),100,Color.TRANSPARENT);
        
    }
    
    private void Enemyattack(double playerx, double playery){
        double playerdistx = getXSprite()-playerx;
        double playerdisty = getYSprite()-playery;
        
        if ((playerdistx)*(playerdistx)<(getRange())*(getRange())) {
            
        }
    }

    public Boolean isdead() {
        if (getAlive()==false) {
            return true;
        }
        return false;
    }
    
    public void move() {
        Random change = new Random();
        for (int i = 0; i < change.nextInt(100)+50; i++) {
            setXSprite(getXSprite()+speedE);
            radiuscircleE.setCenterX(getXSprite());
        }
        for (int i = 0; i < change.nextInt(100)+50; i++) {
            setXSprite(getXSprite()-speedE);
            radiuscircleE.setCenterX(getXSprite());
        }
        for (int i = 0; i < change.nextInt(100)+50; i++) {
            setYSprite(getYSprite()+speedE);
            radiuscircleE.setCenterY(getYSprite());
        }
        for (int i = 0; i < change.nextInt(100)+50; i++) {
            setYSprite(getYSprite()-speedE);
            radiuscircleE.setCenterY(getYSprite());
        }
        
    }
    
}
