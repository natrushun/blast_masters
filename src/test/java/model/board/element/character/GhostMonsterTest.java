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

public class GhostMonsterTest {
    private GhostMonster ghostMonster;
    private Board board;

    @Before
    public void setUp() {
        int roundsToWin = 1;
        int selectedMapIndex = 1;

        String mapFilePath = "src/test/resources/testMaps/ghostMonsterMap.txt";

        try {
            Settings settings = new Settings();
            board = new Board(BOARD_WIDTH.getSize(), mapFilePath, selectedMapIndex, roundsToWin);
            ghostMonster = (GhostMonster) board.getMonsters().getFirst();
            settings.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading map file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Test
    public void testMove() {
        double prevX, prevY;

        ghostMonster.setCurrentDirection(RIGHT);
        prevX = ghostMonster.getX();
        ghostMonster.move();
        assertEquals(prevX, ghostMonster.getX(), 0);

        ghostMonster.setCurrentDirection(LEFT);
        prevX = ghostMonster.getX();
        ghostMonster.move();
        assertEquals(prevX - ghostMonster.getVelocity(), ghostMonster.getX(), 0);

        ghostMonster.setCurrentDirection(UP);
        prevY = ghostMonster.getY();
        ghostMonster.move();
        assertEquals(prevY - ghostMonster.getVelocity(), ghostMonster.getY(), 0);

        ghostMonster.setCurrentDirection(DOWN);
        prevY = ghostMonster.getY();
        ghostMonster.move();
        assertEquals(prevY + ghostMonster.getVelocity(), ghostMonster.getY(), 0);

    }

    @Test
    public void testIsThereEmptyField() {
        assertFalse(ghostMonster.isThereEmptyField(RIGHT));
        assertTrue(ghostMonster.isThereEmptyField(LEFT));
        assertTrue(ghostMonster.isThereEmptyField(UP));
        assertTrue(ghostMonster.isThereEmptyField(DOWN));
    }

}
