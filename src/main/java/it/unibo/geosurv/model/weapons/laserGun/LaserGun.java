package it.unibo.geosurv.model.weapons.laserGun;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.model.weapons.Weapon;


public class LaserGun extends Weapon {
	
	protected final static int LASER_HEIGHT = 10;
	protected final static int LASER_WIDTH = 300;
	
	private Handler handler;
	private GameObject player;


	public LaserGun(Handler handler) {
		super();
		this.handler = handler; 
		this.cicle = 3000L;
		player = handler.getPlayer();
	}
	
	protected void shoot() {
		float px = this.player.getX();
		float py = this.player.getY();
		float yCorrection = MainPlayer.HALF_PLAYER_HEIGHT;
		float xCorrection = (MainPlayer.PLAYER_WIDTH - LASER_HEIGHT) / 2;
		
		this.handler.addObject(new Laser(px + MainPlayer.PLAYER_WIDTH, py + yCorrection, this.handler, LASER_WIDTH, LASER_HEIGHT)); // right
		this.handler.addObject(new Laser(px - LASER_WIDTH, py + yCorrection, this.handler, LASER_WIDTH, LASER_HEIGHT)); // left
		this.handler.addObject(new Laser(px + xCorrection, py - LASER_WIDTH, this.handler, LASER_HEIGHT, LASER_WIDTH)); // up
		this.handler.addObject(new Laser(px + xCorrection, py + MainPlayer.PLAYER_HEIGHT, this.handler, LASER_HEIGHT, LASER_WIDTH)); // down
	}
}
