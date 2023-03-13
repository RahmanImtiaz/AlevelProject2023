/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

/**
 *
 * @author 10848
 */
public class Room {

    int[][] maze;
    Cell cells[][];
     double RectWidth = 100;
     double RectHeight = 100;

    public Room(double width, double height) {
        RectWidth = width;
        RectHeight = height;
        maze = new int[][]{
            {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
            {4, 1, 1, 3, 1, 1, 1, 1, 1, 1, 5},
            {4, 1, 1, 3, 1, 1, 1, 3, 1, 1, 5},
            {4, 1, 1, 1, 1, 1, 1, 3, 1, 1, 5},
            {4, 1, 1, 1, 1, 1, 1, 3, 1, 1, 5},
            {4, 1, 1, 1, 1, 1, 1, 3, 1, 1, 5},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},};

        cells = new Cell[maze.length][maze[0].length];

        Color tempColour = null;
        //Image wall = new Image("MazeCell.jpeg");

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                int code = -1;

                switch (maze[i][j]) {
                    case 0:
                        code = 0;

                        break;
                    case 1:
                        code = 1;

                        break;
                    case 3:
                        code = 3;
                        break;
                    case 4:
                        code = 4;
                        break;
                    case 5:
                        code = 5;
                        break;
                }

                cells[i][j] = new Cell(code, RectWidth, RectHeight);
                cells[i][j].Cell.setLayoutX(j * (RectWidth));
                cells[i][j].Cell.setLayoutY(i * (RectHeight));

            }

        }
    }

}
