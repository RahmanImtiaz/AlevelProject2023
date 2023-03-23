/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

/**
 *
 * @author 10848
 */
public class Traps extends Sprite{
    int TrapDamage;
    public Traps(int width, int height, String imgpath, double x, double y, int damage) {
        super(width, height, imgpath);
        this.TrapDamage = damage;
        this.setXSprite(x);
        this.setYSprite(y);
    }
   
}
