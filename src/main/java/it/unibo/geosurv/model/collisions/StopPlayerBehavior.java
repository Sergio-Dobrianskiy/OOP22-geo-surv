package it.unibo.geosurv.model.collisions;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.Player;

/**
 * behavior that stops player movements.
 *
 */
public class StopPlayerBehavior implements ICollisionBehavior {

    /**
     * Constructor for this class.
     */
    public StopPlayerBehavior() {
    }

    /**
     * Behavior that removes object in case of collision.
     */
    @Override
    public void collide(final GameObject ths, final Handler handler) {
        final Player player = handler.getPlayer();
        player.setX(player.getX() + player.getVelX() * -1);
        player.setY(player.getY() + player.getVelY() * -1);
    }
}
