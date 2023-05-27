package it.unibo.geosurv.control.weapons;

import java.awt.geom.Point2D;
import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.bullets.BulletImpl;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

/**
 * Represents an AutoGun Weapon.
 */
public class AutoGun extends Weapon {
    /**
     * default bullet speed.
     */
    private final int bulletSpeed = 10;
    /**
     * default AutoGun maximum range.
     */
    private final int maxRange = 400;
    /**
     * default Weapon damage at level 1.
     */
    private final int damageLevel1 = 3;
    /**
     * default Weapon damage at level 2.
     */
    private final int damageLevel2 = 4;
    /**
     * default Weapon damage at level 3.
     */
    private final int damageLevel3 = 5;
    /**
     * default delta from 2nd shot.
     */
    private final float level2delta = 0.1f;
    /**
     * default delta from 3rd shot.
     */
    private final float level3delta = 0.2f;

    private Handler handler;
    private GameObject player;
    private GameObject closestEnemy;
    private long lastTime;
    private float closestEnemyDistance;
    private double delta = 0;
    private boolean secondaryShooting = false;
    private boolean tertiaryShooting = false;

    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public AutoGun(final Handler handler) {
        super();
        this.handler = handler;
        this.lastTime = System.nanoTime();
        this.player = handler.getPlayer();
        this.damageLvl1 = damageLevel1;
        this.damageLvl2 = damageLevel2;
        this.damageLvl3 = damageLevel3;
    }

    /**
     * shoots 1/2/3 bullets at the nearest enemy.
     */
    @Override
    public void tick() {
        double second = Game.SECOND_IN_NANO;

        if (this.currentLevel > 0) { // TODO: sistemare
            long now = System.nanoTime();
            this.delta = (now - this.lastTime) / second;

            if (this.secondaryShooting && this.delta > level2delta) {
                this.shoot();
                this.secondaryShooting = false;
            }

            if (this.tertiaryShooting && this.delta > level3delta) {
                this.shoot();
                this.tertiaryShooting = false;
            }

            if (this.delta >= 1) {
                this.lastTime = now;
                this.closestEnemy = Func.findClosestEnemy(handler);
                if (this.closestEnemy == null) {
                    return;
                }

                this.closestEnemyDistance = (float) Point2D.distance(player.getX(), player.getY(), closestEnemy.getX(),
                        closestEnemy.getY());
                if (this.closestEnemyDistance <= maxRange) {
                    this.shoot();

                    if (this.currentLevel >= 2) { // TODO: sistemare
                        this.secondaryShooting = true;
                    }

                    if (this.currentLevel >= 3) { // TODO: sistemare
                        this.tertiaryShooting = true;
                    }
                }
            }
        }
    }

    /**
     * shoots a bullet at the nearest enemy.
     */
    protected void shoot() {
        Pair<Float, Float> angle = Func.findAngle2(this.player, this.closestEnemy);
        GameObject tempBullet = handler
                .addObject(new BulletImpl(player.getX(), player.getY(), handler, this.getDamage()));
        tempBullet.setVelX((float) ((bulletSpeed) * angle.getX()));
        tempBullet.setVelY((float) ((bulletSpeed) * angle.getY()));
    }
}
