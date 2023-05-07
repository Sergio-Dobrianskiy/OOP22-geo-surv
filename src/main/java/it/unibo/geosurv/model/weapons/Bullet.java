package it.unibo.geosurv.model.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.weapons.explosionGun.Explosion;
import it.unibo.geosurv.model.weapons.satelliteGun.Satellite;
import it.unibo.geosurv.model.weaposn.laserGun.Laser;

public abstract class Bullet extends GameObject {
	
	protected long LIFE_SPAN = 10000L; // max milliseconds of life
	protected long creationTime;

	protected final Handler handler;
	
	public Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.creationTime = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		updatePosition(this.velX, this.velY);
		if (this.stillAlive()) {
			this.checkCollisions();
		}
	}
	
	private void updatePosition(final float velX, final float velY) {
		x += velX;
		y += velY;
	}
	
	protected boolean stillAlive() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - this.creationTime) > LIFE_SPAN) {
			handler.removeObject(this);
			System.out.println("removing" + this);
			return false;
		}
		return true;
	}
	
	private void checkCollisions() {
		LinkedList<GameObject> tmpObjects = handler.getObjects();
		for (int i = 0; i < tmpObjects.size(); i++) {
			GameObject tempObject = tmpObjects.get(i);
			
			if (tempObject.getId() == ID.Block) { // if bullet touches wall => removed
				if (! (this instanceof  Satellite) && (this instanceof  Laser)) {
					handler.removeObject(this);
				}
			}
			
			if (tempObject.getId() == ID.Monster) { // if bullet touches wall => removed
				if (this.getShape().getBounds2D().intersects(tempObject.getShape().getBounds2D())) {
					if (! (this instanceof  Explosion) && (this instanceof  Laser)) {
						handler.removeObject(this);
					}
					((Monster)tempObject).kill();
				}
			}
		}
	}
}
