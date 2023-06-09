package it.unibo.geosurv;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.block.Block;
import it.unibo.geosurv.model.block.BlockFactory;
import it.unibo.geosurv.model.block.BlockType;
import it.unibo.geosurv.model.bullets.AutoBullet;
import it.unibo.geosurv.model.bullets.Explosion;
import it.unibo.geosurv.model.bullets.Laser;
import it.unibo.geosurv.model.bullets.Satellite;
import it.unibo.geosurv.model.collisions.Collisions;
import it.unibo.geosurv.model.player.Player;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * testing collisions.
 *
 */
class TestCollisions {
    @Test
    void playerCollidesWithBlock() {
        final Handler handler = new Handler();                                          // create handler
        final Player player = new Player(0, 0, handler);                          // create player
        final BlockFactory blockFactory = new BlockFactory();                           // create bullet
        Optional<Block> block = blockFactory.createBlock(BlockType.WALL, 0, 0);   // create block on top of the bullet
        assertTrue(Collisions.isColliding(player, block.get(), ID.Block));  // should collide

        block = blockFactory.createBlock(BlockType.WALL, 1000, 1000);             // create block distant from the bullet
        assertFalse(Collisions.isColliding(player, block.get(), ID.Block)); // should not collide 
    }

    @Test
    void autoBulletCollidesWithBlock() {
        final Handler handler = new Handler();                                          // create handler
        AutoBullet bullet = new AutoBullet(0, 0, handler, 1);
        handler.addObject(bullet);
        final BlockFactory blockFactory = new BlockFactory();                           // create bullet
        final Optional<Block> block = blockFactory.createBlock(BlockType.WALL, 0, 0);   // create block on top of the bullet

        assertTrue(Collisions.isColliding(bullet, block.get(), ID.Block));  // should collide
        assertTrue(handler.getGameObjects().contains(bullet));                          // handler should contain bullet

        bullet.collide();
        assertFalse(handler.getGameObjects().contains(bullet));                         // handler should not contain bullet after collision

        bullet = new AutoBullet(1000, 1000, handler, 1);                  // create bullet away from block
        assertFalse(Collisions.isColliding(bullet, block.get(), ID.Block)); // should not collide 
    }

    @Test
    void satelliteCollidesWithBlock() {
        final Handler handler = new Handler();                                          // create handler
        Satellite satellite = new Satellite(0, 0, handler, 1);
        handler.addObject(satellite);
        final BlockFactory blockFactory = new BlockFactory();                           // create satellite
        final Optional<Block> block = blockFactory.createBlock(BlockType.WALL, 0, 0);   // create block on top of the satellite

        assertTrue(Collisions.isColliding(satellite, block.get(), ID.Block));  // should collide
        assertTrue(handler.getGameObjects().contains(satellite));                          // handler should contain satellite 

        satellite.collide();
        assertTrue(handler.getGameObjects().contains(satellite));                         // handler should still contain satellite after collision

        satellite = new Satellite(1000, 1000, handler, 1);                  // create satellite away from block
        assertFalse(Collisions.isColliding(satellite, block.get(), ID.Block)); // should not collide 
    }

    @Test
    void explosionCollidesWithBlock() {
        final Handler handler = new Handler();                                          // create handler
        Explosion explosion = new Explosion(0, 0, handler, 1);
        handler.addObject(explosion);
        final BlockFactory blockFactory = new BlockFactory();                           // create explosion
        final Optional<Block> block = blockFactory.createBlock(BlockType.WALL, 0, 0);   // create block on top of the explosion

        assertTrue(Collisions.isColliding(explosion, block.get(), ID.Block));  // should collide
        assertTrue(handler.getGameObjects().contains(explosion));                          // handler should contain explosion 

        explosion.collide();
        assertTrue(handler.getGameObjects().contains(explosion));                         // handler should still contain explosion after collision

        explosion = new Explosion(1000, 1000, handler, 1);                  // create satellite away from block
        assertFalse(Collisions.isColliding(explosion, block.get(), ID.Block)); // should not collide 
    }

    @Test
    void laserCollidesWithBlock() {
        final Handler handler = new Handler();
        final Player player = new Player(0, 0, handler);
        handler.addPlayer(player);
        Laser laser = new Laser(0, 0, handler, 1, 10, 10, Texture.LASER_H);
        handler.addObject(laser);
        final BlockFactory blockFactory = new BlockFactory();                           // create laser
        final Optional<Block> block = blockFactory.createBlock(BlockType.WALL, 0, 0);   // create block on top of the laser

        assertTrue(Collisions.isColliding(laser, block.get(), ID.Block));  // should collide
        assertTrue(handler.getGameObjects().contains(laser));                          // handler should contain laser 

        laser.collide();
        assertTrue(handler.getGameObjects().contains(laser));                         // handler should still contain laser after collision

        laser = new Laser(1000, 1000, handler, 1, 10, 10, Texture.LASER_H); // create laser away from block
        assertFalse(Collisions.isColliding(laser, block.get(), ID.Block)); // should not collide 
    }
}
