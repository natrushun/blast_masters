package view.state;

import control.Settings;
import model.board.Board;
import model.board.Direction;
import model.board.Size;
import model.board.element.Entity;
import model.board.element.character.Monster;
import model.board.element.character.Player;
import view.ui.GameHUD;
import view.ui.HoverPanel;
import view.ui.PlayerDataPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static model.board.Direction.*;
import static model.board.Direction.UP;
import static model.board.Image.*;

/**
 * The graphical engine for the game, responsible for rendering the game board and handling user input.
 */
public class GameEngine extends JPanel {
    private Board board;
    private Settings settings;
    private Timer frametimer;
    private Image background;
    private Map<Direction, Boolean> Player1Movement;
    private Map<Direction, Boolean> Player2Movement;
    private JButton nextRoundButton;
    private JButton mainMenuButton;
    private HoverPanel hoverPanel;
    private GameHUD gameHUD;

    private PlayerDataPanel playe1Panel;
    private PlayerDataPanel playe2Panel;

    public GameEngine(Board board, Settings settings, JButton mainMenuButton) {
        super();
        Player1Movement = new HashMap<>();
        Player2Movement = new HashMap<>();
        this.mainMenuButton = mainMenuButton;
        this.board = board;
        this.settings = settings;
        background = getBackgroundImage(board.getSelectedMapIndex()).getImage();
        frametimer = new javax.swing.Timer(10, new FrameListener());
        frametimer.start();

        this.mainMenuButton.setVisible(true);

        add(mainMenuButton);
        handleKeyPresses();
        initializeHover();
        initializeNextRoundButton();
        initializePlayerDataPanel();
        gameHUD = new GameHUD(0, 0);
        add(gameHUD);
    }

    private void initializePlayerDataPanel() {
        playe2Panel=new PlayerDataPanel(false);
        playe1Panel=new PlayerDataPanel(true);
        board.setPlayer2DataPanel(playe2Panel);
        board.setPlayer1DataPanel(playe1Panel);
        add(playe1Panel);
        add(playe2Panel);
    }


