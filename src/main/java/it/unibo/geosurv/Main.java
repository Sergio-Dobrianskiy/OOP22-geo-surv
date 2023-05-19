package it.unibo.geosurv;

import it.unibo.geosurv.controller.menu.MainMenuController;
import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.view.graphics.MenuView;

public final class Main {

	// prevent instance creation
	private Main() {
	}

	public static void main(String args[]) {

		MenuView menuView = new MenuView(); // view
		// Game game = new Game(); // model
		MainMenuController mainMenuController = new MainMenuController(menuView); // controller

		// new Game();
		mainMenuController.startMenu(); // game menu
	}
}
