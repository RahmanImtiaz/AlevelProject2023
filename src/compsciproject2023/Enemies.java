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
    boolean up, down, right, left;

    public Enemies(int width, int height, String imgpath, int stength, int health, double x, double y, int Range) {
        super(width, height, imgpath);

        this.StrengthE = stength;
        this.Health = health;
        setRange(Range);
        setXSprite(x);
        setYSprite(y);
        radiuscircleE = new Circle(getXSprite()+50, getYSprite()+50, 350, Color.TRANSPARENT);

    }

    private void Enemyattack(double playerx, double playery) {
        double playerdistx = getXSprite() - playerx;
        double playerdisty = getYSprite() - playery;

        if ((playerdistx) * (playerdistx) < (getRange()) * (getRange())) {

        }
    }

    public Boolean isdead() {
        if (isAlive() == false) {
            return true;
        }
        return false;
    }

    public void move(double dx, double dy, double width, double height){
        setYSprite(getYSprite() - dy);
        setXSprite(getXSprite() - dx);
        radiuscircleE.setCenterX(getXSprite()+50);
        radiuscircleE.setCenterY(getYSprite()+50);
        setXSpeed(dx);
        setYSpeed(dy);
       
        //Makes sure enemies dont go out of screen
        if (getXSprite()<0) {
            setXSprite(0);
        }
        if (getXSprite()>width-70) {
            setXSprite(width-70);
        }
        if (getYSprite()<0) {
            setYSprite(0);
        }
        if (getYSprite()>height-70) {
            setYSprite(height-70);
        }
    }
   
   
    public void setAction(){
   
        Random change = new Random();
        int i = change.nextInt(100)+1; //pick number from 1 to 100
        if (i<=25) {
            up = true;
        }
        if (i>25 && i<=50) {
            down = true;
        }
        if (i>50 && i<=75) {
            left = true;
        }
        if (i>75 && i<=100) {
            right = true;
        }
   
    }
   
    public void move2() {
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