    private void initializeNextRoundButton() {
        nextRoundButton = new JButton("Next Round");
        nextRoundButton.setBackground(new Color(51, 206, 250));
        nextRoundButton.setForeground(Color.white);
        nextRoundButton.setPreferredSize(new Dimension(300, 50));
        nextRoundButton.setVisible(false);
        nextRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean newNewRound = false;
                if (nextRoundButton.getText().equals("New Round")) {
                    newNewRound = true;
                    gameHUD.updateScores(0,0);
                    gameHUD.restartTimer();
                }
                setHoverPanelInvisible();
                restart(newNewRound);
            }
        });
        add(nextRoundButton);
    }

    private void initializeHover() {
        hoverPanel = new HoverPanel(board.getPlayer1().getPoints(), board.getPlayer1().getPoints());
        hoverPanel.setVisible(false);
        add(hoverPanel);
    }

    private ImageIcon getBackgroundImage(int mapIndex) {
        return switch (mapIndex) {
            case 1 -> new ImageIcon(BACKGROUND_IMG_MAP2.getImageUrl());
            case 2 -> new ImageIcon(BACKGROUND_IMG_MAP3.getImageUrl());
            default -> new ImageIcon(BACKGROUND_IMG_MAP1.getImageUrl());
        };
    }

    /**
     * Paints the game components including the background, entities, and players.
     *
     * @param grphcs the graphics context
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 1520, 807, null);
        ArrayList<Entity> entities = new ArrayList<>(board.getEntities());
        for (Entity entity : entities) {
            entity.draw(grphcs);
        }
        board.getPlayer1().draw(grphcs);
        board.getPlayer2().draw(grphcs);
        ArrayList<Monster> monsters = new ArrayList<>(board.getMonsters());
        for (Monster monster : monsters) {
            monster.draw(grphcs);
        }

        gameHUD.setBounds(0,0, gameHUD.getWidth(), gameHUD.getHeight());

        playe2Panel.setBounds(20,660,playe2Panel.getWidth(), playe2Panel.getHeight());
        playe1Panel.setBounds(getWidth()/2+20,660,playe1Panel.getWidth(), playe1Panel.getHeight());
        playe1Panel.setOpacity(1);
        playe2Panel.setOpacity(1);

        mainMenuButton.setBounds(this.getWidth() - mainMenuButton.getWidth(), 5, mainMenuButton.getWidth(), mainMenuButton.getHeight());

        if (hoverPanel.isVisible()) {

            Graphics2D g2d = (Graphics2D) grphcs;
            float hoverPanelOpacity = 0.7f;
            playe1Panel.setOpacity(hoverPanelOpacity);
            playe2Panel.setOpacity(hoverPanelOpacity);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hoverPanelOpacity));
            g2d.fillRect(0,Size.TILE_HEIGHT.getSize(), 1500, 10*Size.TILE_HEIGHT.getSize() );
            int hoverPanelX = (getWidth() - hoverPanel.getWidth()) / 2;
            int hoverPanelY = (getHeight() - hoverPanel.getHeight()) / 2;

            hoverPanel.setBounds(hoverPanelX, hoverPanelY, hoverPanel.getWidth(), hoverPanel.getHeight());
            nextRoundButton.setBounds(hoverPanelX + hoverPanel.getWidth() / 2 - nextRoundButton.getWidth() / 2, hoverPanelY + hoverPanel.getHeight(), nextRoundButton.getWidth(), nextRoundButton.getHeight());
            float nextButtonOpacity = 1f;
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, nextButtonOpacity);
            g2d.setComposite(alphaComposite);
        }

    }

    /**
     * Handles key presses for player movement and actions.
     */
    public void handleKeyPresses() {

        int[] events = settings.getKeyBindings();

        this.getInputMap().put(KeyStroke.getKeyStroke(events[7], 0), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player1Movement.put(LEFT, true);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[7], 0, true), "released left");
        this.getActionMap().put("released left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player1Movement.put(LEFT, false);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[9], 0), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player1Movement.put(RIGHT, true);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[9], 0, true), "released right");
        this.getActionMap().put("released right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player1Movement.put(RIGHT, false);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[6], 0), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player1Movement.put(UP, true);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[6], 0, true), "released up");
        this.getActionMap().put("released up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player1Movement.put(UP, false);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[8], 0), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player1Movement.put(DOWN, true);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[8], 0, true), "released down");
        this.getActionMap().put("released down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player1Movement.put(DOWN, false);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[1], 0), "pressed a");
        this.getActionMap().put("pressed a", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player2Movement.put(LEFT, true);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[1], 0, true), "released a");
        this.getActionMap().put("released a", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player2Movement.put(LEFT, false);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[3], 0), "pressed d");
        this.getActionMap().put("pressed d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player2Movement.put(RIGHT, true);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[3], 0, true), "released d");
        this.getActionMap().put("released d", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player2Movement.put(RIGHT, false);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[0], 0), "pressed w");
        this.getActionMap().put("pressed w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player2Movement.put(UP, true);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[0], 0, true), "released w");
        this.getActionMap().put("released w", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player2Movement.put(UP, false);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[2], 0), "pressed s");
        this.getActionMap().put("pressed s", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player2Movement.put(DOWN, true);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[2], 0, true), "released s");
        this.getActionMap().put("released s", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Player2Movement.put(DOWN, false);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke(events[5], 0, true), "pressed t");
        this.getActionMap().put("pressed t", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                board.player2PlantsBomb();
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke(events[4], 0, true), "pressed y");
        this.getActionMap().put("pressed y", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                board.player2PlantsBox();
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke(events[10], 0, true), "pressed u");
        this.getActionMap().put("pressed u", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                board.player1PlantsBox();
            }
        });


        this.getInputMap().put(KeyStroke.getKeyStroke(events[11], 0, true), "pressed p");
        this.getActionMap().put("pressed p", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                board.player1PlantsBomb();
            }
        });

    }

    /**
     * ActionListener for updating the game state and rendering the frame.
     */
    class FrameListener implements ActionListener {
        private final int TARGET_FPS = 120;
        private final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        private long lastUpdateTime;

        @Override
        public void actionPerformed(ActionEvent ae) {
            long now = System.nanoTime();
            lastUpdateTime = now;

            repaint();
            board.statusCheck();
            handleGameState(board.getGameState());

            try {
                long sleepTime = (lastUpdateTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the movement of a player based on the key input.
     *
     * @param player         the player to move
     * @param playerMovement the map of player movement directions
     */
    private void handlePlayerMovement(Player player, Map<Direction, Boolean> playerMovement) {
        ArrayList<Direction> moves = new ArrayList<>();
        for (Direction d : playerMovement.keySet()) {
            if (playerMovement.get(d)) {
                moves.add(d);
            }
        }
        if (!moves.isEmpty()) {
            player.move(moves.get(0));
        }
    }

    /**
     * Handles the game state transitions and actions based on the current state.
     *
     * @param state the current game state
     */
    private void handleGameState(GameState state) {
        switch (state) {
            case DRAW:
                board.removeRemovableEntities();
                setHoverPanelVisible("Draw", false);
                break;
            case PLAYER2_FINAL_WIN:
                board.removeRemovableEntities();
                setHoverPanelVisible(settings.getPlayer2Name()+" won the game", true);
                break;
            case PLAYER1_FINAL_WIN:
                board.removeRemovableEntities();
                setHoverPanelVisible(settings.getPlayer1Name()+" won the game",true);
                break;
            case PLAYER1_WON:
                board.removeRemovableEntities();
                setHoverPanelVisible(settings.getPlayer1Name() + " won the round", false);
                break;
            case PLAYER2_WON:
                board.removeRemovableEntities();
                setHoverPanelVisible(settings.getPlayer2Name() + " won the round", false);
                break;
            case BOTH_ALIVE:
                board.removeRemovableEntities();
                handlePlayerMovement(board.getPlayer1(), Player1Movement);
                handlePlayerMovement(board.getPlayer2(), Player2Movement);
                board.moveMonsters();
                break;
            default:
        }

    }

    private void restart(boolean newNewRound) {
        board.reset(newNewRound);
        remove(playe2Panel);
        remove(playe1Panel);
        initializePlayerDataPanel();
    }
    private void setHoverPanelVisible(String stateLabel, boolean newRound) {
        //stopRunningTimers();

        gameHUD.stopTimer();
        gameHUD.updateScores(board.getPlayer1().getPoints(), board.getPlayer2().getPoints());
        hoverPanel.setScore(board.getPlayer2().getPoints(), board.getPlayer1().getPoints(), stateLabel);
        hoverPanel.setVisible(true);
        nextRoundButton.setText("Next Round");
        nextRoundButton.setVisible(true);
        if (newRound) {
            nextRoundButton.setText("New Round");
        }
    }

    private void setHoverPanelInvisible() {
        gameHUD.restartStoppedTimer();
        hoverPanel.setVisible(false);
        nextRoundButton.setVisible(false);
    }
}



