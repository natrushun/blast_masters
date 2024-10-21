package main;

import view.ui.MainMenu;

import javax.swing.*;
import java.io.IOException;

/**
 * The main class for the Blast Masters game.
 * Initializes the main menu and sets up the game window.
 */
public class BlastMastersMain {
    /**
     * The main method to start the Blast Masters game.
     *
     * @param args the command-line arguments (not used)
     * @throws IOException if an IO error occurs
     */
    public static void main(String[] args) throws IOException {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setContentPane(mainMenu.panelMain);
        mainMenu.setTitle("Blast Masters");
        mainMenu.setSize(800, 600); //todo: size
        mainMenu.setVisible(true);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}