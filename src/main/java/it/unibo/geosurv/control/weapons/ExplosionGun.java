package it.unibo.geosurv.control.weapons;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.bullets.Explosion;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

public class ExplosionGun extends Weapon {
	
	private final float MIN_RANGE = 10f;
	private final float MAX_RANGE = 200f;
	protected final long EXPLOSION_CYCLE = 4000L;
	private final int DAMAGE_LVL_1 = 3;
	private final int DAMAGE_LVL_2 = 6;
	private final int DAMAGE_LVL_3 = 9;
			
	private Handler handler;
	private int numberOfExplosions = 2;  // test

	public ExplosionGun(Handler handler) {
		super();
		this.handler = handler; 
		this.cycle = EXPLOSION_CYCLE;
		this.damageLvl1 = DAMAGE_LVL_1;
		this.damageLvl1 = DAMAGE_LVL_2;
		this.damageLvl1 = DAMAGE_LVL_3;
	}
	
	/**
	 * creates explosions around the player
	 */
	@Override
	protected void shoot() {
		GameObject player = handler.getPlayer();
		float x = player.getX();
		float y = player.getY();
		Pair<Float, Float> pair;
			
		for (int i = 0; i <= currentLevel * numberOfExplosions; i++) {
			pair = Func.randomPoint(MIN_RANGE, MAX_RANGE);
			handler.addObject(new Explosion(pair.getX() + x, pair.getY() + y, handler, getDamage()));
		}
	}
}
