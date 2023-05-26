package it.unibo.geosurv.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.Handler;

public class KeyInput extends KeyAdapter {

    private final Game game;
    private final Handler handler;
    // private final TextureRender view;

    public KeyInput(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
        // this.view = new TextureRender(handler);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
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
                game.pause();
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

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
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
