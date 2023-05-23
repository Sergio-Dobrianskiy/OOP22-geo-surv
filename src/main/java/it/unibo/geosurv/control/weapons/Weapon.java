package it.unibo.geosurv.control.weapons;

import it.unibo.geosurv.control.TickingObject;

public abstract class Weapon implements TickingObject {
	
	protected int damageLvl1 = 1;
	protected int damageLvl2 = 2;
	protected int damageLvl3 = 3;
	
	/**
     * maximum level of a weapon
     */
	protected static final int MAX_LVL = 3;
	
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
     * shoot every cycle
     */
	public void tick() {
		if (this.currentLevel > 0 && this.checkTime()) {
			this.shoot();
		}
	}

	/**
     * shoot weapon's bullet
     */
	protected abstract void shoot();
	
	
	/**
     * raises weapon level by one 
     * 
     * @return boolean true if level was raised, false if it was already at maximum level
     */
	public boolean levelUp() {
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
	
	
	/**
     * returns weapon damage based on it's level
     * 
     * @return int damage
     */
	protected int getDamage() {
		switch (this.currentLevel) {
			case 1:
				return this.damageLvl1;
			case 2:
				return this.damageLvl2;
			case 3:
				return this.damageLvl3;
			default:
				return 0;
		}
	}

}
