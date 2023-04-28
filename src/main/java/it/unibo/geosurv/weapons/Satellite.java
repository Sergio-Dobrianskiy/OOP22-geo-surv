package it.unibo.geosurv.weapons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import it.unibo.geosurv.core.Game;
import it.unibo.geosurv.core.GameObject;
import it.unibo.geosurv.core.Handler;
import it.unibo.geosurv.core.ID;
import it.unibo.geosurv.utility.Func;

public class Satellite extends Bullet {
	
	private Handler handler;
	private Game game;
//	private Camera cam;
//	private SpriteSheet ss;
	
	private int orbitGunLvl;
	private float rotationSpeed;
	private GameObject tempPlayer;
	
	private double angle;
	private float speed;
	private float radius;
	
	private float centerX;
	private float centerY;
	private GameObject bullet;
	ArrayList<GameObject> bullets;
	private int numberOfBullets;
	private int counter;

//	public Satellite(int x, int y, Handler handler, SpriteSheet ss, Game game, Camera cam) {
	public Satellite(int x, int y, Handler handler, Game game) {
		super(x, y, ID.Satellite);
		this.handler = handler;
		this.game = game;
//		this.cam = cam;
//		this.ss = ss;
		this.orbitGunLvl = 1;
		tempPlayer = Func.findPlayer(handler);
		this.angle = 0d;
		this.radius = 150;
		this.speed = 0.015f;
		this.bullets = new ArrayList<>();
		this.numberOfBullets = 0;
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
		this.addBullet();
		this.addBullet();
	}
	
		
	public void addBullet() {
//		this.bullet = handler.addObject(new Bullet(this.getXPos(angle), this.getYPos(angle), handler, ss));
		this.bullets.add(this.bullet);
		this.numberOfBullets++;
	}
	
	@Override
	public void tick() {
		this.angle += this.speed;
		
		double angleDifference = 6.28319 / this.numberOfBullets; // 360° = radians 6.28319
		this.counter = 0; // TODO: find a better method
		
		this.bullets.forEach( b -> {
			double angle2 = this.angle + (angleDifference * this.counter);
			b.setX(this.getXPos(angle2));
			b.setY(this.getYPos(angle2));
			this.counter++;
		});	
	}
	
	// Math.sin e cos funzionano in radianti
	public float getXPos(double angle) {
		return (float) (16 + this.tempPlayer.getX() + (Math.cos(angle) * this.radius));
	}
	
	public float getYPos(double angle) {
		return (float) (16 + this.tempPlayer.getY() + (Math.sin(angle) * this.radius));
	}
	
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, 8, 8);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 8, 8);
	}

}
