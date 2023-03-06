/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compsciproject2023;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

/**
 *
 * @author 10848
 */
public class Terrain {

    int[][] maze;
    Rectangle rectangles[][];
    final int RectWidth = 140;
    final int RectHeight = 150;

    public Terrain() {
        maze = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 3},
            {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 3},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            
        };

        rectangles = new Rectangle[maze.length][maze[0].length];

        Color tempColour = null;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {

                switch (maze[i][j]) {
                    case 0:
                        tempColour = Color.BLACK;
                        break;
                    case 1:
                        tempColour = Color.BLANCHEDALMOND;
                        break;
                    case 3:
                        tempColour = Color.WHITE;
                        break;
                }

                rectangles[i][j] = new Rectangle(RectWidth, RectHeight, tempColour);
                rectangles[i][j].setLayoutX(j * (RectWidth));
                rectangles[i][j].setLayoutY(i * (RectHeight));
            }

        }
    }

}


