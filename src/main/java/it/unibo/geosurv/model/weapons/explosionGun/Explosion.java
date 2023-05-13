package it.unibo.geosurv.model.weapons.explosionGun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.weapons.Bullet;

public class Explosion extends Bullet {
	
	protected final int BULLET_HEIGHT = 32;
	protected final int BULLET_WIDTH = 32;
	protected final long EXPLOSION_LIFE_SPAN = 2000L;

	public Explosion(float x, float y, Handler handler) {
		super(x, y, handler);
		super.lifeSpan = EXPLOSION_LIFE_SPAN;
		this.height = BULLET_HEIGHT;
		this.width = BULLET_WIDTH;
	}
	
	@Override
	public void render(Graphics g) {
		drawOval(g, Color.red);
	}

	public Ellipse2D getShape() {
		return setOvalShape();
	}
}

