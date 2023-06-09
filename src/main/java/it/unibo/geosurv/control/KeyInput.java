package it.unibo.geosurv.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.Handler;

/**
 * Manages player's input.
 */
public class KeyInput extends KeyAdapter {

    private final Game game;
    private final Handler handler;

    /**
     * Constructor for this class.
     *
     * @param game main game class
     * @param handler games Handler
     */
    public KeyInput(final Game game, final Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    /**
     * Manages key presses.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                handler.setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                handler.setRight(true);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                handler.setUp(true);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                handler.setDown(true);
                break;
            case KeyEvent.VK_P:
                game.statePause();
                break;
            case KeyEvent.VK_G:
                game.switchDebug();
                // if (game.isDebugMode()) {
                // view.showDebug(game.getGraphics());
                // }
                break;
            default:
                break;
        }

    }

    /**
     * Manages key releases.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        final int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                handler.setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                handler.setRight(false);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                handler.setUp(false);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                handler.setDown(false);
                break;
            default:
                break;
        }
    }
}
