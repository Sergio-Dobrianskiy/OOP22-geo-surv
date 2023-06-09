package it.unibo.geosurv.control.weapons;

import it.unibo.geosurv.control.ITickingObject;

/**
 * Represents a Weapon.
 */
public abstract class Weapon implements ITickingObject {
    /**
     * default Weapon damage at level 1.
     */
    protected int damageLvl1 = 2;
    /**
     * default Weapon damage at level 2.
     */
    protected int damageLvl2 = 4;
    /**
     * default Weapon damage at level 3.
     */
    protected int damageLvl3 = 6;

    /**
     * maximum level of a weapon.
     */
    protected static final int MAX_LVL = 3;

    /**
     * time in milliseconds of the last shot.
     */
    private long lastShoot = 0;

    /**
     * default time in milliseconds between shots.
     */
    protected static long CYCLE = 2000L;

    /**
     * current level of a weapon.
     */
    protected int currentLevel = 0;

    /**
     * shoot every cycle.
     */
    public void tick() {
        if (this.currentLevel > 0 && this.isNewCycle()) {
            this.shoot();
        }
    }

    /**
     * shoot weapon's bullet.
     */
    protected abstract void shoot();

    /**
     * raises weapon level by one .
     * 
     * @return boolean true if level was raised, false if it was already at maximum level
     */
    public boolean levelUp() {
        if (currentLevel < MAX_LVL) {
            currentLevel += 1;
            return true;
        }
        return false;
    }

    /**
     * resets the weapon level.
     */
    protected void resetLevel() {
        currentLevel = 0;
    }

    /**
     * check if an amount of time has passed.
     * 
     * @return boolean returns true if a new cycle has started
     */
    private boolean isNewCycle() {
        final long currentTime = System.currentTimeMillis();
        if ((currentTime - this.lastShoot) > this.CYCLE) {
            this.lastShoot = currentTime;
            return true;
        }
        return false;
    }

    /**
     * returns weapon damage based on it's level.
     * 
     * @return int damage
     */
    protected int getDamage() {
        switch (this.currentLevel) {
            case 1:
                return this.damageLvl1;
            case 2:
                return this.damageLvl2;
            case 3:
                return this.damageLvl3;
            default:
                return 0;
        }
    }

    /**
     * returns weapon level.
     * @return 
     * 
     * @return int level
     */
    public int getLevel() {
        return this.currentLevel;
    }
}
