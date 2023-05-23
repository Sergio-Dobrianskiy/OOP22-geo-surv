package it.unibo.geosurv.view.graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;

public class MenuView extends JFrame {

    private static final long serialVersionUID = 1L;
	private JFrame frame;
    private JButton startButton;
    private JButton closeButton;

    public MenuView() {

        frame = new JFrame("Geo-Survivors");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();

        startButton = new JButton("Start");
        closeButton = new JButton("Close");
        JLabel note = new JLabel("Press g for debug");

        panel.add(startButton);
        panel.add(closeButton);
        panel.add(note);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JFrame getFrame() {
        return frame;
    }

}
