package it.unibo.geosurv.model.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

public abstract class Weapon extends GameObject {

	public Weapon(float x, float y) {
		super(x, y, ID.Weapon);
	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public RectangularShape getShape() {
		
		return null;
	}

}
