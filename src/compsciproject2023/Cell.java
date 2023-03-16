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
    Image leftdoor = new Image("Doorleft.png");
    Image rightdoor = new Image("Doorright.png");
    Image downdoor = new Image("Doordown.png");
    Image updoor = new Image("Doorup.png");
    //Rectangle Cell;
    ImageView Cell;
    boolean border;
    
    String type;

    public Cell(int code, double width, double height) {
        if (code == 0) {
            Cell = new ImageView(Topwall2);
            border = true;
            type = "Wall";
            //Cell.setFill(new ImagePattern(upwall));
        }
        if (code == 1) {
            Cell = new ImageView(floor);
            border = false;
            type = "Floor";
            //Cell.setFill(new ImagePattern(floor));
        }
        if (code == 3) {
            Cell = new ImageView(Topwall2);
            border = true;
            type = "Wall";
            //Cell.setFill(new ImagePattern(Topwall2));
        }
        if (code == 4) {
            Cell = new ImageView(leftdoor);
            border = false;
            type = "LeftDoor";
            //Cell.setFill(new ImagePattern(leftdoor));
        }
        if (code == 5) {
            Cell = new ImageView(rightdoor);
            border = false;
            type = "RightDoor";
            //Cell.setFill(new ImagePattern(rightdoor));
        }
        if (code == 6) {
            Cell = new ImageView(downdoor);
            border = false;
            type = "DownDoor";
            
        }
        if (code == 7) {
            Cell = new ImageView(updoor);
            border = false;
            type = "UpDoor";
        }
        
       
        Cell.setFitHeight(height);
        Cell.setFitWidth(width);
    }
   
   
   
   
}