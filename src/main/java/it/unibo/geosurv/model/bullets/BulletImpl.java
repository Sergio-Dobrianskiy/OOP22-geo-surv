package it.unibo.geosurv.model.bullets;

import it.unibo.geosurv.model.Handler;

public class BulletImpl extends Bullet {
	
	protected final static int BULLET_HEIGHT = 8;
	protected final static int BULLET_WIDTH = 8;
	
	public BulletImpl(float x, float y, Handler handler) {
		super(x, y, handler);
		this.height = BULLET_HEIGHT;
		this.width = BULLET_WIDTH;
	}
}