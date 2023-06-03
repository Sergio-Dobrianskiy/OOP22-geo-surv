package it.unibo.geosurv;

import it.unibo.geosurv.controller.menu.MainMenuController;
import it.unibo.geosurv.controller.menu.MenuView;
import it.unibo.geosurv.model.Game;

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
    public static void main(final String args[]) {

        MenuView menuView = new MenuView(); // view
        // Game game = new Game(); // model
        MainMenuController mainMenuController = new MainMenuController(menuView); // controller

        mainMenuController.startMenu(); // game menu

        Game game = mainMenuController.getGame();

        // Start the game
        game.start();
    }
}
