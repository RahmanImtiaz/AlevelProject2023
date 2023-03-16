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
public enum Direction {
    
    UP, DOWN, LEFT, RIGHT, NONE;
    
    Direction getNeighbourDoor(Direction d){
        switch(d){
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
        
        }
        return NONE;
    }
}
