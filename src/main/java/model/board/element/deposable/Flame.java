package model.board.element.deposable;

import model.board.Board;
import model.board.Direction;
import model.board.element.Entity;
import model.board.element.character.Player;
import model.board.element.field.Wall;
import model.board.element.powerup.Bonus;
import view.state.GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static model.board.Size.TILE_HEIGHT;
import static model.board.Size.TILE_WIDTH;

/**
 * Represents a Flame entity that expands in a specific direction from a Bomb explosion.
 * This class handles the expansion of the Flame, collision detection, and interactions with other entities.
 */
public class Flame extends Entity {
    private Board board;
    private Direction direction;
    private int numberOfExpansions;

    /**
     * Constructs a Flame entity with the specified parameters.
     *
     * @param x                 The x-coordinate of the Flame's position.
     * @param y                 The y-coordinate of the Flame's position.
     * @param width             The width of the Flame.
     * @param height            The height of the Flame.
     * @param velocity          The velocity of the Flame.
     * @param image             The image representing the Flame.
     * @param alive             The boolean indicating if the Flame is alive.
     * @param visible           The boolean indicating if the Flame is visible.
     * @param board             The Board object where the Flame exists.
     * @param direction         The Direction in which the Flame expands.
     * @param numberOfExpansions The number of expansions the Flame should make.
     */
    public Flame(double x, double y, int width, int height, double velocity, Image image, boolean alive, boolean visible, Board board, Direction direction, int numberOfExpansions, Bomb bomb) {
        super(x, y, width, height, velocity, image, alive, visible);
        this.explodable = false;
        this.board = board;
        board.addEntity(this);
        this.direction = direction;
        this.numberOfExpansions = numberOfExpansions;
    }

    /**
     * Marks the entities that the Flame collides with as removable.
     * If a Box is hit, it also triggers the Box to explode.
     *
     * @return True if the Flame can continue expanding, false otherwise.
     */
    public boolean markEntitiesRemovable() {
        Bonus bonus = null;
        ArrayList<Entity> entities = new ArrayList<>(board.getEntities());
        for(Entity entity : entities) {
            if (entity != null) {
                if(entity.collides(this)) {
                    if(entity.isExplodable()) {
                        entity.setRemovable(true);
                        if(entity instanceof Box) {
                            if(((Box) entity).getOwner() != null){
                                ((Box) entity).giveBoxToPlayer();
                            }

                            if(((Box) entity).getBonus() != null) {
                                bonus = ((Box) entity).getBonus();
                                bonus.setExplodable(true);
                                bonus.setVisible(true);
                                board.addEntity(bonus);
                            }
                            return false;
                        }
                        if(entity instanceof Player) {
                            entity.setAlive(false);
                        }
                    }
                    if(entity instanceof Wall) return false;
                    if(entity instanceof Bomb) {
                        ((Bomb) entity).explode();
                    }
                }
            }
        }

        return true;
    }

    /**
     * Expands the Flame by one tile in the direction it is moving.
     * Adjusts the Flame's position and size accordingly.
     */
    public void expandOneTile() {
        switch(direction) {
            case UP:
                y -= TILE_HEIGHT.getSize();
                height += TILE_HEIGHT.getSize();
                break;
            case DOWN:
                height += TILE_HEIGHT.getSize();
                break;
            case LEFT:
                x -= TILE_WIDTH.getSize();
                width += TILE_WIDTH.getSize();
                break;
            case RIGHT:
                width += TILE_WIDTH.getSize();
                break;
        }
        boolean needToBePlacedBack = false;
        ArrayList<Entity> entities = new ArrayList<>(board.getEntities());
        for(Entity entity : entities) {
            if(entity instanceof Wall && entity.collides(this)) needToBePlacedBack = true;
        }
        if(needToBePlacedBack) {
            switch(direction) {
                case UP:
                    y += TILE_HEIGHT.getSize();
                    height -= TILE_HEIGHT.getSize();
                    break;
                case DOWN:
                    height -= TILE_HEIGHT.getSize();
                    break;
                case LEFT:
                    x += TILE_WIDTH.getSize();
                    width -= TILE_WIDTH.getSize();
                    break;
                case RIGHT:
                    width -= TILE_WIDTH.getSize();
                    break;
            }
        }
    }

    /**
     * Initiates the expansion of the Flame.
     * The Flame expands in its direction, marking entities as removable upon collision.
     * After the specified number of expansions, the Flame becomes removable itself.
     */
    public void expand() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int expansions = 0;
            boolean mainConditionMet = false;

            @Override
            public void run() {
                if(board.getGameState()!= GameState.BOTH_ALIVE){
                    removable = true;
                    timer.cancel();
                    timer.purge();
                }
                if (!mainConditionMet) {
                    if (expansions < numberOfExpansions && markEntitiesRemovable()) {
                        expansions++;
                        expandOneTile();
                    } else {
                        markEntitiesRemovable();
                        mainConditionMet = true;
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                removable = true;
                                timer.cancel();
                                timer.purge();
                            }
                        }, 500);
                    }
                }
            }
        };
        timer.schedule(task, 0, 200);
    }
}
