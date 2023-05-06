package it.unibo.geosurv.model.weapons.ExplosionGun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.weapons.Bullet;

public class Explosion extends Bullet {
	
	private final static long LIFE_SPAN = 2000L; // max milliseconds of life
	
	private long creationTime;

	public Explosion(float x, float y, Handler handler) {
		super(x, y, ID.Bullet, handler);
		this.creationTime = System.currentTimeMillis();
		
	}
	
	@Override
	public void tick() {
		super.tick();
		long currentTime = System.currentTimeMillis();
		if ((currentTime - this.creationTime) > LIFE_SPAN) {
			handler.removeObject(this);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int) x, (int) y, 32, 32);
	}

	@Override
	public Ellipse2D getShape() {
		return new Ellipse2D.Float(x, y, 32f, 32f);
	}
}
