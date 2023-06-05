package it.unibo.geosurv.control.weapons;

import java.util.ArrayList;
import java.util.List;

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
	private static final float orbitSpeed = 0.05f;
    /**
     * Satellites orbit radius.
     */
	private static final float orbitRadius = 150f;
    /**
     * Radians in a circle.
     */
	private static final double radiansInCircle = 6.28319d; // 360° = radians 6.28319
    /**
     * Default Satellites damage.
     */
	private static final int damage = 3;
    /**
     * Default number of Satellites per weapon level.
     */
	private static final int satellitesPerLevel = 3;
	
    private  double angleDifference;
    private Handler handler;
    private GameObject tempPlayer;
    private double angle = 0d;
	private List<GameObject> satellites;
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
	}
	
	/**
	 * Adds a rotating satellite.
	 */	
	public void addSatellite() {
		GameObject satellite = handler.addObject(new Satellite(this.getXPos(angle), this.getYPos(angle), handler, damage));
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
		return (float) (this.tempPlayer.getX() + (Math.cos(angle) * orbitRadius));
	}
	
	/**
	 * calculates y coordinate of a satellite, Math.sin and cos accept radians as input.
	 * 
	 * @param angle y angle
	 * 
	 * @return float y coordinate of the satellite
	 */	
	public float getYPos(final double angle) {
		return (float) (this.tempPlayer.getY() + (Math.sin(angle) * orbitRadius));
	}

	/**
	 * manages each satellite rotation.
	 */
	@Override
	protected void shoot() {
		this.angle += orbitSpeed;
		this.angleDifference = radiansInCircle / satellites.size();
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
			while (satellites.size() < (currentLevel * satellitesPerLevel)) {
				// System.out.println("size " + satellites.size());
				this.addSatellite();
			}
		return true;
		}
	return false;
	}
}
