package model.board;

/**
 * Enum representing various sizes used in the game board.
 */
public enum Size {
    /**
     * Represents the size of the game board.
     */
    BOARD_HEIGHT(10),
    BOARD_WIDTH(25),

    /**
     * Represents the height of a player character.
     */
    PLAYER_HEIGHT(45),

    /**
     * Represents the width of a player character.
     */
    PLAYER_WIDTH(35),

    /**
     * Represents the size of monsters.
     */
    MONSTER_SIZE(60),

    INTELLIGENTMONSTER_HEIGHT(60),

    INTELLIGENTMONSTER_WIDTH(30),

    /**
     * Represents the size of a wall.
     */
    WALL_SIZE(60),

    /**
     * Represents the size of a box.
     */
    BOX_SIZE(60),

    /**
     * Represents the size of a bonus.
     */
    BONUS_SIZE(40),

    /**
     * Represents the height of a bomb.
     */
    BOMB_HEIGHT(60),

    /**
     * Represents the width of a bomb.
     */
    BOMB_WIDTH(60),

    /**
     * Represents the width of a tile.
     */
    TILE_WIDTH(60),

    /**
     * Represents the height of a tile.
     */
    TILE_HEIGHT(60),

    /**
     * Represents the height of a flame effect.
     */
    FLAME_HEIGHT(60),

    /**
     * Represents the width of a flame effect.
     */
    FLAME_WIDTH(60);
    private final int size;

    Size(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

}
