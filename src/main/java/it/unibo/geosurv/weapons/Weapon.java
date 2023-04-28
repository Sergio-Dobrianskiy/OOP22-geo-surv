package it.unibo.geosurv.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;

import it.unibo.geosurv.core.GameObject;
import it.unibo.geosurv.core.ID;

public abstract class Weapon extends GameObject {

	public Weapon(int x, int y, ID id) {
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
