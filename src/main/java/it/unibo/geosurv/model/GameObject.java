package it.unibo.geosurv.model;

import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.view.graphics.Texture;

public abstract class GameObject implements TickingObject, IGameObject {
	protected float x, y;									// position
	protected float velX = 0, velY = 0;						// velocity
	protected int height = 0, width = 0;					// dimensions
	protected Texture texture = Texture.MISSING_TEXTURE;	// texture
	protected ID id;										// ID
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	@Override
	public ID getId() {
		return id;
	}

	@Override
	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public abstract void tick();
	

	@Override
	public float getX() {
		return x;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public float getVelX() {
		return velX;
	}

	@Override
	public void setVelX(float velX) {
		this.velX = velX;
	}

	@Override
	public float getVelY() {
		return velY;
	}

	@Override
	public void setVelY(float velY) {
		this.velY = velY;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
	
	@Override
	public int getWidth() {
		return this.width;
	}
	
	@Override
	public void setHeight(final int height) {
		this.height = height;
	}

	@Override
	public void setWidth(final int width) {
		this.width = width;
	}
	
	@Override
	public int getRenderX() {
		return (int) (x - (this.width / 2));
	}
	
	@Override
	public int getRenderY() {
		return (int) (y - (this.height / 2));
	}
	
	@Override
	public Texture getTexture() {
		return this.texture;
	}
	
	/**
	 * return GameObject's hitbox (that is a Rectabgle by default)
	 * 
	 * @return RectangularShape centered on the GameObject
	 */
	@Override
	public RectangularShape getShape() {
		return new Rectangle(this.getRenderX(), getRenderY(), this.width, this.height);
	}
}
