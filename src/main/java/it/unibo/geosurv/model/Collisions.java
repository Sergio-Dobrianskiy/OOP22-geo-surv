package it.unibo.geosurv.model;

import java.util.ArrayList;
import it.unibo.geosurv.model.bullets.Bullet;
import it.unibo.geosurv.model.bullets.BulletImpl;
import it.unibo.geosurv.model.drops.Experience;
import it.unibo.geosurv.model.drops.Life;
import it.unibo.geosurv.model.monsters.Monster;
import it.unibo.geosurv.model.player.MainPlayer;

public class Collisions {

	private Handler handler;

	public Collisions(final Handler handler) {
		this.handler = handler;
	}

	public void checkPlayerCollisions() {
		ArrayList<GameObject> tmpObjects = handler.getGameObjects();
		MainPlayer player = handler.getPlayer();
		for (int i = 0; i < tmpObjects.size(); i++) {
			GameObject tempObject = tmpObjects.get(i);
			if (Collisions.isColliding(player, tempObject, ID.Block)) { // if player touches wall => stop
				this.stopMovements();
			}
			if (Collisions.isColliding(player, tempObject, ID.Monster)) { // if player touches Monsters
				player.hit(((Monster) tempObject).getPower()); // TODO: verify the cast => maybe if we call a
				// function here that works at Monster we do not
				// need the cast
				// (Monster.over(handler.player)->decrease life)
			}
			if (Collisions.isColliding(player, tempObject, ID.Experience)) {
				handler.getPlayer().setExperience(((Experience) tempObject).getExperience());
				handler.removeObject(tempObject);
			}
			if (Collisions.isColliding(player, tempObject, ID.Life)) {
				handler.getPlayer().setLife(((Life) tempObject).getLife());
				handler.removeObject(tempObject);
			}
		}
	}

	public void checkBulletCollisionss(final Bullet bullet) {
		ArrayList<GameObject> tmpObjects = handler.getGameObjects();
		for (int i = 0; i < tmpObjects.size(); i++) {
			GameObject tempObject = tmpObjects.get(i);

			if (bullet instanceof BulletImpl && Collisions.isColliding(bullet, tempObject, ID.Block)) {
				handler.removeObject(bullet);

			} else if (Collisions.isColliding(bullet, tempObject, ID.Monster)) {
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
	 * @param ID  that the touched object should have
	 */
	public static boolean isColliding(final GameObject obj1, final GameObject obj2, final ID id) {
		return obj2.getId() == id && obj1.getShape().getBounds2D().intersects(obj2.getShape().getBounds2D());
	}

	public static boolean isColliding(final GameObject obj1, final GameObject obj2) {
		return obj1.getShape().getBounds2D().intersects(obj2.getShape().getBounds2D());
	}

	public void stopMovements() {
		MainPlayer player = handler.getPlayer();
		player.setX(player.getX() + player.getVelX() * -1);
		player.setY(player.getY() + player.getVelY() * -1);
	}
}
