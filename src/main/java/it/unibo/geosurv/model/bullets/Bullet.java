package it.unibo.geosurv.model.bullets;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.collisions.Collisions;
import it.unibo.geosurv.model.collisions.ICollisionBehavior;
import it.unibo.geosurv.model.collisions.NoCollisionBehavior;

/**
 * Represents the abstract class for all Bullets.
 */
public abstract class Bullet extends GameObject {
    /**
     * Bullet's default life span.
     */
    protected long lifeSpan = 5000L;     // max milliseconds of life
    protected ICollisionBehavior collisionBehavior;
    private int damage;
    private long creationTime;
    protected final Handler handler;
    private Collisions collisions;

    /**
     * Constructor for this class.
     *
     * @param x       bullet coordinate
     * @param y       bullet coordinate
     * @param handler game's Handler
     * @param damage  bullet damage
     */
    public Bullet(final float x, final float y, final Handler handler, final int damage) {
        super(x, y, ID.Bullet);
        this.handler = handler;
        this.creationTime = System.currentTimeMillis();
        this.collisions = new Collisions(handler);
        this.damage = damage;
        this.collisionBehavior = new NoCollisionBehavior();
    }

    /**
     * checks if the bullet is still alive, otherwise it removes it from the game.
     */
    @Override
    public void tick() {
        if (this.stillAlive()) {
            updatePosition(this.velX, this.velY);
            this.collisions.checkBulletCollisionss(this);
        } else {
            handler.removeObject(this);
        }
    }

    /**
     * updates bullet position on the field.
     * 
     * @param velX x component of velocity vector
     * @param velY y component of velocity vector
     */
    private void updatePosition(final float velX, final float velY) {
        this.setX(this.getX() + this.velX);
        this.setY(this.getY() + this.velY);
    }

    /**
     * checks if the bullet has expired it's life span.
     * 
     * @return boolean true if bullet is still present in game
     */
    protected boolean stillAlive() {
        final long currentTime = System.currentTimeMillis();
        if ((currentTime - this.creationTime) > lifeSpan) {
            handler.removeObject(this);
            return false;
        }
        return true;
    }

    /**
     * returns bullet damage.
     * 
     * @return int damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * set bullet damage.
     * 
     * @param damage bullet damage
     */
    public void setDamage(final int damage) {
        this.damage = damage;
    }

    /**
     * starts collision behavior, no behavior by default.
     */
    public void collide() {
        this.collisionBehavior.collide(this, this.handler);
    }
}
