package view.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A custom JPanel for displaying a timer line that decreases over time.
 */
public class LineTimerPanel extends JPanel {
    private int lineLength;
    private final int length=40;
    private Timer timer;

    /**
     * Constructs a LineTimerPanel with the given duration in seconds.
     * Initializes the line length and timer settings based on the duration.
     * @param sec The duration of the timer in seconds
     */
    public LineTimerPanel(int sec) {
        lineLength = length;
        int iteration= sec*1000/lineLength;

        timer = new Timer(iteration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (lineLength > 0) {
                    lineLength -= 1;
                    repaint();
                } else {
                    timer.stop();

                }
            }
        });

    }
    public void startTimer(){
        lineLength =length;
        timer.restart();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int centerY = getHeight() / 2;

        int strokeWidth = 10;
        g2d.setStroke(new BasicStroke(strokeWidth));

        g2d.setColor(Color.BLACK);
        if(timer.isRunning() && lineLength>0) g2d.drawLine(0, centerY, lineLength, centerY);

    }
}
