package it.unibo.geosurv.control.weapons;

import it.unibo.geosurv.control.TickingObject;

public abstract class Weapon implements TickingObject {
	
	/**
     * maximum level of a weapon
     */
	protected static final int MAX_LVL = 4;
	
	/**
     * time in milliseconds of the last shot
     */
	protected long lastShoot = 0;
	
	/**
     * time in milliseconds between shots
     */
	protected long cycle = 2000L;
	
	/**
     * current level of a weapon
     */
	protected int currentLevel = 0;
	
	
	/**
     * shoots every cycle
     */
	public void tick() {
		if (this.checkTime()) {
			this.shoot();
		}
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
	
	/**
     * resets the weapon level
     */
	protected void resetLevel() {
		currentLevel = 0;
	}
	
	/**
     * check if an amount of time has passed
     */
	private boolean checkTime() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - this.lastShoot) > this.cycle) {
			this.lastShoot = currentTime;
			return true;
		}
		return false;
	}
	
	protected abstract void shoot();

}
