package it.unibo.geosurv.model.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

public class AutoGun extends Weapon {
	
	private Handler handler;
//	private SpriteSheet ss;
	
	private int autoGunLvl = 3;
	private GameObject player;
	private GameObject closestEnemy;
	
	private double delta = 0;
	private boolean secondaryShooting = false;
	private boolean tertiaryShooting = false;
	
	private long lastTime;
	private float closestEnemyDistance;
	private float bulletVelocity = 10;

	public AutoGun(float x, float y, Handler handler) {
		super(x, y, ID.Weapon);
		this.handler = handler;
		this.lastTime = System.nanoTime();
		this.player = handler.getPlayer();
	}
	
	@Override
	public void tick() {
		double second = 1000000000;

		if (autoGunLvl >= 0) { 							// TODO: sistemare
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
				this.findClosestEnemy();
				System.out.println("distance: " + this.closestEnemyDistance);
				
				if (this.closestEnemyDistance <= 400) {
					System.out.println("shoot");
					this.shoot();
					
					if (autoGunLvl >= 2) {				// TODO: sistemare
						this.secondaryShooting = true;
					}
					
					if (autoGunLvl >= 3) {				// TODO: sistemare
						this.tertiaryShooting = true;
					}
				}
			}
		}
	}
	
	
	public void findClosestEnemy() {
		float closestDistance = Float.MAX_VALUE;
		float distance;
		LinkedList<GameObject> tmpObjects = handler.getObjects();
		GameObject tmpObject;
		float px, py;
		px = this.player.getX();
		py = this.player.getY();
		
		
		for (int i = 0; i < tmpObjects.size(); i++) {
			tmpObject = tmpObjects.get(i);
			if (tmpObject.getId() == ID.Monster) {
				float ex, ey;
				
				ex = tmpObject.getX();
				ey = tmpObject.getY();
				
				distance = (float) Point2D.distance(px, py, ex, ey);
				if (distance < closestDistance) {
					closestDistance = distance;
					this.closestEnemy = tmpObject;
				}
			}
		}
		this.closestEnemyDistance = closestDistance;
	}
	
	private void shoot() {
		int mx = (int) this.closestEnemy.getX();
		int my = (int) this.closestEnemy.getY();
		float px = player.getX();
		float py = player.getY();
		GameObject tempBullet = handler.addObject(new BulletImpl(px + 16, py + 24, handler));
		float angle = (float) Math.atan2(my - py - 16, mx - px - 24);
		tempBullet.setVelX((float) ((bulletVelocity) * Math.cos(angle)));
		tempBullet.setVelY((float) ((bulletVelocity) * Math.sin(angle)));
	}
	
}
