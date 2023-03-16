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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    int roomnum;

    private boolean visited;

    public Room(double width, double height, Player p1/*, int roomNum*/) {
        //this.roomnum = roomNum;
        visited = false;
        int numC = (int) ((int) width / RectWidth);
        int numR = (int) ((int) height / RectHeight);

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
                if (row == numR / 2 && col != 0 && col != numC - 1) {// middle row is all floor, not doors 
                    codeimg = 1;
                }

                cells[row][col] = new Cell(codeimg, RectWidth, RectHeight);
                cells[row][col].Cell.setLayoutX(col * (RectWidth));
                cells[row][col].Cell.setLayoutY(row * (RectHeight));

            }
        }
    }

    private int getrandcode() {
        int num = rand.nextInt(100);
        if (num < 80) {
            return 1; //floor
        } else {
            return 3; //walls
        }
    }

    public void drawRoom(Group root) {
        root.getChildren().clear();
        for (Cell[] row:cells) {
            for (Cell cell:row) {
                root.getChildren().add(cell.Cell);
            }
        }
    }

    public void isAddedToMaze() {
        visited = true;
    }

    public boolean IsInTheMaze() {
        return visited;
    }

    public void makeDoor(Direction direction) {
        int numR = cells.length;
        int numC = cells[0].length;
        Image leftdoor = new Image("Doorleft.png");
        Image rightdoor = new Image("Doorright.png");
        Image downdoor = new Image("Doordown.png");
        Image updoor = new Image("Doorup.png");
        switch (direction) {
            case DOWN:
                cells[numR - 1][numC / 2].Cell = new ImageView(downdoor);
                cells[numR - 1][numC / 2].type = "DownDoor";
                break;
            case UP:
                cells[0][numC / 2].Cell = new ImageView(updoor);
                cells[numR - 1][numC / 2].type = "UpDoor";
                break;
            case LEFT:
                cells[numR / 2][0].Cell = new ImageView(leftdoor);
                cells[numR - 1][numC / 2].type = "LeftDoor";
                break;
            case RIGHT:
                cells[numR / 2][0].Cell = new ImageView(rightdoor);
                cells[numR - 1][numC / 2].type = "RightDoor";
                break;
        }
    }

    

    public void playerwallcollision(Player p1) {
        double minoverlapx = Double.POSITIVE_INFINITY;
        double minoverlapy = Double.POSITIVE_INFINITY;
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {

                if (cells[row][col].border == true) {

                    if (p1.radiuscircleP.getBoundsInParent().intersects(cells[row][col].Cell.getBoundsInParent())) {
                        //p1.notmoving = true;
                        System.out.println("interset walllllllllll");
                        p1.setXSprite(p1.getXSprite() + p1.xspeed);
                        p1.setYSprite(p1.getYSprite() + p1.yspeed);

                        //double overlapx;
                        //double overlapy;
                        ////Calculate overlap in x and y directions
                        //overlapx = Math.min((p1.radiuscircleP.getBoundsInParent()).getMaxX() - (cells[row][col].Cell.getBoundsInParent()).getMinX() , (cells[row][col].Cell.getBoundsInParent()).getMaxX() - (p1.radiuscircleP.getBoundsInParent()).getMinX());
                        //overlapy = Math.min((p1.radiuscircleP.getBoundsInParent()).getMaxY() - (cells[row][col].Cell.getBoundsInParent()).getMinY() , (cells[row][col].Cell.getBoundsInParent()).getMaxY() - (p1.radiuscircleP.getBoundsInParent()).getMinY());
                        //if (overlapx < overlapy) {
                        //double sign = Math.signum(p1.getXSpeed());
                        //p1.setXSprite(p1.getXSprite() - overlapx * sign);
                        //p1.setXSpeed(0);
                        //} else {
                        //double sign = Math.signum(p1.getYSpeed());
                        //p1.setXSprite(p1.getYSprite() - overlapy * sign);
                        //p1.setXSpeed(0);
                        //}
                        ////////////////////////////////////////////////////////////////////////
                        //if (overlapx<minoverlapx && overlapy<minoverlapy) {
                        ////store smallest overlap of wall
                        //minoverlapx = overlapx;
                        //minoverlapy = overlapy;
                        //}
                        //}
                        //if (cells[row][col].Cell != null) {
                        //double distx = 0;
                        //double disty = 0;
                        //if (Math.abs(minoverlapx-minoverlapy)<EPSILON) {
                        //distx = ((p1.radiuscircleP.getBoundsInParent()).getMaxX() - (cells[row][col].Cell.getBoundsInParent()).getMinX() < (cells[row][col].Cell.getBoundsInParent()).getMaxX() - (p1.radiuscircleP.getBoundsInParent()).getMinX()) ? -minoverlapx : minoverlapx;
                        //disty = ((p1.radiuscircleP.getBoundsInParent()).getMaxY() - (cells[row][col].Cell.getBoundsInParent()).getMinY() < (cells[row][col].Cell.getBoundsInParent()).getMaxY() - (p1.radiuscircleP.getBoundsInParent()).getMinY()) ? -minoverlapy : minoverlapy;
                        //} else if (minoverlapx< minoverlapy){
                        //distx = ((p1.radiuscircleP.getBoundsInParent()).getMaxX() - (cells[row][col].Cell.getBoundsInParent()).getMinX() < (cells[row][col].Cell.getBoundsInParent()).getMaxX() - (p1.radiuscircleP.getBoundsInParent()).getMinX()) ? -minoverlapx : minoverlapx;
                        //} else {
                        //disty = ((p1.radiuscircleP.getBoundsInParent()).getMaxY() - (cells[row][col].Cell.getBoundsInParent()).getMinY() < (cells[row][col].Cell.getBoundsInParent()).getMaxY() - (p1.radiuscircleP.getBoundsInParent()).getMinY()) ? -minoverlapy : minoverlapy;
                        //}
                        //p1.setXSprite(p1.getXSprite() + distx);
                        //p1.setYSprite(p1.getYSprite() + disty);
                    }
                }
            }
        }
    }
}
