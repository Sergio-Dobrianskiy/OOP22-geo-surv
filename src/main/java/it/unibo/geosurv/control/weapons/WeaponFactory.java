package it.unibo.geosurv.control.weapons;

import java.util.Optional;
import it.unibo.geosurv.model.Handler;

/**
 * Factory that creates Weapons.
 */
public class WeaponFactory implements IWeaponFactory {
    private final Handler handler;

    /**
     * Constructor for this class.
     * 
     * @param handler
     */
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
    public Optional<Weapon> createWeapon(final WeaponType type, final int level) {
        Weapon weapon;
        switch (type) {
        case AUTO_GUN:
            weapon = new AutoGun(this.handler);
            break;
        case EXPLOSION_GUN:
            weapon = new ExplosionGun(this.handler);
            break;
        case LASER_GUN:
            weapon = new LaserGun(this.handler);
            break;
        case SATELLITE_GUN:
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
