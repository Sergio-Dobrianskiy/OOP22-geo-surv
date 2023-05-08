package it.unibo.geosurv.model.weapons.laserGun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.weapons.Bullet;

public class Laser extends Bullet {
	
	protected final long LIFE_SPAN = 2000L;
	
	private int height;
	private int width;
	private GameObject player;
	private int initialX;
	private int initialY;

	public Laser(float x, float y, Handler handler, final int width, final int height) {
		super(x, y, handler);
		this.lifeSpan = LIFE_SPAN;
		this.width = width;
		this.height = height;
		this.player = handler.getPlayer();
		this.initialX = (int) player.getX();
		this.initialY = (int) player.getY();
	}
	
	/**
     * calculate x coordinate of the laser taking into account the player movements
     * 
     * @param float x coordinate at the time of the laser creation
     * 
     * @return int x coordinate at the time of render/collision
     */
	private int currentX(float n) {
		int px = (int) player.getX();
		return (int) n - initialX + px;
	}
	
	/**
     * calculate y coordinate of the laser taking into account the player movements
     * 
     * @param float y coordinate at the time of the laser creation
     * 
     * @return int y coordinate at the time of render/collision
     */
	private int currentY(float n) {
		int py = (int) player.getY();
		return (int) n - initialY + py;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(this.currentX(x), this.currentY(y), this.width, this.height);
	}

	@Override
	public Rectangle getShape() {
		return new Rectangle(this.currentX(x), this.currentY(y), this.width, this.height);
	}

}
