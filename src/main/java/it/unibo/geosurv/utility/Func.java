package it.unibo.geosurv.utility;

import it.unibo.geosurv.core.Handler;
import it.unibo.geosurv.core.GameObject;
import it.unibo.geosurv.core.ID;
import it.unibo.geosurv.core.MainPlayer;
import java.util.LinkedList;
import java.util.Random;

public class Func {

	public static MainPlayer findPlayer(Handler handler) {
		LinkedList<GameObject> tmpObjects = handler.getObjects();
		for (int i = 0; i < tmpObjects.size(); i++) {
			if (tmpObjects.get(i).getId() == ID.Player) {
				return (MainPlayer) tmpObjects.get(i);
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

	/**
	 * Return random point on a circumference or ring.
	 *
	 * @param min minimal radius
	 * @param max maximum radius
	 * @return Pair coordinates x, y
	 */
	public static Pair<Float, Float> randomPoint(final float min, final float max) {
		// TODO: Assert.assertTrue(max > min) ....
		Random random = new Random();
		double angle = 2 * Math.PI * Math.random();
		double minRandom = min / max; // proportion: max / 1 = min / x
		double radius = Math.sqrt(random.nextDouble(minRandom, 1));
		float x = (float) (radius * Math.cos(angle));
		float y = (float) (radius * Math.sin(angle));

		return new Pair<>(x * max, y * max);
	}
}
