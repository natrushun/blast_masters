package model.board.element.field;

import model.board.element.Entity;


import java.awt.*;

/**
 * The Wall class represents a wall entity on the game board.
 * Walls are non-explodable and provide obstacles to players and monsters.
 */
public class Wall extends Entity {

    /**
     * Constructs a Wall object with the specified parameters.
     *
     * @param x       the x-coordinate of the wall
     * @param y       the y-coordinate of the wall
     * @param width   the width of the wall
     * @param height  the height of the wall
     * @param velocity the velocity of the wall (unused for walls)
     * @param image   the image representing the wall
     * @param alive   the status indicating if the wall is alive
     * @param visible the status indicating if the wall is visible
     */
    public Wall(double x, double y, int width, int height, double velocity, Image image, boolean alive, boolean visible) {
        super(x, y, width, height, velocity, image, alive, visible);
        this.explodable = false;
    }

    @Override
    public String toString() {
        return "\uD83E\uDDF1";
    }
}
