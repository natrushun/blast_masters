package model.board.element.powerup;

import model.board.element.Entity;
import model.board.element.character.Player;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a bonus entity in the game that provides players with benefits or handicaps.
 * Bonus is an abstract class that defines common properties and behavior of
 * all types of bonuses in the game. Each bonus has an owner, and when used,
 * it applies a specific benefit to the owner player.
 */
public abstract class Bonus extends Entity {

    /** The player who owns this bonus. */
    protected Player owner;

    /**
     * Constructs a Bonus with the specified parameters.
     *
     * @param x       the x-coordinate of the bonus
     * @param y       the y-coordinate of the bonus
     * @param width   the width of the bonus
     * @param height  the height of the bonus
     * @param velocity the velocity of the bonus
     * @param image   the image representing the bonus
     * @param alive   the status indicating if the bonus is alive
     * @param visible the visibility status of the bonus
     * @param owner   the player who owns the bonus
     */
    public Bonus(double x, double y, int width, int height, double velocity, Image image, boolean alive, boolean visible, Player owner) {
        super(x, y, width, height, velocity, image, alive, visible);
        this.owner = owner;
        this.explodable = false;
    }

    /**
     * Abstract method to define the specific benefit of the bonus when used.
     * Subclasses must implement this method to apply their specific benefit
     * to the owner player.
     */
    public abstract void use();

    /**
     * Retrieves the owner player of this bonus.
     *
     * @return the owner player of the bonus
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * Sets the owner player of this bonus.
     *
     * @param owner the player who will become the owner of the bonus
     */

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Assigns this bonus to the specified player, making it invisible and
     * ready to be used.
     * This method is typically called when a player interacts with the bonus
     * entity in the game.
     *
     * @param player the player who will receive and use the bonus
     */
    public void getUsedByPlayer(Player player) {
        this.owner = player;
        this.visible = false;
        this.removable = true;
        this.use();
    }

    /**
     * Sets the explodable status of the bonus after a delay.
     * This method allows the bonus to become explodable after a certain
     * time delay, useful for bonuses with temporary effects.
     *
     * @param e the explodable status to set
     */
    public void setExplodable(boolean e) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                explodable = e;
            }
        }, 600);
    }
}
