package it.unibo.geosurv.model.weapons;

import java.awt.Graphics;
import java.awt.geom.RectangularShape;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.ID;

public abstract class Weapon extends GameObject {
	
	/**
     * maximum level of a weapon
     */
	protected static final int MAX_LVL = 4;
	
	/**
     * time in milliseconds between shots
     */
	protected long cicle = 2000L;
	
	/**
     * current level of a weapon
     */
	protected int currentLevel = 0;
	
	/**
     * time weapon has shoot
     */
	protected long lastShoot = 0;

	public Weapon() {
		super(0, 0, ID.Weapon);
		this.lastShoot = 0;
	}
	
	@Override
	public void tick() {
		if (this.checkTime()) {
			this.shoot();
		}
	}

	/**
     * a weapon is not rendered
     */
	@Override
	public void render(Graphics g) {
	}

	/**
     * a weapon doesn't have a shape / collision box
     */
	@Override
	public RectangularShape getShape() {
		return null;
	}
	
	
	/**
     * raises weapon level by one 
     * 
     * @return boolean true if level was raised, false if it was already at maximum level
     */
	protected boolean levelUp() {
		if (currentLevel < MAX_LVL) {
			currentLevel += 1;
			return true;
		}
		return false;
	}
	
	private boolean checkTime() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - this.lastShoot) > this.cicle) {
			this.lastShoot = currentTime;
			return true;
		}
		return false;
	}
	
	protected void shoot() {
		
	}

}
