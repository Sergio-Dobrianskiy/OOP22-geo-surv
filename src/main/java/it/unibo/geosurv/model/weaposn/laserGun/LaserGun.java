package it.unibo.geosurv.model.weaposn.laserGun;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.weapons.Weapon;


public class LaserGun extends Weapon {
	
	private final static long CYCLE = 3000L; // cycle every x milliseconds
	
	private Handler handler;
	private long lastLaser;
	private GameObject player;

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
		System.out.println("laser" + px + " " + py);
		handler.addObject(new Laser(px, py, handler, 1000, 100));
	}
}
