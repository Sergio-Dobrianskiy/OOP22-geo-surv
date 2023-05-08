package it.unibo.geosurv.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

public class KeyInput extends KeyAdapter {

    Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        LinkedList<GameObject> tmpObjects = handler.getObjects();

        for (int i = 0; i < tmpObjects.size(); i++) {
            GameObject tempObject = tmpObjects.get(i);
            // TODO: si può usare un'unica volta Game.returnHandler().getPlayer();
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W)
                    handler.setUp(true);
                if (key == KeyEvent.VK_S)
                    handler.setDown(true);
                if (key == KeyEvent.VK_A)
                    handler.setLeft(true);
                if (key == KeyEvent.VK_D)
                    handler.setRight(true);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        LinkedList<GameObject> tmpObjects = handler.getObjects();

        for (int i = 0; i < tmpObjects.size(); i++) {
            GameObject tempObject = tmpObjects.get(i);
            // TODO: si può usare un'unica volta Game.returnHandler().getPlayer();
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W)
                    handler.setUp(false);
                if (key == KeyEvent.VK_S)
                    handler.setDown(false);
                if (key == KeyEvent.VK_A)
                    handler.setLeft(false);
                if (key == KeyEvent.VK_D)
                    handler.setRight(false);
            }
        }
    }
}