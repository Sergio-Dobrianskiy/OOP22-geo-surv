package it.unibo.geosurv.model.bullets;

import java.awt.Rectangle;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.view.graphics.Texture;

public class Laser extends Bullet {
	
	protected final long LIFE_SPAN = 2000L;
	
	private GameObject player;
	private int initialPlayerX;
	private int initialPlayerY;
	private int initialLaserX;
	private int initialLaserY;

	public Laser(float x, float y, Handler handler, final int width, final int height, Texture texture) {
		super(x, y, handler);
		this.lifeSpan = LIFE_SPAN;
		this.width = width;
		this.height = height;
		this.player = handler.getPlayer();
		this.initialPlayerX = (int) player.getX();
		this.initialPlayerY = (int) player.getY();
		this.initialLaserX = (int) x;
		this.initialLaserY = (int) y;
		this.texture = texture;
	}
	
	
	/**
     * laser follows the player
     */
	@Override
	public void tick() {
		super.tick();
		this.x = currentX();
		this.y = currentY();
	}

	
	/**
     * calculate x coordinate of the laser taking into account the player movements
     * 
     * @return int x coordinate at the time of render/collision
     */
	private int currentX() {
		return (int) player.getX() - initialPlayerX + initialLaserX;
	}
	
	/**
     * calculate y coordinate of the laser taking into account the player movements
     * 
     * @return int y coordinate at the time of render/collision
     */
	private int currentY() {
		return (int) player.getY() - initialPlayerY + initialLaserY;
	}

	@Override
	public Rectangle getShape() {
		return new Rectangle((int) x - (this.width / 2), (int) y - (this.height / 2), this.width, this.height);
	}

}
