package it.unibo.geosurv.model;

import java.util.ArrayList;

import it.unibo.geosurv.model.bullets.Bullet;
import it.unibo.geosurv.model.bullets.BulletImpl;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.player.MainPlayer;

public class Collisions {
	
	private Handler handler;
	
	public Collisions(Handler handler) {
		this.handler = handler;
	}
	
	public void checkPlayerCollisions() {
		ArrayList<GameObject> tmpObjects = handler.getObjects();
		MainPlayer player = handler.getPlayer();
		for (int i = 0; i < tmpObjects.size(); i++) {
	    		GameObject tempObject = tmpObjects.get(i);
			if (Collisions.isColliding(player, tempObject, ID.Block)) { // if player touches wall => stop
				player.stopMovements();
			}
			if (Collisions.isColliding(player, tempObject, ID.Monster)) { // if player touches Monsters
				player.hit(((Monster) tempObject).getPower()); // TODO: verify the cast => maybe if we call a
					                // function here that works at Monster we do not
					                // need the cast
					                // (Monster.over(handler.player)->decrease life)
	        }
	    }
	}
	
	public void checkBulletCollisionss(final Bullet bullet) {
		ArrayList<GameObject> tmpObjects = handler.getObjects();
		for (int i = 0; i < tmpObjects.size(); i++) {
			GameObject tempObject = tmpObjects.get(i);

			if (bullet instanceof BulletImpl && Collisions.isColliding(bullet, tempObject, ID.Block)) {
				handler.removeObject(bullet);
				
			} else if (Collisions.isColliding(bullet,tempObject, ID.Monster)) {
				((Monster) tempObject).hit(bullet.getDamage());
				if (bullet instanceof BulletImpl) {
					handler.removeObject(bullet);
				}
			}
		}
	}
	
	/**
	 * Check if the first GameObject is colliding with another GameObject
	 * with a specific ID
	 * 
	 * @param 1st GameOnject to check
	 * @param 2nd GameOnject to check
	 * @param ID that the touched object should have
	 */
	public static boolean isColliding(final GameObject obj1, final GameObject obj2, final ID id) {
		return obj2.getId() == id && obj1.getShape().getBounds2D().intersects(obj2.getShape().getBounds2D());
	}
	
	public static boolean isColliding(final GameObject obj1, final GameObject obj2) {
		return obj1.getShape().getBounds2D().intersects(obj2.getShape().getBounds2D());
	 }
}
