package it.unibo.geosurv.model.weapons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import it.unibo.geosurv.model.Game;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.utility.Func;

public class SatelliteGun extends Weapon {
	
	private Handler handler;
	private Game game;
//	private Camera cam;
//	private SpriteSheet ss;
	
	private int orbitGunLvl;
	private float rotationSpeed;
	private GameObject tempPlayer;
	
	private double angle = 0d;
	private final float orbitSpeed = 0.05f;
	private final float orbitRadius = 150;
	
	private float centerX;
	private float centerY;
	private GameObject bullet;
	ArrayList<GameObject> bullets;
	private int numberOfBullets = 0;
	private int counter;
	

//	public SatelliteGun(int x, int y, Handler handler, SpriteSheet ss, Game game, Camera cam) {
	public SatelliteGun(float x, float y, Handler handler, Game game) {
		super(x, y, ID.Satellite);
		this.handler = handler;
		this.game = game;
//		this.cam = cam;
//		this.ss = ss;
		this.orbitGunLvl = 1;						// test value
		tempPlayer = Func.findPlayer(handler);
		this.bullets = new ArrayList<>();
		this.addBullet();
		this.addBullet();							// test
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
		this.addBullet();
	}
	
		
	public void addBullet() {
//		this.bullet = handler.addObject(new Bullet(this.getXPos(angle), this.getYPos(angle), handler, ss));
		this.bullet = handler.addObject(new Satellite(this.getXPos(angle), this.getYPos(angle), handler));
		this.bullets.add(this.bullet);
		this.numberOfBullets++;
	}
	
	@Override
	public void tick() {
		this.angle += this.orbitSpeed;
		
		double angleDifference = 6.28319 / this.numberOfBullets; // 360° = radians 6.28319
		this.counter = 0; 							// TODO: find a better method
		
		this.bullets.forEach( b -> {
			double angle2 = this.angle + (angleDifference * this.counter);
			b.setX(this.getXPos(angle2));
			b.setY(this.getYPos(angle2));
			this.counter++;
		});	
	}
	
	// Math.sin and cos accept radians as input
	public float getXPos(double angle) {
		return (float) (16d + this.tempPlayer.getX() + (Math.cos(angle) * this.orbitRadius));
	}
	
	public float getYPos(double angle) {
		return (float) (16d + this.tempPlayer.getY() + (Math.sin(angle) * this.orbitRadius));
	}
}
