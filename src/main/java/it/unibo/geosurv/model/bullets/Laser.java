package it.unibo.geosurv.model.bullets;

import java.awt.Rectangle;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.view.graphics.Texture;

/**
 * Represents a Laser Bullet.
 */
public class Laser extends Bullet {

    /**
     * laser's life span.
     */
    private final long laserlifeSpan = 2000L;

    private GameObject player;
    private int initialPlayerX;
    private int initialPlayerY;
    private int initialLaserX;
    private int initialLaserY;

    /**
     * Constructor for this class.
     *
     * @param x laser coordinate
     * @param y laser coordinate
     * @param handler game's Handler
     * @param damage laser damage
     * @param width laser width
     * @param height laser height
     * @param texture laser texture
     */
    public Laser(final float x, final float y, final Handler handler, final int damage, final int width, final int height, final Texture texture) {
        super(x, y, handler, damage);
        this.width = width;
        this.height = height;
        this.player = handler.getPlayer();
        this.initialPlayerX = (int) player.getX();
        this.initialPlayerY = (int) player.getY();
        this.initialLaserX = (int) x;
        this.initialLaserY = (int) y;
        this.texture = texture;
        this.lifeSpan = laserlifeSpan;
    }

    /**
     * laser follows the player.
     */
    @Override
    public void tick() {
        super.tick();
        this.x = currentX();
        this.y = currentY();
    }

    /**
     * calculate x coordinate of the laser taking into account the player movements.
     * 
     * @return int x coordinate at the time of render/collision
     */
    private int currentX() {
        return (int) player.getX() - initialPlayerX + initialLaserX;
    }

    /**
     * calculate y coordinate of the laser taking into account the player movements.
     * 
     * @return int y coordinate at the time of render/collision
     */
    private int currentY() {
        return (int) player.getY() - initialPlayerY + initialLaserY;
    }

    /**
     * adapts getShape to the laser.
     */
    @Override
    public Rectangle getShape() {
        return new Rectangle((int) x - (this.width / 2), (int) y - (this.height / 2), this.width, this.height);
    }

}
