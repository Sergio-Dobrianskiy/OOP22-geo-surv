package it.unibo.geosurv.view.graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuView extends JFrame {

    private JFrame frame;
    private JButton startButton;
    private JButton closeButton;

    public menuView() {
        
        frame = new JFrame("Geo-Survivors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();

        startButton = new JButton("Start");
        closeButton = new JButton("Close");

        panel.add(startButton);
        panel.add(closeButton);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
