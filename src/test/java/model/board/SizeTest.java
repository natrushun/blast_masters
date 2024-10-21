package model.board;

import org.junit.Test;
import static org.junit.Assert.*;

public class SizeTest{

    @Test
    public void testGetSize() {
        assertEquals("BOARD_HEIGHT should be 10", 10, Size.BOARD_HEIGHT.getSize());
        assertEquals("BOARD_WIDTH should be 25", 25, Size.BOARD_WIDTH.getSize());
        assertEquals("PLAYER_HEIGHT should be 45", 45, Size.PLAYER_HEIGHT.getSize());
        assertEquals("PLAYER_WIDTH should be 35", 35, Size.PLAYER_WIDTH.getSize());
        assertEquals("MONSTER_SIZE should be 60", 60, Size.MONSTER_SIZE.getSize());
        assertEquals("INTELLIGENTMONSTER_HEIGHT should be 60", 60, Size.INTELLIGENTMONSTER_HEIGHT.getSize());
        assertEquals("INTELLIGENTMONSTER_WIDTH should be 30", 30, Size.INTELLIGENTMONSTER_WIDTH.getSize());
        assertEquals("WALL_SIZE should be 60", 60, Size.WALL_SIZE.getSize());
        assertEquals("BOX_SIZE should be 60", 60, Size.BOX_SIZE.getSize());
        assertEquals("BONUS_SIZE should be 40", 40, Size.BONUS_SIZE.getSize());
        assertEquals("BOMB_HEIGHT should be 60", 60, Size.BOMB_HEIGHT.getSize());
        assertEquals("BOMB_WIDTH should be 60", 60, Size.BOMB_WIDTH.getSize());
        assertEquals("TILE_WIDTH should be 60", 60, Size.TILE_WIDTH.getSize());
        assertEquals("TILE_HEIGHT should be 60", 60, Size.TILE_HEIGHT.getSize());
        assertEquals("FLAME_HEIGHT should be 60", 60, Size.FLAME_HEIGHT.getSize());
        assertEquals("FLAME_WIDTH should be 60", 60, Size.FLAME_WIDTH.getSize());
    }
}