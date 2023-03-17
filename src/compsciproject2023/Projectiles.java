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

        //Image imgProjectile = new Image("RedFireBall.png");
        //ivView = new ImageView(imgProjectile);
        //ivView.setLayoutY(y);
    }


    public ImageView getImageView() {
        return Sprite;
    }

    public void moveprojectile() {
        //y = y-dy;
        //ivView.setLayoutY(y);
        setXSprite(getXSprite() + dx);
        setYSprite(getYSprite() + dy);
        setXSpeed(dx);
        setYSpeed(dy);
        if (getYSprite() < 0 || getXSprite() < 0) {
            stillShooting = false;
        }
    }

    public Rectangle2D getBounds() {

        Bounds boundsInLocal = Sprite.getBoundsInLocal();
        Point2D topLeft = new Point2D(boundsInLocal.getMinX(), boundsInLocal.getMinY());
        Point2D bottomRight = new Point2D(boundsInLocal.getMaxX(), boundsInLocal.getMaxY());
        Point2D topLeftInScene = Sprite.localToScene(topLeft);
        Point2D bottomRightInScene = Sprite.localToScene(bottomRight);
        Sprite.getParent().requestLayout();
        return new Rectangle2D(topLeftInScene.getX(), topLeftInScene.getY(),
                bottomRightInScene.getX() - topLeftInScene.getX(),
                bottomRightInScene.getY() - topLeftInScene.getY());
    }

    public boolean checkcollision(Room room) {
        Collection<Rectangle2D> wallBounds = room.getwallbounds();
        for (Rectangle2D wall : wallBounds) {
            if (this.getBounds().intersects(wall)) {
                return true;
            }
        }
        return false;
    }
}
