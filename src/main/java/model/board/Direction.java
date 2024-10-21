package model.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Enum representing directions for movement on a game board.
 */
public enum Direction {


    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * Gets a random direction different from the specified direction.
     *
     * @param d the direction to exclude
     * @return a random direction other than the specified direction
     */
    public static Direction getDirectionExcept(Direction d) {
        ArrayList<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        Collections.shuffle(directions);
        Direction result = directions.get(0);
        for(Direction direction : directions) {
            if(direction != d) {
                result = direction;
                break;
            }
        }
        return result;
    }

    /**
     * Gets the opposite direction of the specified direction.
     *
     * @param d the input direction
     * @return the opposite direction of the input direction
     */
    public static Direction getOppositeDirection(Direction d) {
        return switch (d) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> null;
        };

    }

}
