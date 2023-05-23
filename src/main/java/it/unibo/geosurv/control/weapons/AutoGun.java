package it.unibo.geosurv.control.weapons;

import java.awt.geom.Point2D;
import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.IGameObject;
import it.unibo.geosurv.model.bullets.BulletImpl;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.utility.Pair;

public class AutoGun extends Weapon {
	
	private final int BULLET_VELOCITY = 10;
	private final int MAX_RANGE = 400;
	private final int DAMAGE_LVL_1 = 3;
	private final int DAMAGE_LVL_2 = 4;
	private final int DAMAGE_LVL_3 = 5;
	
	private Handler handler;
	private IGameObject player;
	private IGameObject closestEnemy;
	private long lastTime;
	private float closestEnemyDistance;
	private double delta = 0;
	private boolean secondaryShooting = false;
	private boolean tertiaryShooting = false;

	public AutoGun(Handler handler) {
		super();
		this.handler = handler;
		this.lastTime = System.nanoTime();
		this.player = handler.getPlayer();
		this.damageLvl1 = DAMAGE_LVL_1;
		this.damageLvl1 = DAMAGE_LVL_2;
		this.damageLvl1 = DAMAGE_LVL_3;
	}
	
	/**
	 * shoots 1/2/3 bullets at the nearest enemy
	 */
	@Override
	public void tick() {
		double second = Game.SECOND_IN_NANO;

		if (currentLevel > 0) { 							// TODO: sistemare
			long now = System.nanoTime();
			this.delta = (now - this.lastTime) / second;
			
			if (this.delta > 0.1 && this.secondaryShooting == true) {
				this.shoot();
				this.secondaryShooting = false;
			}
			
			if (this.delta > 0.2 && this.tertiaryShooting == true) {
				this.shoot();
				this.tertiaryShooting = false;
			}
			
			if (this.delta >= 1) {
				this.lastTime = now;
				this.closestEnemy = Func.findClosestEnemy(handler);
				if (this.closestEnemy == null) {
					return;
				}

				this.closestEnemyDistance = (float) Point2D.distance(player.getX(), player.getY(), 
						closestEnemy.getX(), closestEnemy.getY());
				if (this.closestEnemyDistance <= MAX_RANGE) {
					this.shoot();
					
					if (currentLevel >= 2) {				// TODO: sistemare
						this.secondaryShooting = true;
					}
					
					if (currentLevel >= 3) {				// TODO: sistemare
						this.tertiaryShooting = true;
					}
				}
			}
		}
	}
	
	
	/**
	 * shoots a bullet at the nearest enemy
	 */
	protected void shoot() {
		Pair<Float, Float> angle = Func.findAngle2(this.player, this.closestEnemy);
		IGameObject tempBullet = handler.addObject(new BulletImpl(player.getX(), player.getY(), handler, this.getDamage()));
		tempBullet.setVelX((float) ((BULLET_VELOCITY) * angle.getX()));
		tempBullet.setVelY((float) ((BULLET_VELOCITY) * angle.getY()));
	}
}
