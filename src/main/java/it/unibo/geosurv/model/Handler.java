package it.unibo.geosurv.model;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.model.player.MainPlayer;

/**
 * Represents the Handler.
 */
public class Handler implements TickingObject {

    private CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<TickingObject> tickingObjects = new CopyOnWriteArrayList<>();
    private MainPlayer player;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;


    /**
     * calls tick() for every gameObject and tickingObject.
     */
    public void tick() {
        Iterator<GameObject> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            GameObject tempObject = iterator.next();
            tempObject.tick();
        }

        Iterator<TickingObject> it = tickingObjects.iterator();
        while (it.hasNext()) {
            TickingObject tempObject = it.next();
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

    public MainPlayer addPlayer(MainPlayer player) {
        this.player = player;
        this.gameObjects.add(player);
        return player;
    }

    public MainPlayer getPlayer() {
        return this.player;
    }

    public int getObjectsSize() {
        return this.gameObjects.size();
    }

    public CopyOnWriteArrayList<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public CopyOnWriteArrayList<TickingObject> getTickingbjects() {
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
