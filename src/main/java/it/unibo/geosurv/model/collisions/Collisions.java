package it.unibo.geosurv.model.collisions;

import java.util.ArrayList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.bullets.Bullet;
import it.unibo.geosurv.model.bullets.BulletImpl;
import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.drops.Life;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.player.MainPlayer;

/**
 * Manages game collisions.
 */
public class Collisions {

    private Handler handler;

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
        ArrayList<GameObject> tmpObjects = handler.getGameObjects();
        MainPlayer player = handler.getPlayer();
        for (int i = 0; i < tmpObjects.size(); i++) {
            GameObject tempObject = tmpObjects.get(i);
            if (Collisions.isColliding(player, tempObject, ID.Block)) { // if player touches wall => stop
                this.stopMovements();
            }
            if (Collisions.isColliding(player, tempObject, ID.Monster)) { // if player touches Monsters
                player.hit(((Monster) tempObject).getPower()); // TODO: verify the cast => maybe if we call a
                // function here that works at Monster we do not
                // need the cast
                // (Monster.over(handler.player)->decrease life)
            }
            if (Collisions.isColliding(player, tempObject, ID.Experience)) {
                handler.getPlayer().setExperience(((Experience) tempObject).getExperience());
                handler.removeObject(tempObject);
            }
            if (Collisions.isColliding(player, tempObject, ID.Life)) {
                handler.getPlayer().setLife(((Life) tempObject).getLife());
                handler.removeObject(tempObject);
            }
        }
    }

    /**
     * check if a bullet is colliding.
     * 
     * @param bullet to check
     */
    public void checkBulletCollisionss(final Bullet bullet) {
        ArrayList<GameObject> tmpObjects = handler.getGameObjects();
        for (int i = 0; i < tmpObjects.size(); i++) {
            GameObject tempObject = tmpObjects.get(i);

            if (Collisions.isColliding(bullet, tempObject, ID.Block)) {
                bullet.collide();

            } else if (Collisions.isColliding(bullet, tempObject, ID.Monster)) {
                ((Monster) tempObject).hit(bullet.getDamage());
                bullet.collide();
            }
        }
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
        MainPlayer player = handler.getPlayer();
        player.setX(player.getX() + player.getVelX() * -1);
        player.setY(player.getY() + player.getVelY() * -1);
    }
}
