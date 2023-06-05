package it.unibo.geosurv.model;

import java.util.ArrayList;
import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.model.player.Player;

/**
 * Represents the Handler.
 */
public class Handler implements TickingObject {

    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private ArrayList<TickingObject> tickingObjects = new ArrayList<>();
    private Player player;
    private boolean up = false, down = false, left = false, right = false;


    /**
     * calls tick() for every gameObject and tickingObject.
     */
    public void tick() {
        for (int i = 0; i < gameObjects.size(); i++) {
            final GameObject tempObject = gameObjects.get(i);
            tempObject.tick();
        }
        for (int i = 0; i < tickingObjects.size(); i++) {
            final TickingObject tempObject = tickingObjects.get(i);
            tempObject.tick();
        }
    }


    public GameObject addObject(final GameObject tempObject) {
        gameObjects.add(tempObject);
        return tempObject;
    }

    public void removeObject(final GameObject tempObject) {
        gameObjects.remove(tempObject);
    }

    public TickingObject addTickingObject(final TickingObject tempObject) {
        tickingObjects.add(tempObject);
        return tempObject;
    }

    public void removeTickingObject(final TickingObject tempObject) {
        tickingObjects.remove(tempObject);
    }

    public Player addPlayer(Player player) {
        this.player = player;
        this.gameObjects.add(player);
        return player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getObjectsSize() {
        return this.gameObjects.size();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public ArrayList<TickingObject> getTickingbjects() {
        return this.tickingObjects;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void clearHandler() {
        gameObjects.clear();
        tickingObjects.clear();
    }
}
