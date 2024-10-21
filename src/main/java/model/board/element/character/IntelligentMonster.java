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
import java.util.List;

/**
 * The IntelligentMonster class represents a monster entity on the game board
 * that exhibits intelligent behavior. It can determine the closest player
 * and make decisions based on that information.
 */
public class IntelligentMonster extends Monster {

    private int imageChangeCounter = 0;
    private static final int IMAGE_CHANGE_THRESHOLD = 12;

    /**
     * Constructs an IntelligentMonster object with the specified parameters.
     *
     * @param x        the x-coordinate of the monster
     * @param y        the y-coordinate of the monster
     * @param width    the width of the monster
     * @param height   the height of the monster
     * @param velocity the velocity of the monster
     * @param images   the image representing the monster
     * @param alive    the status indicating if the monster is alive
     * @param visible  the status indicating if the monster is visible
     * @param board    the game board the monster belongs to
     */
    public IntelligentMonster(double x, double y, int width, int height, double velocity, List<Image> images, boolean alive, boolean visible, Board board) {
        super(x, y, width, height, velocity, images, alive, visible, board);
    }

    /**
     * Moves the entity.
     * Handles movement, collision detection with walls, boxes, bombs, flames, and other players,
     * and changes direction accordingly.
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
                    int nextIndex = (currentIndex + 1) % 8;
                    this.image = images.get(nextIndex + 8);
                    break;
                case DOWN:
                    currentIndex = images.indexOf(this.image);
                    nextIndex = (currentIndex + 1) % 8;
                    this.image = images.get(nextIndex + 16);
                    break;
                case LEFT:
                    currentIndex = images.indexOf(this.image);
                    nextIndex = (currentIndex + 1) % 8;
                    this.image = images.get(nextIndex + 24);
                    break;
                case RIGHT:
                    currentIndex = images.indexOf(this.image);
                    nextIndex = (currentIndex + 1) % 8;
                    this.image = images.get(nextIndex);
                    break;
                default:
                    break;
            }
        }

        if(this.inIntersection()) {
            Direction closest = getClosestPlayerDirection();
            this.currentDirection = closest != null ? closest : this.currentDirection;
            changeDirectionRandomly();
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
    }

    @Override
    public String toString() {
        return "Im";
    }
}
