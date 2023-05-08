package it.unibo.geosurv.model.weapons;

import java.util.LinkedList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.weapons.autogun.BulletImpl;

public abstract class Bullet extends GameObject {
	
	protected int damage = 1;
	protected int bulletHeight;
	protected int bulletWidth;
	protected long lifeSpan = 5000L; // max milliseconds of life
	
	protected long creationTime;
	protected final Handler handler;
	private NotificationService notificationService;

	public Bullet(float x, float y, Handler handler) {
		super(x, y, ID.Bullet);
		this.handler = handler;
		this.creationTime = System.currentTimeMillis();
		this.notificationService = new NotificationService();
	}
	
	public void sendNotification() {
		notificationService.notify();
	}
	
	public NotificationService getService() {
		return notificationService;
	}

	@Override
	public void tick() {
		updatePosition(this.velX, this.velY);
		if (this.stillAlive()) {
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
		LinkedList<GameObject> tmpObjects = handler.getObjects();
		for (int i = 0; i < tmpObjects.size(); i++) {
			GameObject tempObject = tmpObjects.get(i);
			
			if (tempObject.getId() == ID.Block) { // if bullet touches wall => removed
				if (this.getShape().getBounds2D().intersects(tempObject.getShape().getBounds2D())) {
					if (this instanceof BulletImpl) {
						handler.removeObject(this);
					}					
				}
			}
			
			if (tempObject.getId() == ID.Monster) { // if bullet touches wall => removed
				if (this.getShape().getBounds2D().intersects(tempObject.getShape().getBounds2D())) {
					if (this instanceof BulletImpl) {
						handler.removeObject(this);
					}
					((Monster)tempObject).kill();
				}
			}
		}
	}
}
