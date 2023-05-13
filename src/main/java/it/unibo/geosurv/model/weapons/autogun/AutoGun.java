package it.unibo.geosurv.model.weapons.autogun;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.model.utility.Func;
import it.unibo.geosurv.model.weapons.Weapon;

public class AutoGun extends Weapon {
	
	private final int BULLET_VELOCITY = 10;
	private final int MAX_RANGE = 400;
	
	private Handler handler;
	private GameObject player;
	private GameObject closestEnemy;
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
		this.levelUp();
		this.levelUp();
		this.levelUp(); // testing
	}
	
	@Override
	public void tick() {
		double second = Game.SECOND_IN_NANO;

		if (currentLevel >= 0) { 							// TODO: sistemare
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
				this.closestEnemyDistance = (float) Point2D.distance(player.getX(), player.getX(), 
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
	
	
	protected void shoot() {
		int mx = (int) this.closestEnemy.getX();  // TODO: gun is aiming the upper left corner
		int my = (int) this.closestEnemy.getY();
		float px = player.getX();
		float py = player.getY();
		GameObject tempBullet = handler.addObject(new BulletImpl(px + MainPlayer.HALF_PLAYER_WIDTH, py + MainPlayer.HALF_PLAYER_HEIGHT, handler));
		float angle = (float) Math.atan2(my - py - MainPlayer.HALF_PLAYER_WIDTH, mx - px - MainPlayer.HALF_PLAYER_HEIGHT);
		tempBullet.setVelX((float) ((BULLET_VELOCITY) * Math.cos(angle)));
		tempBullet.setVelY((float) ((BULLET_VELOCITY) * Math.sin(angle)));
	}
	
}
