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

/**
 *
 * @author 10848
 */
public class Player extends Sprite {

    private int StrenghtP, Mp, Health;
    double speed;
    public Image Up1, Up2, Down1, Down2, Left1, Left2, Right1, Right2;

    //KeyHandler KeyH = new KeyHandler();
    public Player(int width, int height, double speed, String imgpath) {

        super(width, height, imgpath);
        this.speed = speed;
        Image img = draw(imgpath);
        setDefaultAttributes();
        getplayerimage();

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
    }

    public void move(double dy, double dx) {
        setYSprite(getYSprite() - dy);
        setXSprite(getXSprite() - dx);
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
}
