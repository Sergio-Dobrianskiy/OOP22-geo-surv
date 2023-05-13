package it.unibo.geosurv.model;

import java.util.LinkedList;
import it.unibo.geosurv.model.monsters.Monster;
// import it.unibo.geosurv.model.weapons.autogun.BulletImpl;
import it.unibo.geosurv.model.Handler;

public class Collisions {
	
	// private Handler handler;
	
	// public Collisions(Handler handler) {
	// 	this.handler = handler;
	// }
	
	// private void checkCollisions() {
	// 	LinkedList<GameObject> tmpObjects = handler.getObjects();
	// 	for (int i = 0; i < tmpObjects.size(); i++) {
	// 		GameObject tempObject = tmpObjects.get(i);

	// 		if (this instanceof BulletImpl && isColliding(tempObject, ID.Block)) {
	// 			handler.removeObject(this);
				
	// 		} else if (isColliding(tempObject, ID.Monster)) {
	// 			((Monster) tempObject).hit(this.damage); // TODO: verify the cast
	// 			((Monster) tempObject).bounce(); // TODO: verify the cast
	// 			if (this instanceof BulletImpl) {
	// 				handler.removeObject(this);
	// 			}
	// 		}
	// 	}
	// }
	
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
}
