package model.board.element.character;

import model.board.Board;
import model.board.Direction;
import model.board.Size;
import model.board.element.Empty;
import model.board.element.Entity;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The Monster class represents an abstract monster entity in the game board.
 * It defines the common characteristics and behaviors of all monsters.
 */
public abstract class Monster extends Entity {

    Direction currentDirection;
    Random random;

    protected Board board;

    protected List<Image> images;

    /**
     * Constructs a Monster object with the specified parameters.
     *
     * @param x         the x-coordinate of the monster
     * @param y         the y-coordinate of the monster
     * @param width     the width of the monster
     * @param height    the height of the monster
     * @param velocity  the velocity of the monster
     * @param images    the list of images representing the monster
     * @param alive     the status indicating if the monster is alive
     * @param visible   the status indicating if the monster is visible
     * @param board     the game board the monster belongs to
     */
    public Monster(double x, double y, int width, int height, double velocity, List<Image> images, boolean alive, boolean visible, Board board) {
        super(x, y, width, height, velocity, images.get(0), alive, visible);
        this.images = images;
        this.board = board;
        this.explodable = true;
        this.currentDirection = Direction.UP;
        this.random = new Random();
    }

    /**
     * Changes the direction of the monster randomly with a small probability.
     */
    public void changeDirectionRandomly() {
        if(random.nextDouble() < 0.005) {
            this.currentDirection = Direction.getDirectionExcept(this.currentDirection);
        }
    }

    /**
     * Abstract method that defines the movement behavior of the monster.
     * Subclasses must implement this method to specify the movement logic.
     */
    public abstract void move();


    /**
     * Determines the direction towards the closest player.
     * Uses breadth-first search to find the closest player's direction.
     * @return The direction towards the closest player, or null if no player is found
     */
    public Direction getClosestPlayerDirection() {
        boolean[][] visited = new boolean[Size.BOARD_HEIGHT.getSize()][Size.BOARD_WIDTH.getSize()];
        for(boolean[] row : visited) {
            for(boolean val : row) {
                val = false;
            }
        }

        int ownRow = this.getRow();
        int ownColumn = this.getColumn();

        LinkedList<Entity> queue = new LinkedList<>();
        queue.add(this);
        visited[ownRow][ownColumn] = true;

        Entity[][] staticElements = board.getStaticElements();

        boolean firstIteration = true;
        while(!queue.isEmpty()) {
            Entity entity = queue.removeFirst();
            int row = entity.getRow();
            int column = entity.getColumn();

            Entity upEntity = (row-1 >= 0) && !(visited[row-1][column]) && (staticElements[row-1][column] instanceof Empty) ? staticElements[row-1][column] : null;
            if(upEntity != null) {
                upEntity.setDir(firstIteration ? Direction.UP : entity.getDir());
                if(upEntity.collides(board.getPlayer1()) || upEntity.collides(board.getPlayer2())) {
                    return upEntity.getDir();
                }
                queue.addLast(upEntity);
                visited[row-1][column] = true;
            }

            Entity downEntity = (row+1 <= Size.BOARD_HEIGHT.getSize()-1) && !(visited[row+1][column]) && (staticElements[row+1][column] instanceof Empty) ? staticElements[row+1][column] : null;
            if(downEntity != null) {
                downEntity.setDir(firstIteration ? Direction.DOWN : entity.getDir());
                if(downEntity.collides(board.getPlayer1()) || downEntity.collides(board.getPlayer2())) {
                    return downEntity.getDir();
                }
                queue.addLast(downEntity);
                visited[row+1][column] = true;
            }

            Entity leftEntity = (column-1 >= 0) && !(visited[row][column-1]) && (staticElements[row][column-1] instanceof Empty) ? staticElements[row][column-1] : null;
            if(leftEntity != null) {
                leftEntity.setDir(firstIteration ? Direction.LEFT : entity.getDir());
                if(leftEntity.collides(board.getPlayer1()) || leftEntity.collides(board.getPlayer2())) {
                    return leftEntity.getDir();
                }
                queue.addLast(leftEntity);
                visited[row][column-1] = true;
            }

            Entity rightEntity = (column+1 <= Size.BOARD_WIDTH.getSize()-1) && !(visited[row][column+1]) && (staticElements[row][column+1] instanceof Empty) ? staticElements[row][column+1] : null;
            if(rightEntity != null) {
                rightEntity.setDir(firstIteration ? Direction.RIGHT : entity.getDir());
                if(rightEntity.collides(board.getPlayer1()) || rightEntity.collides(board.getPlayer2())) {
                    return rightEntity.getDir();
                }
                queue.addLast(rightEntity);
                visited[row][column+1] = true;
            }
            firstIteration = false;
        }

        return null;
    }

    /**
     * Checks if the entity is in an intersection.
     * An intersection is a position where the entity has at least two possible directions to move.
     * @return True if the entity is in an intersection, otherwise false
     */
    public boolean inIntersection() {
        int row = this.getRow();
        int column = this.getColumn();

        if(row == 0 || row == Size.BOARD_HEIGHT.getSize()-1 || column == 0 || column == Size.BOARD_WIDTH.getSize()-1) return false;

        Entity[][] staticElements = board.getStaticElements();
        int freeWays = 0;
        if(staticElements[row-1][column] instanceof Empty) freeWays++;
        if(staticElements[row+1][column] instanceof Empty) freeWays++;
        if(staticElements[row][column-1] instanceof Empty) freeWays++;
        if(staticElements[row][column+1] instanceof Empty) freeWays++;

        return freeWays >= 2 && (this.x % Size.TILE_WIDTH.getSize() == 0) && (this.y % Size.TILE_HEIGHT.getSize() == 0);
    }

    //Setter
    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
