package it.unibo.geosurv.control.weapons;

import java.util.Optional;
import it.unibo.geosurv.model.Handler;

/**
 * Factory that creates Weapons.
 */
public class WeaponFactory implements IWeaponFactory {

    private Handler handler;
    
    public WeaponFactory(final Handler handler) {
        this.handler = handler;
    }
    /**
     * Create a Block.
     * 
     * @param type of the weapon
     * @param level at witch weapon starts the game
     * 
     * @return the created weapon
     */
    @Override
    public Optional<Weapon> createWeapon(WeaponType type, int level) {
        Weapon weapon;
        switch (type) {
        case AutoGun:
            weapon = new AutoGun(this.handler);
            break;
        case ExplosionGun:
            weapon = new ExplosionGun(this.handler);
            break;
        case LaserGun:
            weapon = new LaserGun(this.handler);
            break;
        case SatelliteGun:
            weapon = new SatelliteGun(this.handler);
            break;
        default:
            return Optional.empty();
        }
        
        while (level > weapon.getLevel()) {
            weapon.levelUp();
        }
        
        return Optional.of(weapon);
    }

}
