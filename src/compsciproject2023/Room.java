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
import javafx.geometry.Bounds;
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

    public Room(double width, double height, Player p1, int roomNum) {
        //this.roomnum = roomNum;
        this.visited = false;
        this.roomnum = roomNum;
        int numC = (int) ((int) width / RectWidth);
        int numR = (int) ((int) height / RectHeight);

        //mazeroom = new int[numR][numC];
        //cells = new Cell[mazeroom.length][mazeroom[0].length];
        cells = new Cell[numR][numC];
        for (int row = 0; row < numR; row++) {
            for (int col = 0; col < numC; col++) {
                int codeimg = -1;
                if (row == 0 || row == numR - 1 || col == 0 || col == numC - 1) {//outerboundries
                    codeimg = 0;
                } else {
                    int coderand = getrandcode();
                    codeimg = coderand;
                }

                //if ((col == 0 && row == numR / 2)) {//left door
                //    codeimg = 4;
                //}
                //if ((col == numC - 1 && row == numR / 2)) {//right door
                //    codeimg = 5;
                //}

                if ((row == numR / 2 || row == (numR / 2 + 1) || row == (numR / 2 - 1)) && (col == 1 || col == 2 || col == numC - 2 || col == numC - 3)) {
                    codeimg = 1;
                }
                if (row == numR / 2 && col != 0 && col != numC - 1) {// middle row is all floor, not doors
                    codeimg = 1;
                }
                
                if (col == numC / 2 && row != 0 && row != numR - 1) {// middle row is all floor, not doors (virtical)
                    codeimg = 1;
                }

                if ((row == 1 && col == 1) || (row == 1 && col == 2) || (row == 2 && col == 1) || (row == 2 && col == 2)) {
                    codeimg = 1;
                }
                Image downdoor = new Image("Doordown.png");
                

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

    public void drawRoom(Group root, Room maze) {
        root.getChildren().clear();
        for (Cell row[] : maze.cells) {
            for (Cell r : row) {
                root.getChildren().add(r.Cell);
            }
        }

        /*for (Cell[] row : cells) {
            for (Cell cell : row) {
                root.getChildren().add(cell.Cell);
            }
        }
         */
    }

    public void isAddedToMaze() {
        visited = true;
    }

    public boolean IsInTheMaze() {
        return visited;
    }

    public Collection<Rectangle2D> getwallbounds() {
        List<Rectangle2D> wallbounds = new ArrayList<>();
        int numR = cells.length;
        int numC = cells[0].length;
        for (int row = 0; row < numR; row++) {
            for (int col = 0; col < numC; col++) {
                if (cells[row][col].border == true) {
                    Bounds BoundsInScene = cells[row][col].Cell.localToScene(cells[row][col].Cell.getBoundsInLocal());

                    wallbounds.add(new Rectangle2D(BoundsInScene.getMinX(), BoundsInScene.getMinY(), BoundsInScene.getWidth(), BoundsInScene.getHeight()));
                }

            }
        }
        return wallbounds;
    }

    public String EnterDoor(Player p1) {//Find collision with door and check which direction door is
        for (int row = 0; row < this.cells.length; row++) {
            for (int col = 0; col < this.cells[0].length; col++) {
                if (this.cells[row][col].border) {
                    if (p1.radiuscircleP.getBoundsInParent().intersects(this.cells[row][col].Cell.getBoundsInParent())) {
                        if (cells[row][col].type == "DownDoor") {
                            return "Down";
                        }
                        if (cells[row][col].type == "UpDoor") {
                            return "Up";
                        }
                        if (cells[row][col].type == "LeftDoor") {
                            return "Left";
                        }
                        if (cells[row][col].type == "RightDoor") {
                            return "Right";
                        }
                    }
                }
            }
        }
        return null;
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

    public void playerwallcollision(Player p1) { //Player and wall collisions
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                if (cells[row][col].border == true) {
                    if (p1.radiuscircleP.getBoundsInParent().intersects(cells[row][col].Cell.getBoundsInParent())) {
                        p1.setXSprite(p1.getXSprite() + p1.xspeed);
                        p1.setYSprite(p1.getYSprite() + p1.yspeed);
                    }
                }
            }
        }
    }

    public void Spritewallcollision(Sprite sprite, Group GameRoot, int code) { //collision for sprite and wall //code 1 - projectile, code 2 - enemies
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[0].length; col++) {
                if (cells[row][col].border == true) {
                    if (sprite.Sprite.getBoundsInParent().intersects(cells[row][col].Cell.getBoundsInParent())) {
                        sprite.setXSprite(sprite.getXSprite() + sprite.getXSpeed());
                        sprite.setYSprite(sprite.getYSprite() + sprite.getYSpeed());
                        if (code == 1) {//if sprite is projectile
                            sprite.setAlive(false);
                        }
                    }
                }
            }
        }
    }
}
