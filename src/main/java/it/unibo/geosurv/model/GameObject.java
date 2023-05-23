package it.unibo.geosurv.model;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import it.unibo.geosurv.control.TickingObject;
import it.unibo.geosurv.view.graphics.Texture;

public abstract class GameObject implements TickingObject {
	protected float x, y;									// position
	protected float velX, velY;								// velocity
	protected int height = 0, width = 0;					// dimensions
	protected Texture texture = Texture.MISSING_TEXTURE;	// texture
	protected ID id;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public abstract void tick();
	
	public RectangularShape getShape() {
        return this.setRectangleShape();
    }
	

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(final int height) {
		this.height = height;
	}

	public void setWidth(final int width) {
		this.width = width;
	}
	
	public int getRenderX() {
		return (int) (x - (this.width / 2));
	}
	
	public int getRenderY() {
		return (int) (y - (this.height / 2));
	}

	protected Rectangle setRectangleShape() {
		return new Rectangle(this.getRenderX(), getRenderY(), width, height);
	}
	
	protected Ellipse2D setOvalShape() {
		return new Ellipse2D.Float(getRenderX(), getRenderY(), width, height);
	}
	
	public Texture getTexture() {
		return this.texture;
	}
	
}
