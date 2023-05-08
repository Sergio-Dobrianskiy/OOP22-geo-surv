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
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, BULLET_WIDTH, BULLET_HEIGHT);
	}

	@Override
	public Rectangle getShape() {
		return new Rectangle((int) x, (int) y, BULLET_WIDTH, BULLET_HEIGHT);
	}
	
	/**
	 * Satellite has infinite life span
	 */
	@Override
	protected boolean stillAlive() {
		return true;
	}

}
