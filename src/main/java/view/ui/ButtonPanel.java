package view.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Represents a panel containing buttons the start menu.
 * The ButtonPanel class provides buttons for starting the game, exiting the game,
 * and selecting the number of rounds to win. It also displays a title image for
 * the menu.
 */
public class ButtonPanel extends JPanel {
    private JLabel titleLabel;
    private JButton startButton;
    private JButton exitButton;
    private JSpinner roundSpinner;

    /**
     * Constructs a new ButtonPanel with buttons and settings.
     * Initializes the title label, start button, exit button, and round panel.
     */
    public ButtonPanel(int value) {
        setLayout(new GridLayout(4, 1));
        setOpaque(false);

        initializeTitleLabel();

        initializeStartButton();

        initializeExitButton();

        initializeRoundPanel(value);
    }

    /**
     * Initializes the title label for the ButtonPanel.
     * Loads the title image from the specified file and sets it as the icon for the label.
     * The title label is centered horizontally within the panel.
     * If an IOException occurs while reading the image, the stack trace is printed.
     */
    private void initializeTitleLabel() {
        try {
            Image titleImage = ImageIO.read(new File("src/main/resources/assets/menu/blastmasterstitlesmall.png"));
            titleLabel = new JLabel(new ImageIcon(titleImage));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(titleLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the start button for the ButtonPanel.
     * Creates a JButton with the specified text, font, background color, and foreground color.
     * Adds the start button to the panel.
     */
    private void initializeStartButton() {
        startButton = createButton("Start");
        add(startButton);
    }

    /**
     * Initializes the exit button for the ButtonPanel.
     * Creates a JButton with the specified text, font, background color, and foreground color.
     * Adds the exit button to the panel.
     */
    private void initializeExitButton() {
        exitButton = createButton("Exit");
        add(exitButton);
    }

    /**
     * Creates a JButton with the specified text.
     * Sets the font, background color, and foreground color for the button.
     *
     * @param text the text to be displayed on the button
     * @return the created JButton
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
        button.setBackground(new Color(51, 206, 250));
        button.setForeground(Color.white);
        return button;
    }

    /**
     * Initializes the panel for selecting the number of rounds to win.
     * Creates a JPanel with a centered flow layout, sets its opacity to false,
     * and adds labels for "First to" and "wins" along with a JSpinner for selecting the number of rounds.
     * Adds the round panel to the ButtonPanel.
     */
    private void initializeRoundPanel(int value) {
        JPanel roundPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        roundPanel.setOpaque(false);
        JLabel roundsLabel = createLabel("Best of");
        roundSpinner = new JSpinner(new SpinnerNumberModel(value, 1, 10, 1));
        roundSpinner.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
        roundPanel.add(roundsLabel);
        roundPanel.add(roundSpinner);
        roundPanel.add(createLabel("games"));
        add(roundPanel);
    }

    /**
     * Creates a JLabel with the specified text.
     * Sets the font for the label.
     *
     * @param text the text to be displayed on the label
     * @return the created JLabel
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        return label;
    }

    /**
     * Adds an ActionListener to the start button.
     *
     * @param listener the ActionListener to be added
     */
    public void addStartButtonActionListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    /**
     * Adds an ActionListener to the exit button.
     *
     * @param listener the ActionListener to be added
     */
    public void addExitButtonActionListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    /**
     * Gets the number of rounds to win from the spinner.
     *
     * @return the number of rounds to win
     */
    public int getRoundsToWin() {
        return (int) roundSpinner.getValue();
    }
}
