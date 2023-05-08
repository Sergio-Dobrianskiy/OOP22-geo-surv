package it.unibo.geosurv.model.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.weapons.ExplosionGun.Explosion;

public abstract class Bullet extends GameObject {

	protected final Handler handler;
	private int BULLET_DAMAGE = 1;

	public Bullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		LinkedList<GameObject> tmpObjects = handler.getObjects();
		for (int i = 0; i < tmpObjects.size(); i++) {
			GameObject tempObject = tmpObjects.get(i);

			if (tempObject.getId() == ID.Block) { // if bullet touches wall => removed
				if (this.getShape().getBounds2D().intersects(tempObject.getShape().getBounds2D())) {
					handler.removeObject(this);
				}
			}

			if (tempObject.getId() == ID.Monster) { // if bullet touches wall => removed
				if (this.getShape().getBounds2D().intersects(tempObject.getShape().getBounds2D())) {
					if (!(this instanceof Explosion)) {
						handler.removeObject(this);
					}
					((Monster) tempObject).hit(BULLET_DAMAGE);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
	}

}
