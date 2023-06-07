package it.unibo.geosurv.control.weapons;

import java.awt.geom.Point2D;
import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.bullets.AutoBullet;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

/**
 * Represents an AutoGun Weapon.
 */
public class AutoGun extends Weapon {
    /**
     * default bullet speed.
     */
    private static final int BULLET_SPEED = 10;
    /**
     * default AutoGun maximum range.
     */
    private static final int MAX_RANGE = 400;
    /**
     * default Weapon damage at level 1.
     */
    private static final int DAMAGE_LEVEL_1 = 3;
    /**
     * default Weapon damage at level 2.
     */
    private static final int DAMAGE_LEVEL_2 = 4;
    /**
     * default Weapon damage at level 3.
     */
    private static final int DAMAGE_LEVEL_3 = 5;
    /**
     * default delta from 2nd shot.
     */
    private static final float LEVEL_2_DELTA = 0.1f;
    /**
     * default delta from 3rd shot.
     */
    private static final float LEVEL_3_DELTA = 0.2f;

    private final Handler handler;
    private final GameObject player;
    private GameObject closestEnemy;
    private long lastTime;
    private float closestEnemyDistance;
    private double delta;
    private boolean secondaryShooting;
    private boolean tertiaryShooting;

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
        this.damageLvl1 = DAMAGE_LEVEL_1;
        this.damageLvl2 = DAMAGE_LEVEL_2;
        this.damageLvl3 = DAMAGE_LEVEL_3;
    }

    /**
     * shoots 1/2/3 bullets at the nearest enemy.
     */
    @Override
    public void tick() {
        final double second = Game.SECOND_IN_NANO;

        if (this.currentLevel > 0) { // TODO: sistemare
            final long now = System.nanoTime();
            this.delta = (now - this.lastTime) / second;

            if (this.secondaryShooting && this.delta > LEVEL_2_DELTA) {
                this.shoot();
                this.secondaryShooting = false;
            }

            if (this.tertiaryShooting && this.delta > LEVEL_3_DELTA) {
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
                if (this.closestEnemyDistance <= MAX_RANGE) {
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
    @Override
    protected void shoot() {
        final Pair<Float, Float> angle = Func.findAngle2(this.player, this.closestEnemy);
        final GameObject tempBullet = new AutoBullet(player.getX(), player.getY(), handler, this.getDamage());
        this.handler.addObject(tempBullet);
        tempBullet.setVelX((float) ((BULLET_SPEED) * angle.getX()));
        tempBullet.setVelY((float) ((BULLET_SPEED) * angle.getY()));
    }
}
