package it.unibo.geosurv.model.collisions;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;

/**
 * Interface for behaviors in case of collision.
 */
public interface ICollisionBehavior {
    /**
     * behavior in case of collision.
     * @param ths "this" class
     * @param handler handler
     */
    void collide(GameObject ths, Handler handler);
}
