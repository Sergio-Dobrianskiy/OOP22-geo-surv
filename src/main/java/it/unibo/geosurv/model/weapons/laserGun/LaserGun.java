package it.unibo.geosurv.model.weapons.laserGun;

import java.util.jar.Manifest;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.model.weapons.Weapon;


public class LaserGun extends Weapon {
	
	protected final int ShortSide = 10;
	protected final int LongSide = 300;
	protected final long LASER_CYCLE = 3000L;
	
	private Handler handler;
	private GameObject player;


	public LaserGun(Handler handler) {
		super();
		this.handler = handler; 
		this.cicle = LASER_CYCLE;
		player = handler.getPlayer();
	}
	
	protected void shoot() {
		float px = this.player.getX();
		float py = this.player.getY();
		float xCorrection = LongSide / 2 + MainPlayer.HALF_PLAYER_WIDTH;
		float yCorrection = LongSide / 2 + MainPlayer.HALF_PLAYER_HEIGHT;;
		
		this.handler.addObject(new Laser(px + xCorrection, py, this.handler, LongSide, ShortSide)); // right
		this.handler.addObject(new Laser(px - xCorrection, py, this.handler, LongSide, ShortSide)); // left
		this.handler.addObject(new Laser(px, py - yCorrection, this.handler, ShortSide, LongSide)); // up
		this.handler.addObject(new Laser(px, py + yCorrection, this.handler, ShortSide, LongSide)); // down
	}
}
