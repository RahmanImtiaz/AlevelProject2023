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
public class Projectiles {
    public boolean stillShooting;
    int x, y, dy, dx;
    ImageView ivView;

    public Projectiles() {
        stillShooting = false;
        dy=10;
        
        Image imgProjectile = new Image("RedFireBall.png");
        ivView = new ImageView(imgProjectile);
        ivView.setLayoutY(y);
    }
    
    public ImageView getImageView(){
        return ivView;
    }
    
    public void moveprojec(){
        y = y-dy;
        ivView.setLayoutY(y);
        if (y<0) {
            stillShooting = false;
            
        }
    }
    
    public void shoot(int playerx, int playery){
        x = playerx;
        y = playery;
        ivView.setLayoutX(x);
        stillShooting = true;
    }
    
    

    
    
}
