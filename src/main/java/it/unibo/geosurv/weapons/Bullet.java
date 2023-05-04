package it.unibo.geosurv.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.geosurv.core.GameObject;
import it.unibo.geosurv.core.Handler;
import it.unibo.geosurv.core.ID;

public abstract class Bullet extends GameObject {

	public Bullet(float x, float y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public Rectangle getBounds() {

		return null;
	}
}
