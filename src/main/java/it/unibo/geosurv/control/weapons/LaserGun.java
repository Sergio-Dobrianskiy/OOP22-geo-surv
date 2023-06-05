package it.unibo.geosurv.control.weapons;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.bullets.Laser;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents an LaserGun Weapon.
 */
public class LaserGun extends Weapon {

    /**
     * laser short side length.
     */
    private final int shortSide = 32;
    /**
     * laser long side length.
     */
    private final int longSide = 286;
    /**
     * laser cycle in ms.
     */
    private final long laserCycle = 3000L;
    /**
     * laser damage at level 1.
     */
    private final int damageLevel1 = 2;
    /**
     * laser damage at level 2.
     */
    private final int damageLevel2 = 4;
    /**
     * laser damage at level 3.
     */
    private final int damageLevel3 = 6;

    private Handler handler;
    private GameObject player;

    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public LaserGun(final Handler handler) {
        super();
        this.handler = handler;
        this.cycle = laserCycle;
        this.player = handler.getPlayer();
        this.damageLvl1 = damageLevel1;
        this.damageLvl1 = damageLevel2;
        this.damageLvl1 = damageLevel3;
    }

    /**
     * creates 1/2/4 lasers the player.
     */
    protected void shoot() {
        final float px = this.player.getX();
        final float py = this.player.getY();
        final float xCorrection = (longSide + MainPlayer.PLAYER_WIDTH) / 2;
        final float yCorrection = (longSide + MainPlayer.PLAYER_HEIGHT) / 2;

        if (this.currentLevel >= 1) {
            this.handler.addObject(new Laser(px + xCorrection, py, this.handler, getDamage(), longSide, shortSide, Texture.LASER_H)); // right
        }
        if (this.currentLevel >= 2) {
            this.handler.addObject(new Laser(px - xCorrection, py, this.handler, getDamage(), longSide, shortSide, Texture.LASER_H)); // left
        }
        if (this.currentLevel >= 3) {
            this.handler.addObject(new Laser(px, py - yCorrection, this.handler, getDamage(), shortSide, longSide, Texture.LASER_V)); // up
            this.handler.addObject(new Laser(px, py + yCorrection, this.handler, getDamage(), shortSide, longSide, Texture.LASER_V)); // down
        }
    }
}
