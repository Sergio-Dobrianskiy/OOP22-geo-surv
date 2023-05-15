package it.unibo.geosurv.model.bullets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

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
	
	public Ellipse2D getShape() {
		return setOvalShape();
	}
}

