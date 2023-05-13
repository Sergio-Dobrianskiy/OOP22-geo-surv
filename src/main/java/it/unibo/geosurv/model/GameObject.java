package it.unibo.geosurv.model;

import java.awt.Graphics;
import java.awt.geom.RectangularShape;

public abstract class GameObject {
	protected float x, y;			// position
	protected float velX, velY;		// velocity
	protected float height, width;	// dimensions
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
	public abstract void render(Graphics g); 
	public abstract RectangularShape getShape();

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
	
}
