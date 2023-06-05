package it.unibo.geosurv.controller.menu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

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

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                Image backgroundMenu = new ImageIcon(getClass().getResource("/BackgroundMenu.jpg")).getImage();
                // Draw the background image
                g.drawImage(backgroundMenu, 0, 0, null);
            }
        };

        panel.setLayout(new BorderLayout());

        /* Title */
        JLabel title = new JLabel("Geo-Survivors");

        /* Setting title  */
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        panel.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();

        /* Setting buttonPanel */
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        /* Setting startButton */
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(150, 50));
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(startButton);

        /* Setting closeButton */
        closeButton = new JButton("Close");
        closeButton.setPreferredSize(new Dimension(150, 50));
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        buttonPanel.add(closeButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        /* For debug 
        JLabel note = new JLabel("Press 'g' for debug");

        note.setFont(new Font("Arial", Font.PLAIN, 14));
        note.setHorizontalAlignment(JLabel.CENTER);
        panel.add(note, BorderLayout.SOUTH);

        */

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

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
