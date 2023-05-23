package it.unibo.geosurv.model.bullets;

import java.awt.geom.Ellipse2D;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.view.graphics.Texture;

public class Explosion extends Bullet {
	
	protected final int BULLET_HEIGHT = 64;
	protected final int BULLET_WIDTH = 64;
	protected final long EXPLOSION_LIFE_SPAN = 2000L;

	public Explosion(float x, float y, final Handler handler, final int damage) {
		super(x, y, handler, damage);
		super.lifeSpan = EXPLOSION_LIFE_SPAN;
		this.height = BULLET_HEIGHT;
		this.width = BULLET_WIDTH;
		this.texture = Texture.EXPLOSION;
	}
	
	/**
	 * an explosion has a circular shape
	 * 
	 * @return Ellipse2D centered on the GameObject
	 */
	@Override
	public Ellipse2D getShape() {
		return new Ellipse2D.Float(getRenderX(), getRenderY(), this.width, this.height);
	}
}

