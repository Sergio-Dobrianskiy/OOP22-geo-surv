package it.unibo.geosurv.control.weapons;

import java.util.Optional;

public interface IWeaponFactory {
    
    /**
     * Create a Block.
     * 
     * @return the created block
     */
    Optional<Weapon> createWeapon(WeaponType type, int level);

}
