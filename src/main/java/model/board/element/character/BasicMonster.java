package model.board.element.character;

import model.board.Board;
import model.board.Direction;
import model.board.element.Entity;
import model.board.element.deposable.Bomb;
import model.board.element.deposable.Box;
import model.board.element.deposable.Flame;
import model.board.element.field.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * The BasicMonster class represents a basic monster entity on the game board.
 * Basic monsters move in a predefined direction until they encounter obstacles,
 * at which point they change direction randomly.
 */
public class BasicMonster extends Monster {
    private int imageChangeCounter = 0;
    private static final int IMAGE_CHANGE_THRESHOLD = 12;

    /**
     * Constructs a BasicMonster object with the specified parameters.
     *
     * @param x        the x-coordinate of the monster
     * @param y        the y-coordinate of the monster
     * @param width    the width of the monster
     * @param height   the height of the monster
     * @param velocity the velocity of the monster
     * @param images    the image representing the monster
     * @param alive    the status indicating if the monster is alive
     * @param visible  the status indicating if the monster is visible
     * @param board    the game board the monster belongs to
     */
    public BasicMonster(double x, double y, int width, int height, double velocity, List<Image> images, boolean alive, boolean visible, Board board) {
        super(x, y, width, height, velocity, images, alive, visible, board);
    }

    /**
     * Moves the monster in its current direction, checking for collisions
     * with walls, boxes, bombs, flames, and players. If a collision occurs,
     * the monster changes its direction randomly.
     */
    @Override
    public void move() {
        imageChangeCounter++;
        if(!this.isAlive()) return;
        if (imageChangeCounter >= IMAGE_CHANGE_THRESHOLD) {
            imageChangeCounter = 0;
            switch (currentDirection) {
                case UP:
                    int currentIndex = images.indexOf(this.image);
                    int nextIndex = (currentIndex + 1) % 3;
                    this.image = images.get(nextIndex + 4);
                    break;
                case DOWN:
                    currentIndex = images.indexOf(this.image);
                    nextIndex = (currentIndex + 1) % 3;
                    this.image = images.get(nextIndex + 7);
                    break;
                case LEFT:
                    currentIndex = images.indexOf(this.image);
                    nextIndex = (currentIndex + 1) % 4;
                    this.image = images.get(nextIndex + 10);
                    break;
                case RIGHT:
                    currentIndex = images.indexOf(this.image);
                    nextIndex = (currentIndex + 1) % 4;
                    this.image = images.get(nextIndex);
                    break;
                default:
                    break;
            }
        }
        this.moveTowardsDirection(currentDirection);
        ArrayList<Entity> entites = new ArrayList<>(board.getEntities());

        boolean needToChangeDirection = false;
        for(Entity entity : entites) {
            if(((entity instanceof Wall) || (entity instanceof Box) || (entity instanceof Bomb)) && this.collides(entity)) {
                needToChangeDirection = true;
                break;
            }
            if(entity instanceof Flame && entity.collides(this)) {
                this.alive = false;
                this.removable=true;
            }
            if(entity instanceof Player && entity.collides(this)) {
                entity.setAlive(false);
            }
        }

        if(needToChangeDirection) {
            this.moveTowardsDirection(Direction.getOppositeDirection(this.currentDirection));
            this.currentDirection = Direction.getDirectionExcept(this.currentDirection);
        }

        changeDirectionRandomly();
    }
    @Override
    public String toString() {
        return "Bm";
    }

}
