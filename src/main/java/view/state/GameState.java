package view.state;

/**
 * Represents the various states of the game.
 * The GameState enum defines different states that the game can be in,
 * such as both players alive, one player alive, a player winning, paused,
 * or a draw
 */
public enum GameState {
    /** Both players are alive and actively playing. */
    BOTH_ALIVE,

    /** Player 1 has won the game. */
    PLAYER1_WON,

    /** Player 2 has won the game. */
    PLAYER2_WON,

    /** Player 1 has won the final round of the game. */
    PLAYER1_FINAL_WIN,

    /** Player 2 has won the final round of the game. */
    PLAYER2_FINAL_WIN,

    /** The game ends in a draw. */
    DRAW
}
