package it.unibo.geosurv.model.bullets;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents a Satellite Bullet.
 */
public class Satellite extends Bullet {
    /**
     * satellite height.
     */
    private static final int SATELLITE_HEIGHT = 15;
    /**
     * satellite width.
     */
    private static final int SATELLITE_WIDTH = 15;

    /**
     * Constructor for this class.
     *
     * @param x coordinate
     * @param y coordinate, 
     * @param handler game's Handler
     * @param damage satellites damage
     */
    public Satellite(final float x, final float y, final Handler handler, final int damage) {
        super(x, y, handler, damage);
        this.height = SATELLITE_HEIGHT;
        this.width = SATELLITE_WIDTH;
        this.texture = Texture.SATELLITE;
    }

    /**
     * Satellite has infinite life span.
     */
    @Override
    protected boolean stillAlive() {
        return true;
    }
}
