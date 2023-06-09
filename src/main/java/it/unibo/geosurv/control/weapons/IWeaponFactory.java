package it.unibo.geosurv.control.weapons;

import java.util.Optional;

/**
 * Interface for WeaponFactory class.
 */
public interface IWeaponFactory {
    /**
     * Create a Block.
     * @param type of weapon to create
     * @param level of weapon to create
     * @return the created block
     */
    Optional<Weapon> createWeapon(WeaponType type, int level);
}
