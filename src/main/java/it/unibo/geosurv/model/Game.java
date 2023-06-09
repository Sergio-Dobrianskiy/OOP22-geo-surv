package it.unibo.geosurv.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import it.unibo.geosurv.control.GameState;
import it.unibo.geosurv.control.KeyInput;
import it.unibo.geosurv.control.ITickingObject;
import it.unibo.geosurv.model.loader.ILoader;
import it.unibo.geosurv.model.loader.Loader;
import it.unibo.geosurv.model.player.Player;
import it.unibo.geosurv.view.graphics.Camera;
import it.unibo.geosurv.view.graphics.TextureRender;
import it.unibo.geosurv.view.graphics.Window;

/**
 * Represents the main Game class.
 */
public class Game extends Canvas implements Runnable, ITickingObject {

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

    private boolean isRunning;
    private Thread thread;
    private final Handler handler;
    private final TextureRender textureRender;
    private final Camera camera;
    private GameState state;
    private boolean debugMode;
    /* Variables used for pause */
    private final String pauseText = "Pause";
    private final Font pauseFont = new Font("Arial", Font.BOLD, 150);
    private final Color backgroundPauseColor = new Color(0, 0, 0, 150);
    /* Variables used for gameOver */
    private final String gameOverText = "Game Over";
    private final Font gameOverFont = new Font("Arial", Font.BOLD, 100);
    private final Color gameOverColor = Color.RED;
    private final Color backgroundGameOverColor = new Color(0, 0, 0, 250);

    private Player player;

    /**
     * constructor for this class.
     */
    public Game() {
        final ILoader loader;
        this.state = GameState.LOADING;

        new Window(WINDOW_WIDTH, WINDOW_HEIGHT, "Geo-Survivors", this);

        this.handler = new Handler();
        loader = new Loader(this.handler);
        this.textureRender = new TextureRender(this.handler);

        loader.loadAll(); // loads Player, textures, weapons, level
        this.addKeyListener(new KeyInput(this, this.handler));

        camera = loader.loadCamera(); // loads camera

        // start(); // starts threads
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

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
        // int frames = 0;

        while (isRunning) {
            final long now = System.nanoTime();
            delta += (now - lastTime) / NANO_PER_TICK;
            lastTime = now;
            // System.out.println("***" + delta);
            while (delta > 1) {
                this.tick();
                this.render();
                delta -= 1;
                // System.out.println("->" + delta);

                // frames++;
            }

            if (System.currentTimeMillis() - timer > SECOND_IN_MILLI) {
                timer += SECOND_IN_MILLI;
                // frames = 0;
            }
        }
        stop();
    }

    @Override
    public void tick() {
        if (state == GameState.RUNNING) {
            handler.tick();
            checkPlayerLife();
        }
        camera.tick();
    }

    public void render() {
        final BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(FRAMES_IN_BUFFER);
            return;
        }
        final Graphics g = bs.getDrawGraphics();
        final Graphics2D g2d = (Graphics2D) g;

        ///////////////////////////////////// below here we draw to the game
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        g2d.translate(-camera.getX(), -camera.getY());

        if (debugMode) {
            textureRender.showDebug(g);
        }
        textureRender.renderView(g);

        /* Game management paused */
        if (state == GameState.PAUSE) {

            /* Background obscured */
            g.setColor(backgroundPauseColor);
            g.fillRect((int) camera.getX(), (int) camera.getY(), WINDOW_WIDTH, WINDOW_HEIGHT);

            g.setColor(Color.WHITE);

            FontMetrics fm = g.getFontMetrics();
            final int textWidth = fm.stringWidth(pauseText);
            final int textHeight = fm.getHeight();
            final int xPause = (WINDOW_WIDTH - textWidth) / 2 + (int) camera.getX();
            final int yPause = (WINDOW_HEIGHT - textHeight) / 2 + (int) camera.getY();

            g.drawString(pauseText, xPause, yPause);

        }
        
        /* Game management lost */
        if (state == GameState.LOST) {
            g.setColor(backgroundGameOverColor);
            g.fillRect((int) camera.getX(), (int) camera.getY(), WINDOW_WIDTH, WINDOW_HEIGHT);
        
            g.setFont(gameOverFont);
            g.setColor(gameOverColor);
        
            FontMetrics fm = g.getFontMetrics();
            final int textWidth = fm.stringWidth(gameOverText);
            final int textHeight = fm.getHeight();
            final int xGameOver = (WINDOW_WIDTH - textWidth) / 2 + (int) camera.getX();
            final int yGameOver = (WINDOW_HEIGHT - textHeight) / 2 + (int) camera.getY();
        
            g.drawString(gameOverText, xGameOver, yGameOver);
        }

        ////////////////////////////////////// above here we draw to the game

        g.dispose();
        bs.show();
    }

    /**
     * pauses/resumes the game.
     */
    public void statePause() {
        if (this.state == GameState.RUNNING) {
            this.state = GameState.PAUSE;
        } else if (this.state == GameState.PAUSE) {
            this.state = GameState.RUNNING;
        }
        repaint();
    }

    /**
     * wins the game.
     */
    public void stateWon() {
        if (this.state == GameState.RUNNING) {
            this.state = GameState.WON;
        }
    }

    /**
     * loses the game.
     */
    public void stateLost() {
        if (this.state == GameState.RUNNING) {
            this.state = GameState.LOST;
        }
    }

    /**
     * enters the menu.
     */
    public void stateMenu() {
        if (this.state == GameState.WON || this.state == GameState.LOST) {
            this.state = GameState.MENU;
        }
    }

    /**
     * enters the running state.
     */
    public void stateRunning() {
        if (this.state == GameState.MENU || this.state == GameState.PAUSE) {
            this.state = GameState.RUNNING;
        }
    }
    
    /**
     * enters the loading state.
     */
    public void stateLoading() {
        if (this.state == GameState.MENU) {
            this.state = GameState.LOADING;
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

    /**
     * method for clear Handler
     */
    public void clearHandler() {
        handler.clearHandler();
    }
    /**
     * method for check the life of the Player and if he still alive 
     */

    private void checkPlayerLife() {
        player = handler.getPlayer(); // Aggiornamento della variabile player
        if (player != null && !player.isAlive()) {
            stateLost();
        }
    }
}
