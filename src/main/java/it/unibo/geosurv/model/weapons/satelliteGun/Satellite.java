package it.unibo.geosurv.model.weapons.satelliteGun;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.weapons.Bullet;

public class Satellite extends Bullet {	
	
	private Handler handler;

//	public Satellite(float x, float y, ID id, Handler handler, SpriteSheet ss) {
	public Satellite(float x, float y, Handler handler) {
		super(x, y, ID.Bullet, handler);
	}

	@Override
	public void tick() {
//		x += velX;
//		y += velY;
//		LinkedList<GameObject> tmpObjects = handler.getObjects();
//		
//		for (int i = 0; i < tmpObjects.size(); i++) {
//			GameObject tempObject = tmpObjects.get(i);
//			
//			if (tempObject.getId() == ID.Block && id == ID.Satellite) {
//				if (getShape().intersects(tempObject.getShape())) {
//					System.out.println(this.id);
//					handler.removeObject(this);
//				}
//			}
//		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int) x, (int) y, 8, 8);
	}

	@Override
	public Rectangle getShape() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}

}