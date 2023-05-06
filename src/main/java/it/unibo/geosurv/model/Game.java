package it.unibo.geosurv.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import it.unibo.geosurv.control.KeyInput;
import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.monsters.MonsterSpawner;
import it.unibo.geosurv.model.monsters.triangle.Triangle;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;
import it.unibo.geosurv.model.walls.blocks.Block;
import it.unibo.geosurv.model.weapons.Satellite;
import it.unibo.geosurv.model.weapons.SatelliteGun;
import it.unibo.geosurv.view.graphics.*;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private boolean isRunning = false;
	private Thread thread;
	private static Handler handler;
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
		// loadLevel(Texture.TEST_MAP.getTexture());
		// loadLevel(Texture.BIG_MAP_2.getTexture());

		// TODO: remove sample experience object
		handler.addPlayer(new MainPlayer(180, 300, ID.Player, handler));
		handler.addObject(new SatelliteGun(0, 0, this.handler, this));
		handler.addObject(new Experience(50, 50, ID.Experience, 1));
		handler.addObject(new Triangle(200, 100, ID.Monster, this.handler, this));
		handler.addObject(new Triangle(280, 150, ID.Monster, this.handler, this));
		handler.addObject(new MonsterSpawner(0, 0, ID.Monster, this.handler, this));
		// MonsterSpawner.spawnMonsters(this.handler);
		// camera position above this line makes some objects null

		// randomPOint example
		/*
		 * for (int i = 0; i < 1000; i++) {
		 * float x, y;
		 * Pair<Float, Float> pair = Func.randomPoint(100f, 350f);
		 * GameObject tempPlayer = handler.getPlayer();
		 * x = tempPlayer.getX() + pair.getX();
		 * y = tempPlayer.getY() + pair.getY();
		 * handler.addObject(new Satellite(x, y, handler));
		 * }
		 */

		camera = new Camera(0, 0, handler);
		start();
	}

	public static Handler returnHandler() {
		return handler;
	};

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
		// System.out.println(Thread.currentThread().getName() + ": is running");
		// TODO: capire bene la differenza fra qui e sopra per il discorso di creare
		// mostri nel tempo, o usare più thread per mostri diversi
		// handler.addObject(new MonsterSpawner(0, 0, ID.Monster, handler, this));
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
			g.drawString("Experience: " + Experience.getExperienceCounter(), 900, 80);
			g.drawString("Monsters: " + Monster.getMonstersCounter(), 900, 95);
			g.drawString("Play Exp: " + handler.getPlayer().getExperience(), 900, 110);
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
				// green not used at the moment
				// int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (blue == 255) {
					handler.addObject(new Block(xx * 32, yy * 32));
				}

				if (red == 255) {
					// handler.addPlayer(new MainPlayer(xx * 32, yy * 32, ID.Player, handler));
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
}