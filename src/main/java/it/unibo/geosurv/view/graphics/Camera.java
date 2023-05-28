package it.unibo.geosurv.view.graphics;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.MainPlayer;

/**
 * represents game's camera.
 */
public class Camera {

    private float x;
    private float y;
    private final MainPlayer tempPlayer;

    /**
     * constructor for this class.
     * 
     * @param x camera coordinate
     * @param y camera coordinate
     * @param handler
     */
    public Camera(final float x, final float y, final Handler handler) {
        this.x = x;
        this.y = y;
        this.tempPlayer = handler.getPlayer();
    }

    /**
     * Tick of camera.
     */
    public void tick() {
        x += ((this.tempPlayer.getX() - x) - Game.WINDOW_WIDTH / 2) * 0.05f;
        y += ((this.tempPlayer.getY() - y) - Game.WINDOW_HEIGHT / 2) * 0.05f;

        if (x <= 0) {
            x = 0;
        }

        if (x >= 1045) {
            x = 1045;
        }

        if (y <= 0) {
            y = 0;
        }

        if (y >= 1500) {
            y = 1500;
        }
    }

    /**
     * @return camera x coordinate.
     */
    public float getX() {
        return x;
    }

    /**
     * Set camera y coordinate.
     * 
     * @param xPos
     */
    public void setX(final float xPos) {
        this.x = xPos;
    }

    /**
     * @return camera y coordinate.
     */
    public float getY() {
        return y;
    }

    /**
     * Set camera y coordinate.
     * 
     * @param yPos
     */
    public void setY(final float yPos) {
        this.y = yPos;
    }

}
