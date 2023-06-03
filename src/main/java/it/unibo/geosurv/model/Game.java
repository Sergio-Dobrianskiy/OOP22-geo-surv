package it.unibo.geosurv.model;

import java.awt.Canvas;
import java.awt.Color;
import jaav.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import it.unibo.geosurv.control.GameState;
import it.unibo.geosurv.control.KeyInput;
import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.model.loader.ILoader;
import it.unibo.geosurv.model.loader.Loader;
import it.unibo.geosurv.model.monsters.MonsterSpawner;
import it.unibo.geosurv.view.graphics.Camera;
import it.unibo.geosurv.view.graphics.TextureRender;
import it.unibo.geosurv.view.graphics.Window;

/**
 * Represents the main Game class.
 */
public class Game extends Canvas implements Runnable, TickingObject {

    private static final long serialVersionUID = 1L;
    /**
     * Game's window width.
     */
    public static final int WINDOW_WIDTH = 1000;
    /**
     * Game's window height.
     */
    public static final int WINDOW_HEIGHT = 600;
    /**
     * milliseconds in a second.
     */
    private static final int SECOND_IN_MILLI = 1000;
    /**
     * nanoseconds in a second.
     */
    public static final double SECOND_IN_NANO = 1_000_000_000d;
    /**
     * ticks per second.
     */
    private static final double TICKS_PER_SECOND = 60d;
    /**
     * nanoseconds in a tick.
     */
    private static final double NANO_PER_TICK = SECOND_IN_NANO / TICKS_PER_SECOND;
    private static final int FRAMES_IN_BUFFER = 3;

    private boolean isRunning = false;
    private Thread thread;
    private final Handler handler;
    private TextureRender textureRender;
    private final Camera camera;
    private final ILoader loader;
    private static long startTime;
    private GameState state;
    private String pauseText = "Pause";
    private Color backgroudPauseColor = new Color(0, 0, 0, 150);

    private boolean debugMode = false;

    /**
     * constructor for this class.
     */
    public Game() {
        this.state = GameState.LOADING;

        new Window(WINDOW_WIDTH, WINDOW_HEIGHT, "Geo Survival xxx", this);

        handler = new Handler();
        loader = new Loader(handler);
        textureRender = new TextureRender(handler);

        loader.loadAll(); // loads Player, textures, weapons, level
        this.addKeyListener(new KeyInput(this, handler));

        camera = loader.loadCamera(); // loads camera

        // start(); // starts threads //TODO: comment once changed in Main.java
    }

    /**
     * returns game's Handler.
     * 
     * @return game's Handler
     */
    public Handler returnHandler() {
        return handler;
    }

    /**
     * Starts threads.
     */
    public synchronized void start() {
        this.state = GameState.RUNNING;
        this.isRunning = true;
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
        // int frames = 0;

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / NANO_PER_TICK;
            lastTime = now;
            while (delta > 1) {
                this.tick();
                this.render();
                delta--;
                // frames++;
            }

            if (System.currentTimeMillis() - timer > SECOND_IN_MILLI) {
                timer += SECOND_IN_MILLI;
                // frames = 0;
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

        g2d.translate(-camera.getX(), -camera.getY());

        if (debugMode) {
            textureRender.showDebug(g);
        }
        textureRender.renderView(g);

        if( state == GameState.PAUSE) {

            /* Background obscured */
            g.setColor(backgroudPauseColor);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

            g.setColor(Color.WHITE);

            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(pauseText);
            int textHeight = fm.getHeight();

            int x_pause = (WINDOW_WIDTH - textWidth) / 2;
            int y_pause = (WINDOW_HEIGHT - textHeight) / 2;

            g.drawString(pauseText, x_pause, y_pause);


        
        
        }

        ////////////////////////////////////// above here we draw to the game

        g.dispose();
        bs.show();
    }

    /**
     * pauses/resumes the game.
     */
    public void pause() {
        if (this.state == GameState.RUNNING) {
            this.state = GameState.PAUSE;
        } else if (this.state == GameState.PAUSE) {
            this.state = GameState.RUNNING;
        }
    }

    /**
     * switches on/off the debug mode.
     */
    public void switchDebug() {
        this.debugMode = !this.debugMode;
    }

    /**
     * checks if the game is in debug mode.
     * 
     * @return boolean true if game is in debug mode, false otherwise
     */
    public boolean isDebugMode() {
        return debugMode;
    }
}
