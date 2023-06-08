package it.unibo.geosurv.controller.menu;

import it.unibo.geosurv.model.Game;

public class MainMenuController {
    private final MenuView menuView;
    private Game game;

    public MainMenuController(MenuView menuView) {
        this.menuView = menuView;
        setupButtonListeners();
    }
    
    /* Configure actionListeners */
    private void setupButtonListeners() {
        menuView.getStartButton().addActionListener(e -> startGame());
        menuView.getCloseButton().addActionListener(e -> closeMenu());
    }

    /* Open Menu */
    public void startMenu() {
        menuView.getFrame().setVisible(true);
    }

    /* Close Menu */
    private void closeMenu() {
        menuView.getFrame().dispose();
        System.exit(0);
    }
    
    /* Start to play */
    private void startGame() {
        menuView.getFrame().setVisible(false);
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
