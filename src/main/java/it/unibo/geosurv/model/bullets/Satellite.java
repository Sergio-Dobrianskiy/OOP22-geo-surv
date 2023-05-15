package it.unibo.geosurv.model.bullets;

import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.view.graphics.Texture;

public class Satellite extends Bullet {	
	
	protected final static int BULLET_HEIGHT = 15;
	protected final static int BULLET_WIDTH = 15;

	public Satellite(float x, float y, Handler handler) {
		super(x, y, handler);
		this.height = BULLET_HEIGHT;
		this.width = BULLET_WIDTH;
		this.texture = Texture.SATELLITE;
	}
	
	/**
	 * Satellite has infinite life span
	 */
	@Override
	protected boolean stillAlive() {
		return true;
	}

}
