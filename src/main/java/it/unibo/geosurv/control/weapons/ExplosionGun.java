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
    private static final float minRange = 10f;
    /**
     * maximum range at witch explosion can appear.
     */
    private static final float maxRange = 200f;
    /**
     * explosion cycle in ms.
     */
    private static final long explosionCycle = 4000L;
    /**
     * explosion damage at level 1.
     */
    private static final int damageLevel1 = 3;
    /**
     * explosion damage at level 2.
     */
    private static final int damageLevel2 = 6;
    /**
     * explosion damage at level 3.
     */
    private static final int damageLevel3 = 9;
    /**
     * explosion damage at level 3.
     */
    private static final int explosionsPerLevel = 2;

    private final Handler handler;
    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public ExplosionGun(final Handler handler) {

        this.handler = handler; 
        this.cycle = explosionCycle;
        this.damageLvl1 = damageLevel1;
        this.damageLvl2 = damageLevel2;
        this.damageLvl3 = damageLevel3;
    }

    /**
     * creates explosions around the player.
     */
    @Override
    protected void shoot() {
        final GameObject player = handler.getPlayer();
        final float x = player.getX();
        final float y = player.getY();
        Pair<Float, Float> pair;

        for (int i = 0; i <= currentLevel * explosionsPerLevel; i++) {
            pair = Func.randomPoint(minRange, maxRange);
            handler.addObject(new Explosion(pair.getX() + x, pair.getY() + y, handler, getDamage()));
        }
    }
}
