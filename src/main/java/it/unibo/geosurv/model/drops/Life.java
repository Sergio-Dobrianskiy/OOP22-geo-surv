package it.unibo.geosurv.model.drops;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.collisions.ICollisionBehavior;
import it.unibo.geosurv.model.collisions.RemoveOnCollisionBehavior;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Life pills are created randomly (more or less 1/50 a time) when a monster
 * dies. No experience is created if life pill is created.
 */
public class Life extends GameObject {

    private static final int LIFE = 10;
    private static final int LIFE_HEIGHT = 25;
    private static final int LIFE_WIDTH = 20;
    private final Handler handler;
    private ICollisionBehavior collisionBehavior;

    /**
     * Life pill constructor.
     * 
     * @param x position
     * @param y position
     * @param h handler for collision
     * 
     */

    public Life(final float x, final float y, final Handler h) {
        super(x, y, ID.Life);
        this.height = LIFE_HEIGHT;
        this.width = LIFE_WIDTH;
        this.texture = Texture.LIFE;
        this.handler = h;
        this.collisionBehavior = new RemoveOnCollisionBehavior();
    }

    @Override
    public void tick() {
        /**
         * Life pills do not tick()
         */
    }

    /**
     * @return int amount of experience in the pill
     */
    public int getLife() {
        return LIFE;
    }

    /**
     * starts collision behavior, no behavior by default.
     */
    public void collide() {
        this.collisionBehavior.collide(this, this.handler);
    }

}
