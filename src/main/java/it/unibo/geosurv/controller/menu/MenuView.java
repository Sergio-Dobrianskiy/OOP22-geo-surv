package it.unibo.geosurv.controller.menu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;

/**
 * TODO: javadoc.
 */
public class MenuView extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JFrame frame;
    private final JButton startButton;
    private final JButton closeButton;

    /**
     * User menù.
     */
    public MenuView() {

        frame = new JFrame("Geo-Survivors");
        frame.setPreferredSize(new Dimension(1000, 600)); // TODO: magic numbers
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        final JPanel panel = new JPanel();

        startButton = new JButton("Start");
        closeButton = new JButton("Close");
        final JLabel note = new JLabel("Press g for debug");

        panel.add(startButton);
        panel.add(closeButton);
        panel.add(note);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

    }

    /**
     * @return the start button
     */
    public JButton getStartButton() {
        return startButton;
    }

    /**
     * @return the close button
     */
    public JButton getCloseButton() {
        return closeButton;
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

}
