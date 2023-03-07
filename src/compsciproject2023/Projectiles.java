/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 *
 * @author 10848
 */
public class Projectiles extends Sprite{
    public boolean stillShooting;
    double x, y, dy, dx;
    ImageView ivView;
    
    
    public Projectiles(double dy, double dx, String imgpath, double spritex, double spritey) {
        super(5,5,imgpath);
        stillShooting = false;
        this.dy=dy;
        this.dx=dx;
        this.setXSprite(spritex);
        this.setYSprite(spritey);
       
        //Image imgProjectile = new Image("RedFireBall.png");
        //ivView = new ImageView(imgProjectile);
        //ivView.setLayoutY(y);
    }
   
    public ImageView getImageView(){
        return ivView;
    }
   
    public void moveprojectile(){
        //y = y-dy;
        //ivView.setLayoutY(y);
        setXSprite(getXSprite()+dx);
        setYSprite(getYSprite()+dy);
        if (getYSprite()<0 || getXSprite()<0) {
            stillShooting = false;  
        }
    }
   

   
   

   
   
}

