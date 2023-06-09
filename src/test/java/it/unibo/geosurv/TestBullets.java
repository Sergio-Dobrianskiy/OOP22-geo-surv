package it.unibo.geosurv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.bullets.AutoBullet;
import it.unibo.geosurv.model.bullets.Bullet;
import it.unibo.geosurv.model.bullets.Explosion;
import it.unibo.geosurv.model.bullets.Laser;
import it.unibo.geosurv.model.bullets.Satellite;
import it.unibo.geosurv.model.player.Player;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * testing bullets.
 *
 */
class TestBullets {

    /**
     * testing creation of bullets.
     */
    @Test
    void createBullets() {

        final Handler handler = new Handler();
        final int damage = 10;
        final int size = 10;
        Bullet bullet;
        handler.addPlayer(new Player(0, 0, handler));

        bullet = new AutoBullet(0, 0, handler, damage);
        assertEquals(AutoBullet.class, bullet.getClass());

        bullet = new Explosion(0, 0, handler, damage);
        assertEquals(Explosion.class, bullet.getClass());

        bullet = new Laser(0, 0, handler, damage, size, size, Texture.LASER_H);
        assertEquals(Laser.class, bullet.getClass());

        bullet = new Satellite(0, 0, handler, damage);
        assertEquals(Satellite.class, bullet.getClass());
    }

    /**
     * testing creation of bullets.
     */
    @Test
    void testBulletDamage() {

        final Handler handler = new Handler();
        final int damage = 10;
        final int size = 10;
        Bullet bullet;
        handler.addPlayer(new Player(0, 0, handler));

        bullet = new AutoBullet(0, 0, handler, damage);
        assertEquals(damage, bullet.getDamage());

        bullet = new Explosion(0, 0, handler, damage);
        assertEquals(damage, bullet.getDamage());

        bullet = new Laser(0, 0, handler, damage, size, size, Texture.LASER_H);
        assertEquals(damage, bullet.getDamage());

        bullet = new Satellite(0, 0, handler, damage);
        assertEquals(damage, bullet.getDamage());
    }
}
