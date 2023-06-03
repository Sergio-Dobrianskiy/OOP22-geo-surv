package it.unibo.geosurv.model.collisions;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.bullets.Bullet;

/**
 * Behavior that doesn't react on collision.
 */
public class NoCollisionBehavior implements ICollisionBehavior {

    /**
     * Constructor for this class.
     */
    public NoCollisionBehavior() {
    }

    /**
     * Object doesn't react to collisions.
     */
    @Override
    public void collide(final GameObject ths, final Handler handler) {
    }
}
