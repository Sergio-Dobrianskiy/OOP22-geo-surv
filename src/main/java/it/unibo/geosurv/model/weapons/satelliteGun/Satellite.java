package it.unibo.geosurv.model.weapons.satelliteGun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.weapons.Bullet;

public class Satellite extends Bullet {	
	
	protected final static int BULLET_HEIGHT = 8;
	protected final static int BULLET_WIDTH = 8;

	public Satellite(float x, float y, Handler handler) {
		super(x, y, handler);
		this.height = BULLET_HEIGHT;
		this.width = BULLET_WIDTH;
	}

	@Override
	public void render(Graphics g) {
		this.drawRect(g,  Color.cyan);
	}
	
	/**
	 * Satellite has infinite life span
	 */
	@Override
	protected boolean stillAlive() {
		return true;
	}

}
