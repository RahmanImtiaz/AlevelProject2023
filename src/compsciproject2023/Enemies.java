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
    double xspeedE = 3;
    double yspeedE = 3;
    Circle radiuscircleE;

    public Enemies(int width, int height, String imgpath, int stength, int health, double x, double y, int Range) {
        super(width, height, imgpath);

        this.StrengthE = stength;
        this.Health = health;
        setRange(Range);
        setXSprite(x);
        setYSprite(y);
        radiuscircleE = new Circle(getXSprite(), getYSprite(), 350, Color.TRANSPARENT);

    }

    private void Enemyattack(double playerx, double playery) {
        double playerdistx = getXSprite() - playerx;
        double playerdisty = getYSprite() - playery;

        if ((playerdistx) * (playerdistx) < (getRange()) * (getRange())) {

        }
    }

    public Boolean isdead() {
        if (getAlive() == false) {
            return true;
        }
        return false;
    }

    public void move() {
        Random change = new Random();
        
        int random = change.nextInt(5);

        if (random == 0 || random == 1) {
            yspeedE = -yspeedE;
        }
        if (random == 2) {
            yspeedE = yspeedE;
        }
        if (random == 3) {
            xspeedE = -xspeedE;
        }
        if (random == 4) {
             xspeedE = xspeedE;
        }
        
        
        for (int i = 0; i < change.nextInt(100) + 50; i++) {
            setXSprite(getXSprite() + xspeedE);
            radiuscircleE.setCenterX(getXSprite());
        }
       
        for (int i = 0; i < change.nextInt(100) + 50; i++) {
            setYSprite(getYSprite() + yspeedE);
            radiuscircleE.setCenterY(getYSprite());
        }
        

    }
    
    

}
