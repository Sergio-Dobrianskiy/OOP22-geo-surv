package it.unibo.geosurv.model.weapons.laserGun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.weapons.Bullet;

public class Laser extends Bullet {
	
	private int height;
	private int width;

	public Laser(float x, float y, Handler handler, final int width, final int height) {
		super(x, y, ID.Bullet, handler);
		super.LIFE_SPAN = 2000L;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void render(Graphics g) {
		System.out.println("laserRender" + x + " " + y);
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, this.width, this.height);
	}

	@Override
	public Rectangle getShape() {
		return new Rectangle((int)x, (int)y, this.width, this.height);
	}

}
