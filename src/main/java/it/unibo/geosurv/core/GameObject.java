package it.unibo.geosurv.core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public abstract class GameObject {
	protected float x, y;
	protected float velX,velY;
	protected ID id;
	protected MainPlayer player;
	protected float distanceFromPlayer;
	protected Handler handler;
	
	public GameObject(float x, float y, ID id, Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public abstract void tick();
	public abstract void render(Graphics g); 
	public abstract Rectangle getBounds();

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
	
	public void updateDistanceFromPlayer() {
		// Point2D.distance(x1, y1, x2, y2);
		this.distanceFromPlayer = (float) Point2D.distance(x, y, player.getX(), player.getY());
	}
	
	public float getDistanceFromPlayer() {
		return this.distanceFromPlayer;
	}
	
	public boolean load() {
		this.player = handler.getPlayer();
		if (this.player == null) {
			return false;
		}
		return true;
	}
	
}
