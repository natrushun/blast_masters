package view.ui;

import model.board.Size;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class GameHUD extends JPanel {
    private JLabel player1ScoreLabel;
    private JLabel player2ScoreLabel;
    private JLabel elapsedTimeLabel;
    private Timer timer;
    private int seconds;

    public GameHUD(int score1, int score2) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1520, Size.TILE_HEIGHT.getSize()));

        // Score Labels
        player2ScoreLabel = createScoreLabel(Integer.toString(score2));

        player1ScoreLabel = createScoreLabel(Integer.toString(score1));

        // Timer Label
        elapsedTimeLabel = new JLabel("00:00");
        elapsedTimeLabel.setPreferredSize(new Dimension(120, Size.TILE_HEIGHT.getSize()));
        elapsedTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        elapsedTimeLabel.setVerticalAlignment(JLabel.CENTER);
        elapsedTimeLabel.setOpaque(true);
        elapsedTimeLabel.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel();
        centerPanel.add(player2ScoreLabel, BorderLayout.WEST);
        centerPanel.add(elapsedTimeLabel, BorderLayout.CENTER);
        centerPanel.add(player1ScoreLabel, BorderLayout.EAST);

        setOpaque(false);

        centerPanel.setOpaque(false);
        centerPanel.setPreferredSize(new Dimension(300, Size.TILE_HEIGHT.getSize()));
        add(centerPanel, BorderLayout.CENTER);

        seconds = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                updateTimeLabel();
            }
        });
        timer.start();

        setFocusable(true);
        requestFocusInWindow();

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "stopTimer");
        getActionMap().put("stopTimer", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer.isRunning()) {
                    timer.stop();
                } else {
                    timer.start();
                }
            }
        });

    }

    private JLabel createScoreLabel(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(90, 35));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        label.setBorder(emptyBorder);
        return label;
    }

    public void updateScores(int score1, int score2) {
        player1ScoreLabel.setText(Integer.toString(score1));
        player2ScoreLabel.setText(Integer.toString(score2));
    }

    public void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    public void restartTimer() {
        if (timer != null && !timer.isRunning()) {
            seconds = 0;
            updateTimeLabel();
            timer.start();
        }
    }

    public void restartStoppedTimer() {
        if (timer != null && !timer.isRunning()) {
            timer.start();
        }
    }

    private void updateTimeLabel() {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        String formattedTime = String.format("%02d:%02d", minutes, remainingSeconds);
        elapsedTimeLabel.setText(formattedTime);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(152, 0, 0));
        g2d.fillRoundRect(player2ScoreLabel.getX()-5,player2ScoreLabel.getY()-5,player2ScoreLabel.getWidth()+10, player2ScoreLabel.getHeight()+10,3,3);
        g.setColor(new Color(214,0,0));
        g2d.fillRoundRect(player2ScoreLabel.getX(),player2ScoreLabel.getY(),player2ScoreLabel.getWidth(), player2ScoreLabel.getHeight(),3,3);
        g.setColor(new Color(38, 108, 116));
        g2d.fillRoundRect(player1ScoreLabel.getX()-5,player1ScoreLabel.getY()-5,player1ScoreLabel.getWidth()+10, player1ScoreLabel.getHeight()+10,3,3);
        g.setColor(new Color(50, 152, 163));
        g2d.fillRoundRect(player1ScoreLabel.getX(),player1ScoreLabel.getY(),player1ScoreLabel.getWidth(), player1ScoreLabel.getHeight(),3,3);
    }
}