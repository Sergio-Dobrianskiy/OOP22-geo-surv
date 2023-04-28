package it.unibo.geosurv.utility;
import it.unibo.geosurv.core.Handler;
import it.unibo.geosurv.core.GameObject;
import it.unibo.geosurv.core.ID;
import java.util.LinkedList;

public class Func {
	
	public static GameObject findPlayer(Handler handler) {
		LinkedList<GameObject> tmpObjects = handler.getObjects();
		for (int i = 0; i < tmpObjects.size(); i++) {
			if (tmpObjects.get(i).getId() == ID.Player) {
				return tmpObjects.get(i);
			}
		}
		return null;
	}
	
	public static GameObject findByID(Handler handler, ID id) {
		LinkedList<GameObject> tmpObjects = handler.getObjects();
		for (int i = 0; i < tmpObjects.size(); i++) {
			if (tmpObjects.get(i).getId() == id) {
				return tmpObjects.get(i);
			}
		}
		return null;
	}
}
