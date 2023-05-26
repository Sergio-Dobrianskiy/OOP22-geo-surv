package it.unibo.geosurv.view.graphics;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.MainPlayer;

public class Camera {

    private float x;
    private float y;
    private final MainPlayer tempPlayer;
    // private Handler handler;

    public Camera(final float x, final float y, final Handler handler) {
        this.x = x;
        this.y = y;
        this.tempPlayer = handler.getPlayer();
    }

    /**
     * Tick of camera
     */
    public void tick() {
        x += ((this.tempPlayer.getX() - x) - 1000 / 2) * 0.05f;
        y += ((this.tempPlayer.getY() - y) - 563 / 2) * 0.05f;

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
     * @return camera x coord.
     */
    public float getX() {
        return x;
    }

    /**
     * Set camera y coord.
     * 
     * @param xPos
     */
    public void setX(final float xPos) {
        this.x = xPos;
    }

    /**
     * @return camera y coord.
     */
    public float getY() {
        return y;
    }

    /**
     * Set camera y coord.
     * 
     * @param yPos
     */
    public void setY(final float yPos) {
        this.y = yPos;
    }

}
