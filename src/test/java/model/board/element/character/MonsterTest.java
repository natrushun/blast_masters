package model.board.element.character;

import control.Settings;
import model.board.Board;
import model.board.Size;
import org.junit.Before;
import org.junit.Test;
import view.state.GameEngine;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static model.board.Direction.*;
import static model.board.Size.BOARD_WIDTH;
import static model.board.Size.TILE_WIDTH;
import static org.junit.Assert.*;

public class MonsterTest {
    private Monster monster;
    private Board board;

    @Before
    public void setUp() {
        int roundsToWin = 1;
        int selectedMapIndex = 1;

        String mapFilePath = "src/test/resources/testMaps/monsterMap1.txt";

        try {
            Settings settings = new Settings();
            board = new Board(BOARD_WIDTH.getSize(), mapFilePath, selectedMapIndex, roundsToWin);
            monster = board.getMonsters().getFirst();
            settings.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading map file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Test
    public void testInIntersection() {
        assertTrue(monster.inIntersection());

        monster.moveTowardsDirection(RIGHT);
        assertFalse(monster.inIntersection());
    }

    @Test
    public void testGetClosestPlayerDirection() {
        assertEquals(UP, monster.getClosestPlayerDirection());
        for(int i = 0; i < (BOARD_WIDTH.getSize()-3)*TILE_WIDTH.getSize(); i++) {
            monster.moveTowardsDirection(RIGHT);
        }
        assertEquals(DOWN, monster.getClosestPlayerDirection());
    }

}
