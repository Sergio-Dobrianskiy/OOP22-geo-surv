package it.unibo.geosurv.model;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import it.unibo.geosurv.control.ITickingObject;
import it.unibo.geosurv.model.player.Player;

/**
 * Represents the Handler.
 */
public class Handler implements ITickingObject {

    private final CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<ITickingObject> tickingObjects = new CopyOnWriteArrayList<>();
    private Player player;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    /**
     * calls tick() for every gameObject and tickingObject.
     */
    @Override
    public void tick() {
        final Iterator<GameObject> goIterator = gameObjects.iterator();
        final Iterator<ITickingObject> toIterator = tickingObjects.iterator();
        while (goIterator.hasNext()) {
            goIterator.next().tick();
        }

        while (toIterator.hasNext()) {
            toIterator.next().tick();
        }
    }

    /**
     * Add tempObject to gameObjects.
     * 
     * @param tempObject
     */
    public void addObject(final GameObject tempObject) {
        gameObjects.add(tempObject);
    }

    /**
     * Remove tempObject from gameObjects.
     * 
     * @param tempObject
     */
    public void removeObject(final GameObject tempObject) {
        gameObjects.remove(tempObject);
    }

    /**
     * Add tickingObject.
     * 
     * @param tempObject
     */
    public void addTickingObject(final ITickingObject tempObject) {
        tickingObjects.add(tempObject);
    }

    /**
     * Remove tickingObject.
     * 
     * @param tempObject
     */
    public void removeTickingObject(final ITickingObject tempObject) {
        tickingObjects.remove(tempObject);
    }

    /**
     * Add player to gameObjects.
     * 
     * @param player
     */
    public void addPlayer(final Player player) {
        this.player = player;
        this.gameObjects.add(player);
    }

    /**
     * @return player.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return gameObjects size.
     */
    public int getObjectsSize() {
        return this.gameObjects.size();
    }

    /**
     * @return gameObjects.
     */
    public CopyOnWriteArrayList<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    /**
     * @return tickingObjects.
     */
    public CopyOnWriteArrayList<ITickingObject> getTickingbjects() {
        return this.tickingObjects;
    }

    /**
     * @return if isUp
     */
    public boolean isUp() {
        return up;
    }

    /**
     * @param up
     */
    public void setUp(final boolean up) {
        this.up = up;
    }

    /**
     * @return if isDown.
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @param down
     */
    public void setDown(final boolean down) {
        this.down = down;
    }

    /**
     * @return if isLeft
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @param left
     */
    public void setLeft(final boolean left) {
        this.left = left;
    }

    /**
     * @return if isRight
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @param right
     */
    public void setRight(final boolean right) {
        this.right = right;
    }

    /**
     * Clear handler.
     */
    public void clearHandler() {
        gameObjects.clear();
        tickingObjects.clear();
    }
}
