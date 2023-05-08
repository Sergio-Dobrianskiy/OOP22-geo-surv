package it.unibo.geosurv.model.weapons.satelliteGun;

import java.util.ArrayList;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.model.weapons.Weapon;

public class SatelliteGun extends Weapon {
	
	private final float ORBIT_SPEED = 0.05f;
	private final float ORBIT_RADIUS = 150f;
	private final double RADIANS_IN_CIRCLE = 6.28319d; // 360° = radians 6.28319
	
	private  double angleDifference;
	private Handler handler;
//	private Camera cam;
//	private SpriteSheet ss;
	private GameObject tempPlayer;
	private double angle = 0d;
	private GameObject bullet;
	ArrayList<GameObject> bullets;
	private int numberOfBullets = 0;
	private int counter;
	

//	public SatelliteGun(int x, int y, Handler handler, SpriteSheet ss, Game game, Camera cam) {
	public SatelliteGun(Handler handler) {
		super();
		this.handler = handler;
//		this.cam = cam;
//		this.ss = ss;
		this.levelUp();
		tempPlayer = handler.getPlayer();
		this.bullets = new ArrayList<>();
		this.addSatellite();
		this.addSatellite();							// test
		this.addSatellite();
		this.addSatellite();
		this.addSatellite();
		this.addSatellite();
		this.addSatellite();
		this.addSatellite();
		this.addSatellite();
		this.addSatellite();
		this.addSatellite();
		this.addSatellite();
	}
	
	/**
	 * Adds a rotating satellite
	 */	
	public void addSatellite() {
		this.bullet = handler.addObject(new Satellite(this.getXPos(angle) + MainPlayer.HALF_PLAYER_WIDTH, this.getYPos(angle) + MainPlayer.HALF_PLAYER_HEIGHT, handler));
		this.bullets.add(this.bullet);
		this.numberOfBullets++;
	}
	
	@Override
	public void tick() {
		this.angle += this.ORBIT_SPEED;		
		this.angleDifference = RADIANS_IN_CIRCLE / this.numberOfBullets;
		this.counter = 0; 							// TODO: find a better method
		
		this.bullets.forEach( b -> {
			double angle2 = this.angle + (angleDifference * this.counter);
			b.setX(this.getXPos(angle2));
			b.setY(this.getYPos(angle2));
			this.counter++;
		});	
	}
	
	/**
	 * calculates x coordinate of a satellite, Math.sin and cos accept radians as input
	 * 
	 * @return float y coordinate of the satellite
	 */	
	public float getXPos(double angle) {
		return (float) (13d + this.tempPlayer.getX() + (Math.cos(angle) * this.ORBIT_RADIUS)); // TODO: magic number
	}
	
	/**
	 * calculates y coordinate of a satellite, Math.sin and cos accept radians as input
	 * 
	 * @return float y coordinate of the satellite
	 */	
	public float getYPos(double angle) {
		return (float) (20d + this.tempPlayer.getY() + (Math.sin(angle) * this.ORBIT_RADIUS)); // TODO: magic number
	}
}
