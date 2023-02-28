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

    //KeyHandler KeyH = new KeyHandler();
    public Player(int width, int height, int speed, Color colour) {
        super(width, height, Color.WHITE);
        this.speed = speed;

        setDefaultAttributes();
        getplayerimage();
        draw();

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

    public void move(int dy, int dx) {

       
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

    public void draw() {
        Image image = null;
        switch (direction) {
            case "up":
                image = Up1;
                break;
            case "Down":
                image = Down1;
                break;
            case "left":
                image = Left1;
                break;
            case "right":
                image = Right1;
                break;
        }
    }
}
