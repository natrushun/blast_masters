package view.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents a panel for selecting maps.
 * The MapSelectorPanel class provides a panel with a map selector combo box
 * and a preview of the selected map.
 */
public class MapSelectorPanel extends JPanel {
    private JLabel mapPreviewLabel;
    private JComboBox<String> mapComboBox;

    /**
     * Constructs a MapSelectorPanel with the given map names, map images, and an action listener.
     *
     * @param mapNames       an array of map names to display in the combo box
     * @param mapImages      an array of map preview images corresponding to the map names
     * @param actionListener the ActionListener to handle events when selecting a map
     */
    public MapSelectorPanel(String[] mapNames, Image[] mapImages, ActionListener actionListener) {


        setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
        setOpaque(false);

        JLabel mapLabel = new JLabel("Map selector");
        mapLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        labelPanel.add(mapLabel);
        labelPanel.setOpaque(false);

        mapPreviewLabel = new JLabel(new ImageIcon(mapImages[0]),JLabel.CENTER);
        mapPreviewLabel.setPreferredSize(new Dimension(600, 400));

        JPanel previewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        previewPanel.add(mapPreviewLabel);
        previewPanel.setOpaque(false);



        mapComboBox = new JComboBox<>(mapNames);
        mapComboBox.addActionListener(actionListener);
        mapComboBox.setFont(new Font("Arial", Font.PLAIN, 25));
        mapComboBox.setBackground(Color.LIGHT_GRAY);
        mapComboBox.setPreferredSize(new Dimension(300, 50));
        ((JLabel)mapComboBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        comboPanel.add(mapComboBox);
        comboPanel.setOpaque(false);


        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        add(Box.createVerticalGlue());
        add(labelPanel);
        add(previewPanel);
        add(comboPanel);
        add(Box.createVerticalGlue());

    }


    public int getCurrentMapIndex() {
        return mapComboBox.getSelectedIndex();
    }

    public void setMapPreview(Image image) {
        mapPreviewLabel.setIcon(new ImageIcon(image));
    }
}

