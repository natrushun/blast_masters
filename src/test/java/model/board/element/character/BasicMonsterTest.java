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

public class BasicMonsterTest {
    private BasicMonster basicMonster;
    private Board board;

    @Before
    public void setUp() {
        int roundsToWin = 1;
        int selectedMapIndex = 1;

        String mapFilePath = "src/test/resources/testMaps/basicMonsterMap.txt";

        try {
            Settings settings = new Settings();
            board = new Board(BOARD_WIDTH.getSize(), mapFilePath, selectedMapIndex, roundsToWin);
            basicMonster = (BasicMonster) board.getMonsters().getFirst();
            settings.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading map file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    @Test
    public void testMove() {
        double prevX, prevY;

        basicMonster.setCurrentDirection(LEFT);
        prevX = basicMonster.getX();
        basicMonster.move();
        assertEquals(prevX, basicMonster.getX(), 0);

        basicMonster.setCurrentDirection(RIGHT);
        prevX = basicMonster.getX();
        basicMonster.move();
        assertEquals(prevX + basicMonster.getVelocity(), basicMonster.getX(), 0);

        basicMonster.setCurrentDirection(UP);
        prevY = basicMonster.getY();
        basicMonster.move();
        assertEquals(prevY - basicMonster.getVelocity(), basicMonster.getY(), 0);

        basicMonster.setCurrentDirection(DOWN);
        prevY = basicMonster.getY();
        basicMonster.move();
        assertEquals(prevY + basicMonster.getVelocity(), basicMonster.getY(), 0);

    }

//

}
