package it.unibo.geosurv.model.bullets;

import it.unibo.geosurv.model.Collisions;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

public abstract class Bullet extends GameObject {

	protected int damage = 1;
	protected long lifeSpan = 5000L; 	// max milliseconds of life
	protected long creationTime;
	protected final Handler handler;
	private Collisions collisions;

	public Bullet(float x, float y, final Handler handler, final int damage) {
		super(x, y, ID.Bullet);
		this.handler = handler;
		this.creationTime = System.currentTimeMillis();
		this.collisions = new Collisions(handler);
		this.damage = damage;
	}

	/**
	 * checks if the bullet is still alive, otherwise it removes it from the game
	 */
	@Override
	public void tick() {
		if (this.stillAlive()) {
			updatePosition(this.velX, this.velY);
			this.collisions.checkBulletCollisionss(this);
		} else {
			handler.removeObject(this);
		}
	}

	/**
	 * updates bullet position on the field
	 */
	private void updatePosition(final float velX, final float velY) {
		x += velX;
		y += velY;
	}

	/**
	 * checks if the bullet has expired it's life span
	 */
	protected boolean stillAlive() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - this.creationTime) > lifeSpan) {
			handler.removeObject(this);
			return false;
		}
		return true;
	}

	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
