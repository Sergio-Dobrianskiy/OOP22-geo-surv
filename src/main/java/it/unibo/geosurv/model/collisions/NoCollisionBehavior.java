package it.unibo.geosurv.model.collisions;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.bullets.Bullet;

/**
 * Behavior that removes object in case of collision
 */
public class NoCollisionBehavior implements ICollisionBehavior {
    
    public NoCollisionBehavior() {
    }

    /**
     * Object doesn't react to collisions
     */
    @Override
    public void collide(final GameObject ths, final Handler handler) {
    }
}
