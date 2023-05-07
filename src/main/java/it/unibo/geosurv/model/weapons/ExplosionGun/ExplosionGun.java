package it.unibo.geosurv.model.weapons.explosionGun;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;
import it.unibo.geosurv.model.weapons.Weapon;

public class ExplosionGun extends Weapon {
	
	private final static long CYCLE = 3000L; // cycle every x milliseconds
	
	private Handler handler;
	private long lastExplosions;

	public ExplosionGun(float x, float y, Handler handler) {
		super(x, y);
		this.handler = handler; 
		this.lastExplosions = 0;
	}

	@Override
	public void tick() {
		if (this.checkTime()) {
			this.shoot();
		}
	}
	
	private boolean checkTime() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - this.lastExplosions) > CYCLE) {
			this.lastExplosions = currentTime;
			return true;
		}
		return false;
	}
	
	private void shoot() {
		GameObject player = handler.getPlayer();
		float x = player.getX();
		float y = player.getY();
		Pair<Float, Float> pair;
		for (int i = 0; i <= 5; i++) {
			pair = Func.randomPoint(10f, 200f);
			handler.addObject(new Explosion(pair.getX() + x, pair.getY() + y, handler));
		}
	}
}
