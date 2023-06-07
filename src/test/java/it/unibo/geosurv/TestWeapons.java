package it.unibo.geosurv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import it.unibo.geosurv.control.weapons.AutoGun;
import it.unibo.geosurv.control.weapons.ExplosionGun;
import it.unibo.geosurv.control.weapons.LaserGun;
import it.unibo.geosurv.control.weapons.SatelliteGun;
import it.unibo.geosurv.control.weapons.Weapon;
import it.unibo.geosurv.control.weapons.WeaponFactory;
import it.unibo.geosurv.control.weapons.WeaponType;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.Player;

/**
 * testing weapons.
 *
 */
public class TestWeapons<A> {
    @Test
    void createWeapons() {

        final Handler handler = new Handler();
        Weapon weapon;
        final WeaponFactory weaponFactory = new WeaponFactory(handler);
        handler.addPlayer(new Player(0, 0, handler));

        weapon = weaponFactory.createWeapon(WeaponType.AutoGun, 0).get();
        assertEquals(AutoGun.class, weapon.getClass());

        weapon = weaponFactory.createWeapon(WeaponType.SatelliteGun, 0).get();
        assertEquals(SatelliteGun.class, weapon.getClass());

        weapon = weaponFactory.createWeapon(WeaponType.ExplosionGun, 0).get();
        assertEquals(ExplosionGun.class, weapon.getClass());

        weapon = weaponFactory.createWeapon(WeaponType.LaserGun, 0).get();
        assertEquals(LaserGun.class, weapon.getClass());
    }

    @Test
    void autoGunLevels() {

        final Handler handler = new Handler();
        Weapon weapon;
        final WeaponFactory weaponFactory = new WeaponFactory(handler);
        handler.addPlayer(new Player(0, 0, handler));

        weapon = weaponFactory.createWeapon(WeaponType.AutoGun, 0).get();
        assertEquals(0, weapon.getLevel());
        weapon.levelUp();
        assertEquals(1, weapon.getLevel());
        weapon.levelUp();
        assertEquals(2, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());
    }

    @Test
    void satelliteGunLevels() {

        final Handler handler = new Handler();
        Weapon weapon;
        final WeaponFactory weaponFactory = new WeaponFactory(handler);
        handler.addPlayer(new Player(0, 0, handler));

        weapon = weaponFactory.createWeapon(WeaponType.SatelliteGun, 0).get();
        assertEquals(0, weapon.getLevel());
        weapon.levelUp();
        assertEquals(1, weapon.getLevel());
        weapon.levelUp();
        assertEquals(2, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());
    }

    @Test
    void explosionGunLevels() {

        final Handler handler = new Handler();
        Weapon weapon;
        final WeaponFactory weaponFactory = new WeaponFactory(handler);
        handler.addPlayer(new Player(0, 0, handler));

        weapon = weaponFactory.createWeapon(WeaponType.ExplosionGun, 0).get();
        assertEquals(0, weapon.getLevel());
        weapon.levelUp();
        assertEquals(1, weapon.getLevel());
        weapon.levelUp();
        assertEquals(2, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());
    }

    @Test
    void laseroGunLevels() {

        final Handler handler = new Handler();
        Weapon weapon;
        final WeaponFactory weaponFactory = new WeaponFactory(handler);
        handler.addPlayer(new Player(0, 0, handler));

        weapon = weaponFactory.createWeapon(WeaponType.LaserGun, 0).get();
        assertEquals(0, weapon.getLevel());
        weapon.levelUp();
        assertEquals(1, weapon.getLevel());
        weapon.levelUp();
        assertEquals(2, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());
    }

    @Test
    void specificLevels() {

        final Handler handler = new Handler();
        Weapon weapon;
        final WeaponFactory weaponFactory = new WeaponFactory(handler);
        handler.addPlayer(new Player(0, 0, handler));

        weapon = weaponFactory.createWeapon(WeaponType.LaserGun, 1).get();
        assertEquals(1, weapon.getLevel());
        weapon.levelUp();
        assertEquals(2, weapon.getLevel());

        weapon = weaponFactory.createWeapon(WeaponType.AutoGun, 2).get();
        assertEquals(2, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());

        weapon = weaponFactory.createWeapon(WeaponType.ExplosionGun, 3).get();
        assertEquals(3, weapon.getLevel());
        weapon.levelUp();
        assertEquals(3, weapon.getLevel());
    }

    @Test
    void createJustWeapons() {

        final Handler handler = new Handler();
        ArrayList<Weapon> weapons = new ArrayList<>();
        // Weapon weapon;
        final WeaponFactory weaponFactory = new WeaponFactory(handler);
        handler.addPlayer(new Player(0, 0, handler));

        weapons.add(weaponFactory.createWeapon(WeaponType.AutoGun, 0).get());
        weapons.add(weaponFactory.createWeapon(WeaponType.SatelliteGun, 0).get());
        weapons.add(weaponFactory.createWeapon(WeaponType.ExplosionGun, 0).get());
        weapons.add(weaponFactory.createWeapon(WeaponType.LaserGun, 0).get());

        weapons.forEach(w -> assertTrue(isJustWeapons(w)));

        weapons.forEach(w -> handler.addTickingObject(w));

        handler.getTickingbjects().forEach(w -> assertTrue(isJustWeapons(w)));
        handler.getGameObjects().forEach(w -> assertFalse(isJustWeapons(w)));
    }

    /**
     * check if object's superclass is Weapon.
     * 
     * @param obj object to check
     * 
     * @return true if superclass is Weapon
     */
    boolean isJustWeapons(final Object obj) {
        return obj.getClass().getSuperclass() == Weapon.class;
    }

}
