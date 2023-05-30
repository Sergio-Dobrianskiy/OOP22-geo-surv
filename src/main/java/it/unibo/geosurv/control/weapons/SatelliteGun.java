package it.unibo.geosurv.control.weapons;

import java.util.ArrayList;
import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.bullets.Satellite;

/**
 * Represents a SatelliteGun weapon.
 */
public class SatelliteGun extends Weapon {
	
    /**
     * Satellites orbit speed.
     */
    private final float ORBIT_SPEED = 0.05f;
    /**
     * Satellites orbit radius.
     */
    private final float ORBIT_RADIUS = 150f;
    /**
     * Radians in a circle.
     */
    private final double RADIANS_IN_CIRCLE = 6.28319d; // 360° = radians 6.28319
    /**
     * Default Satellites damage.
     */
    private final int DAMAGE = 3;
    /**
     * Default number of Satellites per weapon level.
     */
    private final int SATELLITES_PER_LEVEL= 3;
	
    private  double angleDifference;
    private Handler handler;
    private GameObject tempPlayer;
    private double angle = 0d;
    private ArrayList<GameObject> satellites;
    private int index;
	
	/**
	 * Constructor for this class.
	 *
	 * @param handler game's Handler
	 */
	public SatelliteGun(final Handler handler) {
		super();
		this.handler = handler;
		this.tempPlayer = handler.getPlayer();
		this.satellites = new ArrayList<>();
		weaponType = WeaponType.SatelliteGun;
	}
	
	/**
	 * Adds a rotating satellite.
	 */	
	public void addSatellite() {
		GameObject satellite = handler.addObject(new Satellite(this.getXPos(angle), this.getYPos(angle), handler, DAMAGE));
		this.satellites.add(satellite);
	}
	
	/**
	 * a SatelliteGun doen't shoot but controls it's satellites.
	 */
	@Override
	public void tick() {
		shoot();
	}
	
	/**
	 * calculates x coordinate of a satellite, Math.sin and cos accept radians as input.
	 * 
	 * @param angle y angle
	 * 
	 * @return float y coordinate of the satellite
	 */	
	public float getXPos(final double angle) {
		return (float) (this.tempPlayer.getX() + (Math.cos(angle) * this.ORBIT_RADIUS));
	}
	
	/**
	 * calculates y coordinate of a satellite, Math.sin and cos accept radians as input.
	 * 
	 * @param angle y angle
	 * 
	 * @return float y coordinate of the satellite
	 */	
	public float getYPos(final double angle) {
		return (float) (this.tempPlayer.getY() + (Math.sin(angle) * this.ORBIT_RADIUS));
	}

	/**
	 * manages each satellite rotation.
	 */
	@Override
	protected void shoot() {
		this.angle += this.ORBIT_SPEED;
		this.angleDifference = RADIANS_IN_CIRCLE / satellites.size();
		this.index = 0;

		this.satellites.forEach(b -> {
			double actualAngle = this.angle + (angleDifference * this.index);
			b.setX(this.getXPos(actualAngle));
			b.setY(this.getYPos(actualAngle));
			this.index++;
		});	
	}
	
	/**
     * raises weapon level by one and creates satellites.
     * 
     * @return boolean true if level was raised, false if it was already at maximum level
     */
	@Override
	public boolean levelUp() {
		if (currentLevel < MAX_LVL) {
			currentLevel += 1;
			while (satellites.size() < (currentLevel * SATELLITES_PER_LEVEL)) {
				System.out.println("size " + satellites.size());
				this.addSatellite();
			}
		return true;
		}
	return false;
	}
}
