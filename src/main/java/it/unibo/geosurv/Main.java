package it.unibo.geosurv;

import it.unibo.geosurv.controller.menu.MainMenuController;
import it.unibo.geosurv.controller.menu.MenuView;

/**
 * Main class for this game.
 */
public final class Main {

    // prevent instance creation
    private Main() {

    }

    /**
     * Start method of this application.
     *
     * @param args parameters for the game
     */
    public static void main(final String[] args) {

        final MenuView menuView = new MenuView(); // view
        // Game game = new Game(); // model
        final MainMenuController mainMenuController = new MainMenuController(menuView); // controller

        mainMenuController.startMenu(); // game menu

        //Game game = mainMenuController.getGame();

        // Start the game
        //game.start();
    }
}
