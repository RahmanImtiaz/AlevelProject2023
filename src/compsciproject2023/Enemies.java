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
    
    public Enemies(int width, int height, String imgpath, int stength, int health, int x, int y, int Range) {
        super(width, height, imgpath);
        this.StrengthE = stength;
        this.Health = health;
        setRange(Range);
        setXSprite(x);
        setYSprite(y);
    }
    
    private void Enemyattack(int playerx, int playery){
        int playerdistx = getXSprite()-playerx;
        int playerdisty = getYSprite()-playery;
        
        if ((playerdistx)*(playerdistx)<(getRange())*(getRange())) {
            
        }
    }
}
