package it.unibo.geosurv.model.weapons;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import it.unibo.geosurv.model.Collisions;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.weapons.autogun.BulletImpl;

public abstract class Bullet extends GameObject {

	protected int damage = 1;

	protected long lifeSpan = 5000L; // max milliseconds of life
	protected long creationTime;
	protected final Handler handler;
	private Collisions collisions;

	public Bullet(float x, float y, Handler handler) {
		super(x, y, ID.Bullet);
		this.handler = handler;
		this.creationTime = System.currentTimeMillis();
		this.collisions = new Collisions(handler);
	}

	@Override
	public void tick() {
		if (this.stillAlive()) {
			updatePosition(this.velX, this.velY);
			this.collisions.checkBulletCollisionss(this);
		} else {
			handler.removeObject(this);
		}
	}

	private void updatePosition(final float velX, final float velY) {
		x += velX;
		y += velY;
	}

	protected boolean stillAlive() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - this.creationTime) > lifeSpan) {
			handler.removeObject(this);
			return false;
		}
		return true;
	}

	
	public RectangularShape getShape() {
        return this.setRectangleShape();
    }
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
