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
    private static final float MIN_RANGE = 10f;
    /**
     * maximum range at witch explosion can appear.
     */
    private static final float MAX_RANGE = 200f;
    /**
     * explosion cycle in ms.
     */
    private static final long EXPLOSION_CYCLE = 4000L;
    /**
     * explosion damage at level 1.
     */
    private static final int DAMAGE_LEVEL_1 = 3;
    /**
     * explosion damage at level 2.
     */
    private static final int DAMAGE_LEVEL_2 = 6;
    /**
     * explosion damage at level 3.
     */
    private static final int DAMAGE_LEVEL_3 = 9;
    /**
     * explosion damage at level 3.
     */
    private static final int EXPLOSION_PER_LEVEL = 2;

    private final Handler handler;
    /**
     * Constructor for this class.
     *
     * @param handler game's Handler
     */
    public ExplosionGun(final Handler handler) {

        this.handler = handler; 
        this.CYCLE = EXPLOSION_CYCLE;
        this.damageLvl1 = DAMAGE_LEVEL_1;
        this.damageLvl2 = DAMAGE_LEVEL_2;
        this.damageLvl3 = DAMAGE_LEVEL_3;
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

        for (int i = 0; i <= currentLevel * EXPLOSION_PER_LEVEL; i++) {
            pair = Func.randomPoint(MIN_RANGE, MAX_RANGE);
            handler.addObject(new Explosion(pair.getX() + x, pair.getY() + y, handler, getDamage()));
        }
    }
}
