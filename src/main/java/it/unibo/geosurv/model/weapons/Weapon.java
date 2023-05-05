package it.unibo.geosurv.model.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.ID;

public abstract class Weapon extends GameObject {

	public Weapon(float x, float y, ID id) {
		super(x, y, id);
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
