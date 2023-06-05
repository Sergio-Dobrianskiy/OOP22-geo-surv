package it.unibo.geosurv.control.weapons;

import java.util.List;
import java.util.Random;

/**
 * Manages Weapons' levels.
 */
public class WeaponLevels {

    private List<Weapon> weapons;

    /**
     * Constructor for this class.
     *
     * @param w array of player's weapons
     */
    public WeaponLevels(final List<Weapon> w) {
        this.weapons = w;
    }

    /**
     * levels up a random weapon.
     */
    public void levelUpWeapon() {
        boolean upgraded = false;
        Random random = new Random();
        while (!upgraded) {
            int index = random.nextInt(this.weapons.size());
            Weapon tmpWeapon = weapons.get(index);
            upgraded = tmpWeapon.levelUp();
        }
    }
}
