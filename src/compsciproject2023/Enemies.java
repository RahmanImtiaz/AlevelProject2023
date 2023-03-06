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
public class Enemies extends Sprite {
    int StrengthE, Health;
    
    public Enemies(int width, int height, String imgpath, int stength, int health) {
        super(width, height, imgpath);
        this.StrengthE = stength;
        this.Health = health;
    }
    
    
}
