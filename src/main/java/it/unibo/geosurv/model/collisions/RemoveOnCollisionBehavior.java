package it.unibo.geosurv.model.collisions;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;

/**
 * Behavior that removes object in case of collision.
 */
public class RemoveOnCollisionBehavior implements ICollisionBehavior {

    /**
     * Constructor for this class.
     */
    public RemoveOnCollisionBehavior() {
    }

    /**
     * Behavior that removes object in case of collision.
     */
    @Override
    public void collide(final GameObject ths, final Handler handler) {
        handler.removeObject(ths);
    }
}
