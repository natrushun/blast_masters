package model.board.element.deposable;

import model.board.Board;
import model.board.element.Entity;
import model.board.element.character.Player;
import model.board.element.powerup.Bonus;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a box on the game board that can contain bonuses.
 */
public class Box extends Entity {
    private Bonus bonus;
    private Player owner;

    private boolean gaveBack;
    Board board;

    /**
     * Constructs a Box object with the specified parameters.
     *
     * @param x       The x-coordinate of the box.
     * @param y       The y-coordinate of the box.
     * @param width   The width of the box.
     * @param height  The height of the box.
     * @param velocity The velocity of the box.
     * @param image   The image of the box.
     * @param alive   The status indicating if the box is alive.
     * @param visible The visibility status of the box.
     * @param bonus   The bonus contained in the box.
     * @param board   The game board the box belongs to.
     */
    public Box(double x, double y, int width, int height, double velocity, Image image, boolean alive, boolean visible, Bonus bonus, Board board) {
        super(x, y, width, height, velocity, image, alive, visible);
        this.bonus = bonus;
        this.explodable = true;
        this.board = board;
        this.owner=null;
        this.gaveBack=false;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    public void setOwner(Player owner){
        this.owner=owner;
    }

    public Player getOwner(){
        return owner;
    }
    /**
     * Plants the box on the game board.
     * Sets the visibility of the box to true, adds it to the list of entities on the board,
     * adds it as a static element at its current position on the board, and associates it with a player.
     */
    public void plant() {
        this.setVisible(true);
        board.addEntity(this);
        board.addStaticElement(this, this.getRow(), this.getColumn());
        board.addBox(this);

    }
    /**
     * Gives the box back to its owner player.
     * Increments the number of placeable boxes for the owner player if the box has not been previously returned.
     */
    public void giveBoxToPlayer(){
        if(!gaveBack){
            owner.incrementNumberOfPlaceableBoxes();
            gaveBack=true;
        }
    }

    public Bonus getBonus() {
        return bonus;
    }

    @Override
    public String toString() {
        return "\uD83D\uDCE6";
    }
}
