/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

/**
 *
 * @author 10848
 */
public class Room extends GridPane {

    int[][] maze;
    int[][] mazeroom;
    Cell cells[][];
    Random rand = new Random();
    double RectWidth = 100;
    double RectHeight = 100;
    double EPSILON = 1.0e-6;

    public Room(double width, double height, Player p1) {

        int numC = (int) ((int) width / RectWidth);
        int numR = (int) ((int) height / RectHeight);
        //RectWidth = width;
        //RectHeight = height;
        maze = new int[][]{
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {4, 1, 1, 3, 1, 1, 1, 1, 1, 1, 5},
            {4, 1, 1, 3, 1, 1, 1, 3, 1, 1, 5},
            {4, 1, 1, 1, 1, 1, 1, 3, 1, 1, 5},
            {4, 1, 1, 1, 1, 1, 1, 3, 1, 1, 5},
            {4, 1, 1, 1, 1, 1, 1, 3, 1, 1, 5},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},};

        mazeroom = new int[numR][numC];
        cells = new Cell[mazeroom.length][mazeroom[0].length];
        for (int row = 0; row < numR; row++) {
            for (int col = 0; col < numC; col++) {
                int codeimg = -1;
                if (row == 0 || row == numR - 1 || col == 0 || col == numC - 1) {//outerboundries
                    codeimg = 0;
                } else {
                    int coderand = getrandcode();
                    codeimg = coderand;
                }

                if ((col == 0 && row == numR / 2)) {//left door
                    codeimg = 4;
                }
                if ((col == numC - 1 && row == numR / 2)) {//right door
                    codeimg = 5;
                }

                if ((row == numR / 2 || row == (numR / 2 + 1) || row == (numR / 2 - 1)) && (col == 1 || col == 2 || col == numC - 2 || col == numC - 3)) {
                    codeimg = 1;
                }

                cells[row][col] = new Cell(codeimg, RectWidth, RectHeight);
                cells[row][col].Cell.setLayoutX(col * (RectWidth));
                cells[row][col].Cell.setLayoutY(row * (RectHeight));

            }

        }

        //cells = new Cell[maze.length][maze[0].length];
        //Color tempColour = null;
        ////Image wall = new Image("MazeCell.jpeg");
        //for (int i = 0; i < maze.length; i++) {
        //for (int j = 0; j < maze[0].length; j++) {
        //int code = -1;
        //switch (maze[i][j]) {
        //case 0:
        //code = 0;
        //break;
        //case 1:
        //code = 1;
        //break;
        //case 3:
        //code = 3;
        //break;
        //case 4:
        //code = 4;
        //break;
        //case 5:
        //code = 5;
        //break;
        //}
        //cells[i][j] = new Cell(code, RectWidth, RectHeight);
        //cells[i][j].Cell.setLayoutX(j * (RectWidth));
        //cells[i][j].Cell.setLayoutY(i * (RectHeight));
        //}}
    }

    private int getrandcode() {
        int num = rand.nextInt(100);

        if (num < 80) {
            return 1; //floor
        } else {
            return 3; //walls
        }
    }

    // public Collection<Rectangle2D> getwallbounds() {
    //List<Rectangle2D> wallbounds = new ArrayList<>();
    //   for (Node node : this.getChildren()) {
    //}
    //return null;
    public void playerwallcollision(Player p1) {
        double minoverlapx = Double.POSITIVE_INFINITY;
        double minoverlapy = Double.POSITIVE_INFINITY;
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {

                if (cells[row][col].border == true) {
                    
                if (p1.radiuscircleP.getBoundsInParent().intersects(cells[row][col].Cell.getBoundsInParent())) {
                    //p1.notmoving = true;
                    System.out.println("interset walllllllllll");
                    double overlapx;
                    double overlapy;
                    //Calculate overlap in x and y directions
                    overlapx = Math.min((p1.radiuscircleP.getBoundsInParent()).getMaxX() - (cells[row][col].Cell.getBoundsInParent()).getMinX() , (cells[row][col].Cell.getBoundsInParent()).getMaxX() - (p1.radiuscircleP.getBoundsInParent()).getMinX());
                    overlapy = Math.min((p1.radiuscircleP.getBoundsInParent()).getMaxY() - (cells[row][col].Cell.getBoundsInParent()).getMinY() , (cells[row][col].Cell.getBoundsInParent()).getMaxY() - (p1.radiuscircleP.getBoundsInParent()).getMinY());
                    
                    if (overlapx<minoverlapx && overlapy<minoverlapy) {
                        //store smallest overlap of wall
                        minoverlapx = overlapx;
                        minoverlapy = overlapy;
                    }
                } 
                
                    if (cells[row][col].Cell != null) {
                        double distx = 0;
                        double disty = 0;
                        if (Math.abs(minoverlapx-minoverlapy)<EPSILON) {
                            distx = ((p1.radiuscircleP.getBoundsInParent()).getMaxX() - (cells[row][col].Cell.getBoundsInParent()).getMinX() < (cells[row][col].Cell.getBoundsInParent()).getMaxX() - (p1.radiuscircleP.getBoundsInParent()).getMinX()) ? -minoverlapx : minoverlapx;
                            disty = ((p1.radiuscircleP.getBoundsInParent()).getMaxY() - (cells[row][col].Cell.getBoundsInParent()).getMinY() < (cells[row][col].Cell.getBoundsInParent()).getMaxY() - (p1.radiuscircleP.getBoundsInParent()).getMinY()) ? -minoverlapy : minoverlapy;
                        
                        }
                    }
                
                }
            }
        }
    }

}
