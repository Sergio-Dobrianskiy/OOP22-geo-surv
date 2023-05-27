package it.unibo.geosurv.control.weapons;

import java.util.ArrayList;
import java.util.Random;

/**
 * Manages Weapons' levels.
 */
public class WeaponLevels {

    private ArrayList<Weapon> weapons;

    /**
     * Constructor for this class.
     *
     * @param weapons array of player's weapons
     */
    public WeaponLevels(final ArrayList<Weapon> weapons) {
        this.weapons = weapons;
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
