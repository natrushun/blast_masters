package view.ui;

import control.Settings;
import model.board.Board;
import view.state.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

import static model.board.Size.BOARD_HEIGHT;
import static model.board.Size.BOARD_WIDTH;

/**
 * The main window for starting the game, allowing players to configure settings,
 * select maps, and start the game.
 */
public class StartGame extends JFrame {

    private int roundsToWin;
    private GameEngine gameEngine;
    private Settings settings;
    private MapSelectorPanel mapSelectorPanel;
    private ButtonPanel buttonPanel;
    private JButton mainMenuButton;
    private Image backgroundImage;
    private Image[] mapImages;
    private String[] mapNames = {"Map 1", "Map 2", "Map 3"};
    private int currentMapIndex = 0;

    public StartGame() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        roundsToWin = 3;
        setTitle("Blast Masters");
        setSize(1500, 807);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/assets/menu/bomberblur.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializeMapImages();

        JPanel panelMain = createMainPanel(roundsToWin);
        mainMenuButton = createMainMenuButton();

        setContentPane(panelMain);

        addStartButtonActionListener();

        addExitButtonActionListener();
    }

    private void initializeMapImages() {
        mapImages = new Image[mapNames.length];
        for (int i = 0; i < mapNames.length; i++) {
            try {
                mapImages[i] = ImageIO.read(new File("src/main/resources/assets/menu/map" + (i + 1) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private JPanel createMainPanel(int value) {
        JPanel panelMain = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelMain.add(createPlayerPanel(), BorderLayout.EAST);
        panelMain.add(createMapSelectorPanel(), BorderLayout.CENTER);
        panelMain.add(createStartPanel(value), BorderLayout.WEST);
        return panelMain;
    }

    private JPanel createPlayerPanel() {
        settings = new Settings();

        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 50));
        containerPanel.add(settings.getP1());
        containerPanel.add(settings.getP2());
        containerPanel.setOpaque(false);
        containerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 25));


        return containerPanel;
    }

    private MapSelectorPanel createMapSelectorPanel() {
        mapSelectorPanel = new MapSelectorPanel(mapNames, mapImages, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMapIndex = mapSelectorPanel.getCurrentMapIndex();
                Image selectedMapImage = mapImages[currentMapIndex];
                mapSelectorPanel.setMapPreview(selectedMapImage);
            }
        });
        return mapSelectorPanel;
    }

    private ButtonPanel createStartPanel(int value) {
        buttonPanel = new ButtonPanel(value);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        return buttonPanel;
    }

    private JButton createMainMenuButton() {
        JButton mainMenuButton = new JButton("\uD83C\uDFE0");
        mainMenuButton.setBackground(new Color(51, 206, 250));
        mainMenuButton.setForeground(Color.white);
        mainMenuButton.setPreferredSize(new Dimension(50, 50));
        mainMenuButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        mainMenuButton.setVisible(false);
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                setContentPane(createMainPanel(roundsToWin));
                initializeMapImages();

                gameEngine.requestFocusInWindow();
                addStartButtonActionListener();
                addExitButtonActionListener();
                revalidate();
                repaint();
            }
        });
        return mainMenuButton;
    }

    /**
     * Adds an action listener to the start button. When the start button is clicked, this method initializes the game engine
     * with the selected map and settings, and starts the game.
     */
    private void addStartButtonActionListener() {
        buttonPanel.addStartButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundsToWin = buttonPanel.getRoundsToWin();
                int selectedMapIndex = mapSelectorPanel.getCurrentMapIndex();

                String mapFilePath = "src/main/resources/maps/map" + (selectedMapIndex + 1) + ".txt";

                try {
                    Board board = new Board(BOARD_WIDTH.getSize(), mapFilePath, selectedMapIndex, roundsToWin);
                    settings.load();

                    mainMenuButton = createMainMenuButton();
                    gameEngine = new GameEngine(board, settings, mainMenuButton);

                    getContentPane().removeAll();
                    getContentPane().add(gameEngine);
                    gameEngine.requestFocusInWindow();

                    revalidate();
                    repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error loading map file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void addExitButtonActionListener() {
        buttonPanel.addExitButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }
}

