package it.unibo.geosurv.model.weapons.laserGun;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.weapons.Weapon;


public class LaserGun extends Weapon {
	
	private final static long CYCLE = 3000L; // cycle every x milliseconds
	
	private Handler handler;
	private long lastLaser;
	private GameObject player;
	private int laserHeight = 30;
	private int laserWidth = 300;

	public LaserGun(float x, float y, Handler handler) {
		super(x, y);
		this.handler = handler; 
		this.lastLaser = 0;
		player = handler.getPlayer();
	}

	@Override
	public void tick() {
		if (this.checkTime()) {
			this.shoot();
		}
	}
	
	private boolean checkTime() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - this.lastLaser) > CYCLE) {
			this.lastLaser = currentTime;
			return true;
		}
		return false;
	}
	
	private void shoot() {
		float px = this.player.getX();
		float py = this.player.getY();

		float yCorrection = (48 - laserHeight) / 2;
		this.handler.addObject(new Laser(px + 32, py + yCorrection, this.handler, laserWidth, laserHeight)); // right
		this.handler.addObject(new Laser(px - laserWidth, py + yCorrection, this.handler, laserWidth, laserHeight)); // left
		this.handler.addObject(new Laser(px, py - laserWidth, this.handler, laserHeight, laserWidth)); // up
		this.handler.addObject(new Laser(px, py + 48, this.handler, laserHeight, laserWidth)); // down
	}
}
