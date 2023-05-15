package it.unibo.geosurv.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import it.unibo.geosurv.control.GameState;
import it.unibo.geosurv.control.KeyInput;
import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.monsters.MonsterSpawner;
import it.unibo.geosurv.view.graphics.Camera;
import it.unibo.geosurv.view.graphics.TextureRender;
import it.unibo.geosurv.view.graphics.Window;

public class Game extends Canvas implements Runnable, TickingObject {

	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	private static final int SECOND_IN_MILLI = 1000;
	public static final double SECOND_IN_NANO = 1_000_000_000d;
	private static final double TICKS_PER_SECOND = 60d;
	private static final double NANO_PER_TICK = SECOND_IN_NANO / TICKS_PER_SECOND;
	private static final int FRAMES_IN_BUFFER = 3;

	private boolean isRunning = false;
	private Thread thread;
	private static Handler handler;
	private TextureRender textureRender;
	private final Camera camera;
	private final Loader loader;
	private static long startTime;
	private GameState state;

	//// debug
	private int fps;
	private boolean showDebug = true;
	private int objectsCounter;

	public Game() {
		this.state = GameState.LOADING;
		new Window(WINDOW_WIDTH, WINDOW_HEIGHT, "Geo Survival", this);

		handler = new Handler();
		loader = new Loader(handler);
		textureRender = new TextureRender(handler);

		this.loader.loadAll(); // loads Player, textures, weapons, level
		this.addKeyListener(new KeyInput(this, handler));

		camera = loader.loadCamera(); // loads camera
		startTime = System.currentTimeMillis();
		handler.addObject(new MonsterSpawner(0, 0, handler, this)); // TODO: move to Loader?

		start(); // starts threads
	}

	public static long getStartTime() {
		return startTime;
	}

	public static Handler returnHandler() {
		return handler;
	};

	private synchronized void start() {
		this.state = GameState.RUNNING;
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
		long timer = System.currentTimeMillis();
		double delta = 0;
		int frames = 0;

		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / NANO_PER_TICK;
			lastTime = now;
			while (delta > 1) {
				tick();
				render();
				delta--;
				frames++;
			}

			if (System.currentTimeMillis() - timer > SECOND_IN_MILLI) {
				this.fps = frames;
				this.objectsCounter = handler.getObjectsSize();
				timer += SECOND_IN_MILLI;
				frames = 0;
			}
		}
		stop();
	}

	public void tick() {
		if (state == GameState.RUNNING) {
			handler.tick();
		}
		camera.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(FRAMES_IN_BUFFER);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		///////////////////////////////////// below here we draw to the game
		g.setColor(Color.black);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

		if (this.showDebug == true) {
			g.setColor(Color.RED);
			g.drawString("FPS: " + this.fps, 850, 50);
			g.drawString("Objects: " + this.objectsCounter, 850, 65);
			g.drawString("Experience: " + Experience.getExperienceCounter(), 850, 80);
			g.drawString("Monsters: " + Monster.getMonstersCounter(), 850, 95);
			g.drawString("Player Exp: " + handler.getPlayer().getExperience(), 850, 110);
			g.drawString("Player Life: " + handler.getPlayer().getLife(), 850, 125);
			g.drawString("Time: " + (((int) ((System.currentTimeMillis() / 1000))) - startTime / 1000), 850, 140);
		}

		g2d.translate(-camera.getX(), -camera.getY());

		textureRender.render(g);
		////////////////////////////////////// above here we draw to the game

		g.dispose();
		bs.show();
	}

	
	public void pause() {
		if (this.state == GameState.RUNNING) {
            this.state = GameState.PAUSE;
        } else if (this.state == GameState.PAUSE) {
            this.state = GameState.RUNNING;
        }
	}

}