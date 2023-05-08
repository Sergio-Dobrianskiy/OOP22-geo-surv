package it.unibo.geosurv.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.Handler;

public class KeyInput extends KeyAdapter {

	private final Game game;
	private final Handler handler;

    public KeyInput(Game game, Handler handler) {
    		this.game = game;
        this.handler = handler;
    }

    
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) handler.setUp(true);					// UP
        if (key == KeyEvent.VK_S) handler.setDown(true);					// DOWN
        if (key == KeyEvent.VK_A) handler.setLeft(true);					// LEFT
        if (key == KeyEvent.VK_D) handler.setRight(true);					// RIGHT
        if (key == KeyEvent.VK_P) game.setPause(!game.getPause());		// PAUSE-UNPAUSE
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
	    if (key == KeyEvent.VK_W) handler.setUp(false);
	    if (key == KeyEvent.VK_S) handler.setDown(false);
	    if (key == KeyEvent.VK_A) handler.setLeft(false);
	    if (key == KeyEvent.VK_D) handler.setRight(false);
    }
}