package it.unibo.geosurv.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import it.unibo.geosurv.graphics.*;

import it.unibo.geosurv.weapons.SatelliteGun;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;

	//// debug
	private int fps;
	private boolean showFps = true;
	private int objectsCounter;
	private boolean showObjectsCounter = true;

	public Game() {

		new Window(1000, 563, "Geo Survival", this);

		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));

		loadTextures();
		loadLevel(Texture.SMALL_MAP.getTexture());

		camera = new Camera(0, 0, handler);
		handler.addObject(new SatelliteGun(0, 0, this.handler, this));
		// TODO: remove sample experience object
		handler.addObject(new Experience(50, 50, ID.Experience, 3));

		start();
	}

	private synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta > 1) {
				tick();
				render();
				delta--;
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				this.fps = frames;
				this.objectsCounter = handler.getObjectsSize();
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	public void tick() {
		handler.tick();
		camera.tick();
	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		///////////////////////////////////// below here we draw to the game
		g.setColor(Color.white);
		g.fillRect(0, 0, 1000, 563);

		if (this.showFps == true) {
			g.setColor(Color.BLUE);
			g.drawString("FPS: " + this.fps, 900, 50);
		}
		if (this.showObjectsCounter == true) {
			g.setColor(Color.BLUE);
			g.drawString("Objects: " + this.objectsCounter, 900, 65);
		}

		g2d.translate(-camera.getX(), -camera.getY());

		handler.render(g);
		////////////////////////////////////// above here we draw to the game

		g.dispose();
		bs.show();
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
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (blue == 255) {
					handler.addObject(new Block(xx * 32, yy * 32, ID.Block));
				}

				if (red == 255) {
					handler.addPlayer(new MainPlayer(xx * 32, yy * 32, ID.Player, handler));
				}
			}
		}
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

	public static void main(String args[]) {
		new Game();
	}
}