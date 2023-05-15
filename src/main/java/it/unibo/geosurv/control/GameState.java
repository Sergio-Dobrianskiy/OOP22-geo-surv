package it.unibo.geosurv.control;


/**
 * States the game can be in.
 */
public enum GameState {
	/**
	 * Game is loading assets.
	 */
	LOADING,
	/**
	 * Game is on menu page.
	 */
	MENU,
	/**
	 * Game is running, player is playing.
	 */
	RUNNING,
	/**
	 * Game is in pause.
	 */
	PAUSE,
	/**
	 * Player won.
	 */
	WON,
	/**
	 * Player lost.
	 */
	LOST,
}
