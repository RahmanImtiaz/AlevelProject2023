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
public class Chests extends Sprite{
    String Type;
    int Boost;
    
    public Chests(int width, int height, String imgpath, String Type, double x, double y, int boost) {
        super(width, height, imgpath);
        this.Type = Type;
        this.Boost = boost;
        this.setXSprite(x);
        this.setYSprite(y);
    }
    
}
