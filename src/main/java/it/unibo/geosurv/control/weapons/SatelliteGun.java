package it.unibo.geosurv.control.weapons;

import java.util.ArrayList;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.bullets.Bullet;
import it.unibo.geosurv.model.bullets.Satellite;

public class SatelliteGun extends Weapon {
	
	private final float ORBIT_SPEED = 0.05f;
	private final float ORBIT_RADIUS = 150f;
	private final double RADIANS_IN_CIRCLE = 6.28319d; // 360° = radians 6.28319
//	private final int DAMAGE_LVL_1 = 1;
//	private final int DAMAGE_LVL_2 = 2;
//	private final int DAMAGE_LVL_3 = 3;
	private final int DAMAGE = 3;
	private final int SATELITES_PER_LEVEL= 3;
	
	private  double angleDifference;
	private Handler handler;
	private GameObject tempPlayer;
	private double angle = 0d;
	ArrayList<GameObject> satellites;
	private int counter;
	

	public SatelliteGun(Handler handler) {
		super();
		this.handler = handler;
		this.tempPlayer = handler.getPlayer();
		this.satellites = new ArrayList<>();
		this.addSatellite();
	}
	
	/**
	 * Adds a rotating satellite
	 */	
	public void addSatellite() {
		GameObject bullet = handler.addObject(new Satellite(this.getXPos(angle), this.getYPos(angle), handler, DAMAGE));
		this.satellites.add(bullet);
	}
	
	@Override
	public void tick() {
		this.angle += this.ORBIT_SPEED;		
		this.angleDifference = RADIANS_IN_CIRCLE / satellites.size();
		this.counter = 0; 							// TODO: find a better method
		
		this.satellites.forEach( b -> {
			double actualAngle = this.angle + (angleDifference * this.counter);
			b.setX(this.getXPos(actualAngle));
			b.setY(this.getYPos(actualAngle));
			this.counter++;
		});	
	}
	
	/**
	 * calculates x coordinate of a satellite, Math.sin and cos accept radians as input
	 * 
	 * @return float y coordinate of the satellite
	 */	
	public float getXPos(double angle) {
		return (float) (this.tempPlayer.getX() + (Math.cos(angle) * this.ORBIT_RADIUS));
	}
	
	/**
	 * calculates y coordinate of a satellite, Math.sin and cos accept radians as input
	 * 
	 * @return float y coordinate of the satellite
	 */	
	public float getYPos(double angle) {
		return (float) (this.tempPlayer.getY() + (Math.sin(angle) * this.ORBIT_RADIUS));
	}

	@Override
	protected void shoot() {
	}
	
	/**
     * raises weapon level by one and creates satellites
     * 
     * @return boolean true if level was raised, false if it was already at maximum level
     */
	@Override
	protected boolean levelUp() {
		if (currentLevel < MAX_LVL) {
			currentLevel += 1;
			while (satellites.size() <= (currentLevel * SATELITES_PER_LEVEL)) {
				System.out.println("size " + satellites.size());
				this.addSatellite();
			}
			return true;
		}
		return false;
	}
}
