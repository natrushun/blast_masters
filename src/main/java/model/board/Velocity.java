package model.board;

/**
 * Enum representing various velocities used in the game.
 */
public enum Velocity {

    /**
     * Represents the velocity of a bomb.
     */
    BOMB_VEL(0),

    /**
     * Represents the velocity of a wall.
     */
    WALL_VEL(0),

    /**
     * Represents the velocity of a box.
     */
    BOX_VEL(0),

    /**
     * Represents the velocity of a player character.
     */
    PLAYER_VEL(2),
    PLAYER_WITH_ROLLER_VEL(2.5),
    PLAYER_WITH_SLOWDOWN_VEL(1),

    /**
     * Represents the velocity of a bonus.
     */
    BONUS_VEL(0),

    /**
     * Represents the velocity of monsters.
     */
    MONSTER_VEL(1),
    SEMI_INTELLIGENT_MONSTER_VEL(1.5),
    INTELLIGENT_MONSTER_VEL(1),
    GHOST_MONSTER_VEL(0.5);

    private final double velocity;


    Velocity(double velocity) {
         this.velocity = velocity;
    }


    public double getVelocity() {
        return velocity;
    }
}
