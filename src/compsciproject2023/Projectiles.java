/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import java.util.Collection;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 10848
 */
public class Projectiles extends Sprite {

    public boolean stillShooting;
    double x, y, dy, dx;
    ImageView ivView;
    boolean alive;

    public Projectiles(double dy, double dx, String imgpath, double spritex, double spritey) {
        super(5, 5, imgpath);
        stillShooting = false;
        alive = true;
        this.dy = dy;
        this.dx = dx;
        this.setXSprite(spritex);
        this.setYSprite(spritey);
    }


    public ImageView getImageView() {
        return Sprite;
    }

    public void moveprojectile() {
        setXSprite(getXSprite() + dx);
        setYSprite(getYSprite() + dy);
        setXSpeed(dx);
        setYSpeed(dy);
        if (getYSprite() < 0 || getXSprite() < 0) {
            stillShooting = false;
        }
    }
}
