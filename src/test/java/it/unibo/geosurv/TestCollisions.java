package it.unibo.geosurv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.geosurv.control.weapons.AutoGun;
import it.unibo.geosurv.control.weapons.ExplosionGun;
import it.unibo.geosurv.control.weapons.LaserGun;
import it.unibo.geosurv.control.weapons.SatelliteGun;
import it.unibo.geosurv.control.weapons.Weapon;
import it.unibo.geosurv.control.weapons.WeaponFactory;
import it.unibo.geosurv.control.weapons.WeaponType;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.block.Block;
import it.unibo.geosurv.model.block.BlockFactory;
import it.unibo.geosurv.model.block.BlockType;
import it.unibo.geosurv.model.player.Player;

/**
 * testing collisions.
 *
 */
class TestCollisions {
    @Test
    void firstTest() {

        final Handler handler = new Handler();
        handler.addPlayer(new Player(0, 0, handler));
        final BlockFactory blockFactory = new BlockFactory();
        final Optional<Block> block = blockFactory.createBlock(BlockType.WALL, 0, 0);
        handler.addObject(block.get());
        
//        weapon = weaponFactory.createWeapon(WeaponType.LaserGun, 0).get();
//        assertEquals(LaserGun.class, weapon.getClass());
    }
}
