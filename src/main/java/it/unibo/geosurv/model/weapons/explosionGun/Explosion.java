package it.unibo.geosurv.model.weapons.explosionGun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.weapons.Bullet;

public class Explosion extends Bullet {
	
	protected final static int BULLET_HEIGHT = 32;
	protected final static int BULLET_WIDTH = 32;

	public Explosion(float x, float y, Handler handler) {
		super(x, y, handler);
		super.lifeSpan = 2000L;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int) x, (int) y, BULLET_WIDTH, BULLET_HEIGHT);
	}

	@Override
	public Ellipse2D getShape() {
		return new Ellipse2D.Float(x, y, BULLET_WIDTH, BULLET_HEIGHT);
	}
}

