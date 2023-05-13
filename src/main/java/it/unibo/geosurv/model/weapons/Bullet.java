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

	public Bullet(float x, float y, Handler handler) {
		super(x, y, ID.Bullet);
		this.handler = handler;
		this.creationTime = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		if (this.stillAlive()) {
			updatePosition(this.velX, this.velY);
			this.checkCollisions();
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

	private void checkCollisions() {
		ArrayList<GameObject> tmpObjects = handler.getObjects();
		for (int i = 0; i < tmpObjects.size(); i++) {
			GameObject tempObject = tmpObjects.get(i);

			if (this instanceof BulletImpl && Collisions.isColliding(this, tempObject, ID.Block)) {
				handler.removeObject(this);
				
			} else if (Collisions.isColliding(this,tempObject, ID.Monster)) {
				((Monster) tempObject).hit(this.damage); // TODO: verify the cast
				if (this instanceof BulletImpl) {
					handler.removeObject(this);
				}
			}
		}
	}
	
	
	public RectangularShape getShape() {
        return this.setRectangleShape();
    }
	
	
}
