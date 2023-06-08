package it.unibo.geosurv.control.weapons;

import java.util.List;
import java.util.Random;

/**
 * Manages Weapons' levels.
 */
public class WeaponLevels {

    private final List<Weapon> weapons;
    private final Random random;

    /**
     * Constructor for this class.
     *
     * @param w array of player's weapons
     */
    public WeaponLevels(final List<Weapon> w) {
        this.weapons = w;
        this.random = new Random();
    }

    /**
     * levels up a random weapon.
     */
    public void levelUpWeapon() {
        boolean upgraded = false;
        while (!upgraded) {
            int index = this.random.nextInt(this.weapons.size());
            Weapon tmpWeapon = weapons.get(index);
            upgraded = tmpWeapon.levelUp();
        }
    }
}
