package it.unibo.geosurv.control.weapons;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.bullets.Explosion;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

/**
 * Represents an ExplosionGun Weapon.
 */
public class ExplosionGun extends Weapon {
    /**
     * minimum range at witch explosion can appear.
     */
    private final float minRange = 10f;
    /**
     * maximum range at witch explosion can appear.
     */
    private final float maxRange = 200f;
    /**
     * explosion cycle in ms.
     */
    private final long explosionCycle = 4000L;
    /**
     * explosion damage at level 1.
     */
    private final int damageLevel1 = 3;
    /**
     * explosion damage at level 2.
     */
    private final int damageLevel2 = 6;
    /**
     * explosion damage at level 3.
     */
    private final int damageLevel3 = 9;
    /**
     * explosion damage at level 3.
     */
    private final int explosionsPerLevel = 2;

    private Handler handler;
    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public ExplosionGun(final Handler handler) {
        super();
        this.handler = handler; 
        this.cycle = explosionCycle;
        this.damageLvl1 = damageLevel1;
        this.damageLvl1 = damageLevel2;
        this.damageLvl1 = damageLevel3;
    }

    /**
     * creates explosions around the player.
     */
    @Override
    protected void shoot() {
        GameObject player = handler.getPlayer();
        float x = player.getX();
        float y = player.getY();
        Pair<Float, Float> pair;

        for (int i = 0; i <= currentLevel * explosionsPerLevel; i++) {
            pair = Func.randomPoint(minRange, maxRange);
            handler.addObject(new Explosion(pair.getX() + x, pair.getY() + y, handler, getDamage()));
        }
    }
}
