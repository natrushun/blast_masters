package model.board;

import org.junit.Test;
import static org.junit.Assert.*;

public class VelocityTest {

    @Test
    public void testGetVelocity() {
        assertEquals("BOMB_VEL should be 0", 0.0, Velocity.BOMB_VEL.getVelocity(), 0.0001);
        assertEquals("WALL_VEL should be 0", 0.0, Velocity.WALL_VEL.getVelocity(), 0.0001);
        assertEquals("BOX_VEL should be 0", 0.0, Velocity.BOX_VEL.getVelocity(), 0.0001);
        assertEquals("PLAYER_VEL should be 2", 2.0, Velocity.PLAYER_VEL.getVelocity(), 0.0001);
        assertEquals("PLAYER_WITH_ROLLER_VEL should be 2.5", 2.5, Velocity.PLAYER_WITH_ROLLER_VEL.getVelocity(), 0.0001);
        assertEquals("PLAYER_WITH_SLOWDOWN_VEL should be 1", 1.0, Velocity.PLAYER_WITH_SLOWDOWN_VEL.getVelocity(), 0.0001);
        assertEquals("BONUS_VEL should be 0", 0.0, Velocity.BONUS_VEL.getVelocity(), 0.0001);
        assertEquals("MONSTER_VEL should be 1", 1.0, Velocity.MONSTER_VEL.getVelocity(), 0.0001);
        assertEquals("SEMI_INTELLIGENT_MONSTER_VEL should be 1.5", 1.5, Velocity.SEMI_INTELLIGENT_MONSTER_VEL.getVelocity(), 0.0001);
        assertEquals("INTELLIGENT_MONSTER_VEL should be 1", 1.0, Velocity.INTELLIGENT_MONSTER_VEL.getVelocity(), 0.0001);
        assertEquals("GHOST_MONSTER_VEL should be 0.5", 0.5, Velocity.GHOST_MONSTER_VEL.getVelocity(), 0.0001);
    }
}