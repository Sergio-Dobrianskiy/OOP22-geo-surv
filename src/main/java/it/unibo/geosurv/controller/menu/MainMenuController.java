package it.unibo.geosurv.controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.geosurv.model.Game;

public class MainMenuController {
    private MenuView menuView;
    private Game game;

    public MainMenuController(MenuView menuView) {
        this.menuView = menuView;
        // this.game = game; //TODO: add in contructor
        // collego gli actionListener
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

    public void startGame() {
        // Logica per avviare il gioco

        Game n = new Game(); // game.start(); //TODO: change once in constructor
        n.start();
    }

    public void closeApplication() {
        System.exit(0);
    }

    public void startMenu() {
        menuView.setVisible(true);
    }

}
