package it.unibo.geosurv.model.collisions;

import java.util.Iterator;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.bullets.Bullet;
import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.drops.Life;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.player.Player;

/**
 * Manages game collisions.
 */
public class Collisions {

    private final Handler handler;

    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public Collisions(final Handler handler) {
        this.handler = handler;
    }

    /**
     * check if the player is colliding.
     */
    public void checkPlayerCollisions() {
        final Iterator<GameObject> goIterator = handler.getGameObjects().iterator();
        final Player player = handler.getPlayer();

        goIterator.forEachRemaining(obj -> {
            if (Collisions.isColliding(player, obj, ID.Block)) {     // if player touches wall => stop
                player.collide();
            }
            if (Collisions.isColliding(player, obj, ID.Monster)) {   // if player touches Monsters
                player.hit(((Monster) obj).getPower());
            }
            if (Collisions.isColliding(player, obj, ID.Experience)) { // if player touches experience pills
                handler.getPlayer().setExperience(((Experience) obj).getExp());
                obj.collide();
            }
            if (Collisions.isColliding(player, obj, ID.Life)) {      // if player touches life pills
                handler.getPlayer().setLife(((Life) obj).getDefaultLife());
                obj.collide();
            }
        });
    }

    /**
     * check if a bullet is colliding.
     * 
     * @param bullet to check
     */
    public void checkBulletCollisions(final Bullet bullet) {
        final Iterator<GameObject> goIterator = handler.getGameObjects().iterator();

        goIterator.forEachRemaining(obj -> {
            if (Collisions.isColliding(bullet, obj, ID.Block)) {            // if bullet collides with a block
                bullet.collide();

            } else if (Collisions.isColliding(bullet, obj, ID.Monster)) {   // if bullet collides with a monster
                ((Monster) obj).hit(bullet.getDamage());
                bullet.collide();
            }
        });
    }

    /**
     * Check if the first GameObject is colliding with another GameObject
     * with a specific ID.
     * 
     * @param obj1 1st GameOnject to check
     * @param obj2 2nd GameOnject to check
     * @param id   that the touched object should have
     * 
     * @return returns true if a collision occurred, false otherwise
     */
    public static boolean isColliding(final GameObject obj1, final GameObject obj2, final ID id) {
        return obj2.getId() == id && obj1.getShape().getBounds2D().intersects(obj2.getShape().getBounds2D());
    }

    /**
     * Check if the first GameObject is colliding with another GameObject
     * with a specific ID.
     * 
     * @param obj1 1st GameOnject to check
     * @param obj2 2nd GameOnject to check
     * 
     * @return returns true if a collision occurred, false otherwise
     */
    public static boolean isColliding(final GameObject obj1, final GameObject obj2) {
        return obj1.getShape().getBounds2D().intersects(obj2.getShape().getBounds2D());
    }

    /**
     * Stops player movements.
     */
    public void stopMovements() {
        final Player player = handler.getPlayer();
        player.setX(player.getX() + player.getVelX() * -1);
        player.setY(player.getY() + player.getVelY() * -1);
    }
}
