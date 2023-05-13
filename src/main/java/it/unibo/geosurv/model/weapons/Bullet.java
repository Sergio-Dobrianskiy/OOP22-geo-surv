package it.unibo.geosurv.model.weapons;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
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

			if (this instanceof BulletImpl && isColliding(tempObject, ID.Block)) {
				handler.removeObject(this);
				
			} else if (isColliding(tempObject, ID.Monster)) {
				((Monster) tempObject).hit(this.damage); // TODO: verify the cast
				if (this instanceof BulletImpl) {
					handler.removeObject(this);
				}
			}
		}
	}
	
	/**
     * Check if a bullet is colliding with a GameObject
     * 
     * @param GameOnject to check
     * @param ID that the touched object should have
     */
	private boolean isColliding(final GameObject obj, final ID id) {
		return obj.getId() == id && this.getShape().getBounds2D().intersects(obj.getShape().getBounds2D());
	}
	
	public RectangularShape getShape() {
        return this.setRectangleShape();
    }
	
	
}
