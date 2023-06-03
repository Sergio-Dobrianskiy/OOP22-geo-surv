package it.unibo.geosurv.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.geosurv.model.Game;

public class MainMenuController {
    private MenuView menuView;
    private Game game;

    public MainMenuController(MenuView menuView) {
        this.menuView = menuView;

        // Coonect the actionListeners
        menuView.getStartButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        menuView.getCloseButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeApplication();
            }
        });
    }

    /* Open Menu */
    public void startMenu() {
        menuView.setVisible(true);
    }

    /* Close Menu */
    private void closeMenu() {
        menuView.getFrame().dispose();
        System.exit(0);
    }
    
    /* Start to play */
    private void startGame() {
        menuView.setVisible(false);
        game = new Game();
        game.start();
    }

    public void closeApplication() {
        System.exit(0);
    }

    public Game getGame() {
        return game;
    }

}
