package it.unibo.geosurv.model.weapons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

public class BulletImpl extends Bullet {
	

	public BulletImpl(float x, float y, Handler handler) {
		super(x, y, ID.Bullet, handler);
		
	}
	
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, 8, 8);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}

}
