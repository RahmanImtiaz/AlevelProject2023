/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import java.awt.image.BufferedImage;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author 10848
 */
public class Player extends Sprite {

    private int StrenghtP, Mp, Health;
    double xspeed, yspeed;
    public Image Up1, Up2, Down1, Down2, Left1, Left2, Right1, Right2;
    Circle radiuscircleP;
    boolean notmoving= false;
   
   

    //KeyHandler KeyH = new KeyHandler();
    public Player(int width, int height, String imgpath) {

        super(width, height, imgpath);
       
        Image img = draw(imgpath);
        setDefaultAttributes();
        getplayerimage();
        double h = Sprite.getFitHeight();
        double w = Sprite.getFitWidth();
        radiuscircleP = new Circle(getXSprite()+50,getYSprite()+70,40,Color.TRANSPARENT);

    }
   
    public void hit(){
        if (getCollision() == true) {
            //Health = Health - 10;
            setHealth(getHealth()-5);
            setCollision(false);
        }
       
        if (getHealth() == 0) {
            setAlive(false);
            System.out.println("Player died!");
        }
    }

    public void setDefaultAttributes() {
        this.Mp = 100;
        this.StrenghtP = 100;
        this.Health = 100;
        setYSprite(100);
        setXSprite(100);
        setXSpeed(4);
        setYSpeed(4);
        direction = "down";
       
        setCollision(false);
    }

    public void move(double dy, double dx, double width, double height) {
        if (!notmoving) {
            //xspeed = dx;
            //yspeed = dy;
        setYSprite(getYSprite() - yspeed);
        setXSprite(getXSprite() - xspeed);
        double h = Sprite.getFitHeight();
        double w = Sprite.getFitWidth();
        radiuscircleP.setCenterX(getXSprite()+50);
        radiuscircleP.setCenterY(getYSprite()+70);  
        }
       
        //Makes sure player does not go out of screen
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

    public void getplayerimage() {
        Down1 = new Image("Down1.png");
        Down2 = new Image("Down2.png");
        Up1 = new Image("Up1.png");
        Up2 = new Image("Up2.png");
        Left1 = new Image("Left1.png");
        Left2 = new Image("Left2.png");
        Right1 = new Image("Right1.png");
        Right2 = new Image("Right2.png");
    }

    public Image draw(String imgpath) {
        Image imgPlay = new Image(imgpath);
        return imgPlay;

    }

    public int getMp() {
        return Mp;
    }

    public int getHealth() {
        return Health;
    }

    public void setMp(int Mp) {
        this.Mp = Mp;
    }

    public void setHealth(int Health) {
        this.Health = Health;
    }

    public double getXspeed() {
        return xspeed;
    }

    public double getYspeed() {
        return yspeed;
    }

    public void setXspeed(double xspeed) {
        this.xspeed = xspeed;
    }

    public void setYspeed(double yspeed) {
        this.yspeed = yspeed;
    }
   
   
}

