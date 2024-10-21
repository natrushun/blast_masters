package model.board.element.deposable;

import model.board.Board;
import model.board.Direction;
import model.board.element.Entity;
import model.board.element.character.Player;
import model.board.element.field.Wall;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static model.board.Image.*;
import static model.board.Size.*;

/**
 * The Bomb class represents a bomb entity in the game.
 * Bombs can be planted by players and explode after a set duration.
 * When a bomb explodes, it creates flame entities in various directions.
 */
public class Bomb extends Entity {
    private boolean detonated = false;
    private Player owner;
    private Board board;
    private int range;
    private List<Image> images;
    private int imageChangeCounter = 0;

    /**
     * Creates a new Bomb instance.
     *
     * @param x       The x-coordinate of the bomb.
     * @param y       The y-coordinate of the bomb.
     * @param width   The width of the bomb.
     * @param height  The height of the bomb.
     * @param velocity The velocity of the bomb.
     * @param images   The image of the bomb.
     * @param alive   The status of the bomb's existence.
     * @param visible The visibility of the bomb.
     * @param owner   The player who owns the bomb.
     * @param board   The game board where the bomb exists.
     */
    public Bomb(double x, double y, int width, int height, double velocity, int range, List<Image> images, boolean alive, boolean visible, Player owner, Board board) {
        super(x, y, BOMB_WIDTH.getSize(), BOMB_HEIGHT.getSize(), velocity, images.get(0), alive, visible);
        this.images=images;
        this.owner = owner;
        this.board = board;
        this.explodable = true;
        this.range = range;
    }

    /**
     * Plants the bomb on the game board.
     * Initiates a timer for the bomb to explode after a set duration.
     */


    public void plant() {
        this.setVisible(true);
        board.addEntity(this);
        board.addStaticElement(this, this.getRow(), this.getColumn());
        board.addBomb(this);

        if(owner.hasDetonator()) {
            return;
        }

        Timer fuse = new Timer();
        fuse.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!detonated) {
                    explode();
                }
            }
        }, 4 * 1000);

        Timer imageTimer = new Timer();
        imageTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (imageChangeCounter >= images.size()) {
                    imageChangeCounter = 0;
                }

                image = images.get(imageChangeCounter);
                imageChangeCounter++;
            }
        }, 0, 4000 / images.size());
    }


    /**
     * Explodes the bomb, creating flame entities in all directions.
     * The range of the explosion is determined by the bomb's range.
     */
    public void explode() {
        if(this.detonated) return;
        this.detonated = true;
        Flame flameUp = new Flame(this.x, this.y, FLAME_WIDTH.getSize(), FLAME_HEIGHT.getSize(), 0, new ImageIcon(FLAME_UP_IMG.getImageUrl()).getImage(), false, true, this.board, Direction.UP, this.range, this);

        Flame flameDown = new Flame(this.x, this.y, FLAME_WIDTH.getSize(), FLAME_HEIGHT.getSize(), 0, new ImageIcon(FLAME_DOWN_IMG.getImageUrl()).getImage(), false, true, this.board, Direction.DOWN, this.range, this);

        Flame flameLeft = new Flame(this.x , this.y, FLAME_WIDTH.getSize(), FLAME_HEIGHT.getSize(), 0, new ImageIcon(FLAME_LEFT_IMG.getImageUrl()).getImage(), false, true, this.board, Direction.LEFT, this.range, this);

        Flame flameRight = new Flame(this.x , this.y, FLAME_WIDTH.getSize(), FLAME_HEIGHT.getSize(), 0, new ImageIcon(FLAME_RIGHT_IMG.getImageUrl()).getImage(), false, true, this.board, Direction.RIGHT, this.range, this);

        this.removable = true;
        flameUp.expand();
        flameDown.expand();
        flameLeft.expand();
        flameRight.expand();

        owner.incrementNumberOfPlaceableBombs();
        owner.removeBomb(this);

    }


    public boolean isDetonated() {
        return detonated;
    }

    @Override
    public String toString() {
        return Character.toString(128_163);
    }

    public void stop() {
        detonated=true;
        removable=true;
    }
}
