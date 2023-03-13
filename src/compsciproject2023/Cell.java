/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author rahmanimtiaz
 */
public class Cell {
    //Image wall = new Image("MazeCell.jpeg");
    Image Buttomwall = new Image("Buttomwall.png");
    Image floor = new Image("floor.png");
    Image leftwall = new Image("Leftwall.png");
    Image rightwall = new Image("Rightwall.png");
    Image Topwall1 = new Image("TopwallP1.png");
    Image Topwall2 = new Image("TopwallP2.png");
    Image upwall = new Image("Upwall.png");
    //Rectangle Cell;
    ImageView Cell;

    public Cell(int code, double width, double height) {
        if (code == 0) {
            Cell = new ImageView(Topwall2);
            //Cell.setFill(new ImagePattern(upwall));
        }
        if (code == 1) {
            Cell = new ImageView(floor);
            //Cell.setFill(new ImagePattern(floor));
        }
        if (code == 3) {
            Cell = new ImageView(Topwall2);
            //Cell.setFill(new ImagePattern(Topwall2));
        }
        if (code == 4) {
            Cell = new ImageView(leftwall);
            //Cell.setFill(new ImagePattern(leftwall));
        }
        if (code == 5) {
            Cell = new ImageView(rightwall);
            //Cell.setFill(new ImagePattern(rightwall));
        }
       
        Cell.setFitHeight(height);
        Cell.setFitWidth(width);
    }
   
   
   
   
}