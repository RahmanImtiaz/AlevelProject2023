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

    public void setDefaultAttributes() {
        this.Mp = 100;
        this.StrenghtP = 100;
        this.Health = 100;
        setYSprite(100);
        setXSprite(100);
        setXSpeed(4);
        setYSpeed(4);
    }

    public void move() {

        if (KeyH.upPressed == true) {
            setYSprite(getYSprite() - getYSpeed());
        } else if (KeyH.downPressed == true) {
            setYSprite(getYSprite() + getYSpeed());
        } else if (KeyH.leftPressed == true) {
            setXSprite(getXSprite() - getXSpeed());
        } else if (KeyH.rightPressed == true) {
            setXSprite(getXSprite() + getXSpeed());
        }

    }
    
    public void getplayerimage(){
      
    }

    public void draw() {

        
    }

}
