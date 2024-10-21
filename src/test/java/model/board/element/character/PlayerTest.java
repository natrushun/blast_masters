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

public class PlayerTest {

    private Player player;
    private Board board;


    @Before
    public void setUp() {
        int roundsToWin = 1;
        int selectedMapIndex = 1;

        String mapFilePath = "src/test/resources/testMaps/playerMap.txt";

        try {
            Settings settings = new Settings();
            board = new Board(BOARD_WIDTH.getSize(), mapFilePath, selectedMapIndex, roundsToWin);
            player = (Player) board.getPlayer1();
            settings.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading map file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }





    @Test
    public void testGetThePositionOfTheBombToBePlaced() {

        double playerx= player.getX();
        double playery= player.getY();

        double bomby= player.getThePositionOfTheBombToBePlaced().getY();
        double bombx= player.getThePositionOfTheBombToBePlaced().getX();

        assertEquals(playerx,bombx,0);
        assertEquals(playery,bomby,0);

    }


    @Test
    public void testMove() {
        // felfele mozgas fal fele
        double playerxBefore= player.getX();
        double playeryBefore= player.getY();


        player.move(UP,player.getVelocity());


        double playerxAfter= player.getX();
        double playeryAfter= player.getY();



        assertEquals(playerxBefore,playerxAfter,0);
        assertEquals(playeryBefore,playeryAfter,0);




        // mozgas ures mezo iranyaba




        player.move(LEFT,player.getVelocity());



        assertEquals(playerxBefore-player.getVelocity(),player.getX(),0);


        playerxBefore = player.getX();
        player.move(RIGHT,player.getVelocity());
        assertEquals(playerxBefore + player.getVelocity(), player.getX(), 0);



    }






}


