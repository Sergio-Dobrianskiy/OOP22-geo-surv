package it.unibo.geosurv.model.collisions;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;

/**
 * Behavior that doesn't react on collision.
 */
public class NoCollisionBehavior implements ICollisionBehavior {

    /**
     * Object doesn't react to collisions.
     */
    @Override
    public void collide(final GameObject ths, final Handler handler) {
        // do nothing by default;
    }
}
