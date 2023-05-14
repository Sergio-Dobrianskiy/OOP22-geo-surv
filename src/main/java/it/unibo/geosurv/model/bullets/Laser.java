package it.unibo.geosurv.model.bullets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.view.graphics.Sprite;
import it.unibo.geosurv.view.graphics.Texture;

public class Laser extends Bullet {
	
	protected final long LIFE_SPAN = 2000L;
	
	private GameObject player;
	private int initialX;
	private int initialY;
	private BufferedImage sprite;

	public Laser(float x, float y, Handler handler, final int width, final int height, BufferedImage bi) {
		super(x, y, handler);
		this.lifeSpan = LIFE_SPAN;
		this.width = width;
		this.height = height;
		this.player = handler.getPlayer();
		this.initialX = (int) player.getX();
		this.initialY = (int) player.getY();
//		this.sprite = sprite.grabImage(5, 2, 32, 32);
		this.sprite = bi;
	}
	
	/**
     * calculate x coordinate of the laser taking into account the player movements
     * 
     * @param float x coordinate at the time of the laser creation
     * 
     * @return int x coordinate at the time of render/collision
     */
	private int currentX(float n) {
		int px = (int) player.getX();
		return (int) n - initialX + px;
	}
	
	/**
     * calculate y coordinate of the laser taking into account the player movements
     * 
     * @param float y coordinate at the time of the laser creation
     * 
     * @return int y coordinate at the time of render/collision
     */
	private int currentY(float n) {
		int py = (int) player.getY();
		return (int) n - initialY + py;
	}
	
	
	@Override
	public void render(Graphics g) {
//		g.setColor(Color.green);
//		g.fillRect(this.currentX(x) - (this.width / 2), this.currentY(y) - (this.height / 2), this.width, this.height);
		int xx = this.currentX(x) - (this.width / 2);
		int yy = this.currentY(y) - (this.height / 2);
		g.drawImage(sprite, xx, yy, null);
	}
	

	@Override
	public Rectangle getShape() {
		return new Rectangle(this.currentX(x) - (this.width / 2), this.currentY(y) - (this.height / 2), this.width, this.height);
	}

}
