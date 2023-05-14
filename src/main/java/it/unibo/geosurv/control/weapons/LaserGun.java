package it.unibo.geosurv.control.weapons;

import java.awt.image.BufferedImage;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.bullets.Laser;
import it.unibo.geosurv.model.player.MainPlayer;
import it.unibo.geosurv.view.graphics.Texture;


public class LaserGun extends Weapon {
	
	protected final int ShortSide = 32;
	protected final int LongSide = 286;
	protected final long LASER_CYCLE = 3000L;
	
	private Handler handler;
	private GameObject player;
	BufferedImage laserH, laserV;
	


	public LaserGun(Handler handler) {
		super();
		this.handler = handler; 
		this.cycle = LASER_CYCLE;
		player = handler.getPlayer();
		laserH = Texture.LASER_H.getTexture();
		laserV = Texture.LASER_V.getTexture();
	}
	
	protected void shoot() {
		float px = this.player.getX();
		float py = this.player.getY();
		float xCorrection = LongSide / 2 + MainPlayer.HALF_PLAYER_WIDTH;
		float yCorrection = LongSide / 2 + MainPlayer.HALF_PLAYER_HEIGHT;;
		
		this.handler.addObject(new Laser(px + xCorrection, py, this.handler, LongSide, ShortSide, laserH)); // right
		this.handler.addObject(new Laser(px - xCorrection, py, this.handler, LongSide, ShortSide, laserH)); // left
		this.handler.addObject(new Laser(px, py - yCorrection, this.handler, ShortSide, LongSide, laserV)); // up
		this.handler.addObject(new Laser(px, py + yCorrection, this.handler, ShortSide, LongSide, laserV)); // down
	}
}
