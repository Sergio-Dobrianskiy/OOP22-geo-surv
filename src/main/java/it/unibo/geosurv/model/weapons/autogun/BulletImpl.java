package it.unibo.geosurv.model.weapons.autogun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.weapons.Bullet;

public class BulletImpl extends Bullet {
	
	protected final static int BULLET_HEIGHT = 8;
	protected final static int BULLET_WIDTH = 8;
	
	public BulletImpl(float x, float y, Handler handler) {
		super(x, y, handler);
		this.height = BULLET_HEIGHT;
		this.width = BULLET_WIDTH;
	}
	
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, width, height);
	}

	@Override
	public Rectangle getShape() {
		return new Rectangle((int)x, (int)y, width, height);
	}

}