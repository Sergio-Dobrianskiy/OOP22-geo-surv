package it.unibo.geosurv.model.bullets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import it.unibo.geosurv.model.Handler;

public class Satellite extends Bullet {	
	
	protected final static int BULLET_HEIGHT = 8;
	protected final static int BULLET_WIDTH = 8;

	public Satellite(float x, float y, Handler handler) {
		super(x, y, handler);
		this.height = BULLET_HEIGHT;
		this.width = BULLET_WIDTH;
	}
	
	/**
	 * Satellite has infinite life span
	 */
	@Override
	protected boolean stillAlive() {
		return true;
	}

}
