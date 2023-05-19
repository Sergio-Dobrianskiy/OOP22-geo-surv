package it.unibo.geosurv.model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import it.unibo.geosurv.control.weapons.AutoGun;
import it.unibo.geosurv.control.weapons.ExplosionGun;
import it.unibo.geosurv.control.weapons.LaserGun;
import it.unibo.geosurv.control.weapons.SatelliteGun;
import it.unibo.geosurv.model.monsters.MonsterSpawner;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.model.walls.blocks.Block;
import it.unibo.geosurv.view.graphics.Camera;
import it.unibo.geosurv.view.graphics.Texture;

public class Loader {

	protected final static int GAME_GRID_HEIGHT = 32;
	protected final static int GAME_GRID_WIDTH = 32;
	private Handler handler;

	public Loader(Handler handler) {
		this.handler = handler;
	}

	public void loadAll() {
		handler.addPlayer(new MainPlayer(150, 300, handler)); // TODO: remove?
		this.loadTextures();
		this.loadGuns();
		loadLevel(Texture.SMALL_MAP.extractTexture());
		// loadLevel(Texture.TEST_MAP.getTexture());
		// loadLevel(Texture.BIG_MAP_2.getTexture());

	}

	public Camera loadCamera() {
		return new Camera(handler.getPlayer().getX(), handler.getPlayer().getY(), handler);
	}

	/**
	 * Loads game textures
	 */
	private void loadTextures() {
		for (final Texture texture : Texture.values()) {
			try {
				texture.load();
			} catch (IOException e) {
				System.out.println("Error while loading texture " + texture.getPath());
				System.out.println(e);
				System.exit(1);
			}
		}
	}

	/**
	 * Loads game Weapons/Guns
	 */
	private void loadGuns() {
		handler.addTickingObject(new AutoGun(this.handler));
		handler.addTickingObject(new SatelliteGun(this.handler));
		// handler.addTickingObject(new ExplosionGun(this.handler));
		// handler.addTickingObject(new LaserGun(this.handler));
	}

	/**
	 * Load the game world.
	 *
	 * @param image that models the game world
	 */
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				// int red = (pixel >> 16) & 0xff;
				// green not used at the moment
				// int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (blue == 255) {
					handler.addObject(new Block(xx * GAME_GRID_WIDTH, yy * GAME_GRID_HEIGHT));
				}

				// if (red == 255) {
				// // handler.addPlayer(new MainPlayer(xx * GAME_GRID_WIDTH, yy *
				// GAME_GRID_HEIGHT,
				// // ID.Player, handler));
				// }
			}
		}
	}

}
