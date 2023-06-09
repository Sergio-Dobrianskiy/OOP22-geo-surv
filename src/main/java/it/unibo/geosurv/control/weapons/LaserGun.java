package it.unibo.geosurv.control.weapons;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.bullets.Laser;
import it.unibo.geosurv.model.player.Player;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents an LaserGun Weapon.
 */
public class LaserGun extends Weapon {

    /**
     * laser short side length.
     */
    private static final int SHORT_SIDE = 32;
    /**
     * laser long side length.
     */
    private static final int LONG_SIDE = 286;
    /**
     * laser cycle in ms.
     */
    private static final long LASER_CYCLE = 3000L;
    /**
     * laser damage at level 1.
     */
    private static final int DAMAGE_LEVEL_1 = 3;
    /**
     * laser damage at level 2.
     */
    private static final int DAMAGE_LEVEL_2 = 6;
    /**
     * laser damage at level 3.
     */
    private static final int DAMAGE_LEVEL_3 = 9;

    private final Handler handler;
    private final GameObject player;

    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public LaserGun(final Handler handler) {
        super();
        this.handler = handler;
        this.CYCLE = LASER_CYCLE;
        this.player = handler.getPlayer();
        this.damageLvl1 = DAMAGE_LEVEL_1;
        this.damageLvl2 = DAMAGE_LEVEL_2;
        this.damageLvl3 = DAMAGE_LEVEL_3;
    }

    /**
     * creates 1/2/4 lasers the player.
     */
    @Override
    protected void shoot() {
        final float px = this.player.getX();
        final float py = this.player.getY();
        final float xCorrection = (LONG_SIDE + Player.PLAYER_WIDTH) / 2;
        final float yCorrection = (LONG_SIDE + Player.PLAYER_HEIGHT) / 2;

        if (this.currentLevel >= 1) {
            this.handler.addObject(new Laser(px + xCorrection, py, this.handler, getDamage(), LONG_SIDE, SHORT_SIDE, Texture.LASER_H)); // right
        }
        if (this.currentLevel >= 2) {
            this.handler.addObject(new Laser(px - xCorrection, py, this.handler, getDamage(), LONG_SIDE, SHORT_SIDE, Texture.LASER_H)); // left
        }
        if (this.currentLevel >= 3) {
            this.handler.addObject(new Laser(px, py - yCorrection, this.handler, getDamage(), SHORT_SIDE, LONG_SIDE, Texture.LASER_V)); // up
            this.handler.addObject(new Laser(px, py + yCorrection, this.handler, getDamage(), SHORT_SIDE, LONG_SIDE, Texture.LASER_V)); // down
        }
    }
}
