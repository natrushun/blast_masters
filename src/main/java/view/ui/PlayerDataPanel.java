package view.ui;

import model.board.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerDataPanel extends JPanel {
    private int imageWidth = 50;
    private int imageHeight = 50;
    private JLabel rangeNumberLabel;
    private JLabel bombNumberLabel;
    private JLabel boxNumberLabel;
    private String[] dynamicBonusName;
    private HashMap<String, LineTimerPanel> lineTimers = new HashMap<>();
    private HashMap<String, JLabel> staticLabels = new HashMap<>();
    private java.awt.Image background;
    private java.awt.Image tableBackground;

    private JPanel tablePanel;
    private JPanel bonusesContainer;
    private HashMap<LineTimerPanel, Boolean> timers = new HashMap<>();
    private float opacity = 1f;

    /**
     * A panel to display player data including bombs, range, boxes, and bonuses.
     * Can be mirrored for multiple players display.
     */
    public PlayerDataPanel(boolean mirror) {
        background = new ImageIcon(Image.PLAYER_DATA_BG_IMG.getImageUrl()).getImage();
        tableBackground = new ImageIcon(Image.PLAYER_DATA_TABLE_IMG.getImageUrl()).getImage();
        int[] dynamicBonusTime = new int[]{5, 10, 10, 10, 5, 15};
        dynamicBonusName = new String[]{"SlowDown", "Ghost", "Immortality", "Immediately", "Pacifist", "SmallRange"};

        JPanel bombPanel = new JPanel(new GridLayout(2, 1));
        JPanel Container = new JPanel(new GridLayout(1, 3));
        JPanel boxPanel = new JPanel(new GridLayout(2, 1));
        JPanel rangePanel = new JPanel(new GridLayout(2, 1));
        JPanel rowPanel = new JPanel();
        bonusesContainer = new JPanel(new BorderLayout());
        tablePanel = new JPanel(new GridLayout(2, 3, 8, 8));

        bonusesContainer.setPreferredSize(new Dimension(330, 120));

        bombPanel.setOpaque(false);
        Container.setOpaque(false);
        boxPanel.setOpaque(false);
        rangePanel.setOpaque(false);
        rowPanel.setOpaque(false);
        bonusesContainer.setOpaque(false);
        tablePanel.setOpaque(false);

        ImageIcon imageIconBomb = resizeImage(Image.BOMB_IMG.getImageUrl());
        JLabel imageLabelBomb = new JLabel(imageIconBomb);
        imageLabelBomb.setVerticalAlignment(JLabel.CENTER);
        bombPanel.add(imageLabelBomb);

        bombNumberLabel = new JLabel("1");
        bombNumberLabel.setForeground(Color.WHITE);
        bombNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        bombPanel.add(bombNumberLabel);
        bombPanel.setBorder(new EmptyBorder(0, 0, 0, 20));

        ImageIcon imageIconRange = resizeImage(Image.BIGGER_RANGE_BONUS_IMG.getImageUrl());
        JLabel imageLabelRange = new JLabel(imageIconRange);
        rangePanel.add(imageLabelRange);

        rangeNumberLabel = new JLabel("2");
        rangeNumberLabel.setForeground(Color.WHITE);
        rangeNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        rangePanel.add(rangeNumberLabel);
        rangePanel.setBorder(new EmptyBorder(0, 0, 0, 10));

        ImageIcon imageIconBox = resizeImage(Image.BOX_IMG_MAP1.getImageUrl());
        JLabel imageLabelBox = new JLabel(imageIconBox);
        bombNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        boxPanel.add(imageLabelBox);

        boxNumberLabel = new JLabel("0");
        boxNumberLabel.setForeground(Color.WHITE);
        boxNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        boxPanel.add(boxNumberLabel);

        String[] staticNames = new String[]{"Roller", "Detonator"};
        int j = 0;
        for (String path : Image.STATIC_BONUSES_IMG.getImageUrls()) {
            ImageIcon rowImageIcon = resizeImage(path);
            JLabel rowLabel = new JLabel(rowImageIcon);
            rowLabel.setVisible(false);

            staticLabels.put(staticNames[j], rowLabel);
            j++;
            rowPanel.add(rowLabel);
        }
        imageHeight = 40;
        imageWidth = 40;
        for (String path : Image.DYNAMIC_BONUSES_IMG.getImageUrls()) {
            JPanel dynamicContainer = new JPanel();
            dynamicContainer.setOpaque(false);
            dynamicContainer.setPreferredSize(new Dimension(100, 50));
            dynamicContainer.setBackground(Color.LIGHT_GRAY);

            ImageIcon tableImageIcon = resizeImage(path);
            JLabel tableLabel = new JLabel(tableImageIcon);


            dynamicContainer.add(tableLabel);
            tablePanel.add(dynamicContainer);
        }


        setLayout(new BorderLayout());

        Container.setBorder(new EmptyBorder(10, 10, 5, 0));
        Container.add(bombPanel);
        Container.add(rangePanel);
        Container.add(boxPanel);
        if (mirror) {
            add(Container, BorderLayout.EAST);
        } else {
            add(Container, BorderLayout.WEST);
        }


        bonusesContainer.add(tablePanel, BorderLayout.CENTER);
        bonusesContainer.setBorder(new EmptyBorder(5, 0, 5, 0));

        rowPanel.setBorder(new EmptyBorder(25, 0, 0, 0));
        add(rowPanel, BorderLayout.CENTER);
        if (mirror) {
            add(bonusesContainer, BorderLayout.WEST);
        } else {
            add(bonusesContainer, BorderLayout.EAST);
        }

        setBorder(new EmptyBorder(0, 0, 0, 0));
        setPreferredSize(new Dimension(700, 120));

        int i = 0;
        for (Component component : tablePanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                LineTimerPanel lineTimerPanel = new LineTimerPanel(dynamicBonusTime[i]);
                lineTimerPanel.setPreferredSize(new Dimension(40, 10));
                timers.put(lineTimerPanel, false);
                panel.add(lineTimerPanel);

                lineTimers.put(dynamicBonusName[i], lineTimerPanel);
                i++;

            }
        }

    }

    public void showBonus(String s) {
        staticLabels.get(s).setVisible(true);
    }


    public void refreshBoxLabel(int value) {
        boxNumberLabel.setText(String.valueOf(value));
    }

    public void refreshBombLabel(int value) {
        bombNumberLabel.setText(String.valueOf(value));
    }

    public void refreshRangeLabel(int value) {
        rangeNumberLabel.setText(String.valueOf(value));
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }

    private ImageIcon resizeImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        java.awt.Image image = imageIcon.getImage();

        java.awt.Image resizedImage = image.getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void startLineTimer(String s) {
        lineTimers.get(s).startTimer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);



        for (Component component : tablePanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                g.drawImage(tableBackground, panel.getX() + bonusesContainer.getX(), panel.getY() + 3, panel.getWidth(), panel.getHeight(), null);
            }
        }
    }
}
