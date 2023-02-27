/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;

/**
 *
 * @author 10848
 */
public class Player extends Sprite {

    private int StrenghtP, Mp, Health;

    KeyHandler KeyH = new KeyHandler();

    public Player(KeyHandler KeyH) {
        this.KeyH = KeyH;
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

    public void move() {
        if (KeyH.upPressed == true) {
            direction = "up";
            setYSprite(getYSprite() - getYSpeed());
        } else if (KeyH.downPressed == true) {
            direction = "down";
            setYSprite(getYSprite() + getYSpeed());
        } else if (KeyH.leftPressed == true) {
            direction = "left";
            setXSprite(getXSprite() - getXSpeed());
        } else if (KeyH.rightPressed == true) {
            direction = "right";
            setXSprite(getXSprite() + getXSpeed());
        }
    }

    public void getplayerimage() {
        Down1 = new Image("Down1.png");
        Down2 = new Image("Down2.png");
        Up1 = new Image("Up1.png");
        Up2 = new Image("Up2.png");
        Left1 = new Image("Left1.png");
        Left2 = new Image("Left2.png");
        Right1 = new Image("Right1");
        Right2 = new Image("Right2");
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
