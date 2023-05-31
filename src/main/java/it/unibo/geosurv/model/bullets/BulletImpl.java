package it.unibo.geosurv.model.bullets;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.collisions.RemoveOnCollisionBehavior;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents a BulletImpl bullet.
 */
public class BulletImpl extends Bullet {

    /**
     * bulletImpl's height.
     */
    private final int bulletImpHeight = 15;
    /**
     * bulletImpl's width.
     */
    private final int bulletImpWidth = 15;

    /**
     * Constructor for this class.
     *
     * @param x bulletImpl coordinate
     * @param y bulletImpl coordinate
     * @param handler game's Handler
     * @param damage bulletImpl damage
     */
    public BulletImpl(final float x, final float y, final Handler handler, final int damage) {
        super(x, y, handler, damage);
        this.height = bulletImpHeight;
        this.width = bulletImpWidth;
        this.texture = Texture.BULLET;
        this.collisionBehavior = new RemoveOnCollisionBehavior();
    }
    
    /**
     * starts collision behavior, no behavior by default.
     * 
     * @param this the bullet iself
     * @param null 
     * @param handler the handler
     */
    @Override
    public void collide() {
        this.collisionBehavior.collide(this, this.handler);
    }
}
