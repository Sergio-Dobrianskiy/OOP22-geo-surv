package it.unibo.geosurv.model.weapons.explosionGun;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;
import it.unibo.geosurv.model.weapons.Weapon;

public class ExplosionGun extends Weapon {
	
	private final float MIN_RANGE = 10f;
	private final float MAX_RANGE = 200f;
	protected final long EXPLOSION_CYCLE = 4000L;
			
	private Handler handler;
	private int numberOfExplosions = 5;  // test

	public ExplosionGun(Handler handler) {
		super();
		this.handler = handler; 
		this.cicle = EXPLOSION_CYCLE;
	}
	
	@Override
	protected void shoot() {
		GameObject player = handler.getPlayer();
		float x = player.getX();
		float y = player.getY();
		Pair<Float, Float> pair;
		for (int i = 0; i <= numberOfExplosions; i++) {
			pair = Func.randomPoint(MIN_RANGE, MAX_RANGE);
			handler.addObject(new Explosion(pair.getX() + x, pair.getY() + y, handler));
		}
	}
}
