package it.unibo.geosurv.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

public class KeyInput extends KeyAdapter {

	private final Game game;
	private final Handler handler;

    public KeyInput(Game game, Handler handler) {
    		this.game = game;
        this.handler = handler;
    }

    
    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) handler.setUp(true);
        if(key == KeyEvent.VK_S) handler.setDown(true);
        if(key == KeyEvent.VK_A) handler.setLeft(true);
        if(key == KeyEvent.VK_D) handler.setRight(true);
        if(key == KeyEvent.VK_P) game.pause = !game.pause;;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
	    if(key == KeyEvent.VK_W) handler.setUp(false);
	    if(key == KeyEvent.VK_S) handler.setDown(false);
	    if(key == KeyEvent.VK_A) handler.setLeft(false);
	    if(key == KeyEvent.VK_D) handler.setRight(false);
    }
}