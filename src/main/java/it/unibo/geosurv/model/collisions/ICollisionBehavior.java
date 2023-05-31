package it.unibo.geosurv.model.collisions;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;

/**
 * Interface for behaviors in case of collision
 */
public interface ICollisionBehavior {
    // TODO: STRATEGY PATTERN
    
    /**
     * behavior in case of collision.
     */
    void collide(final GameObject ths, final Handler handler);
}
