package it.unibo.geosurv.model.utility;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;
import it.unibo.geosurv.model.player.MainPlayer;

public class Func {

	/**
	 * Return random point on a circumference or ring.
	 *
	 * @param min minimal radius
	 * @param max maximum radius
	 *            NOTE: min must be < than max
	 * @return Pair coordinates x, y
	 */
	public static Pair<Float, Float> randomPoint(float min, float max) {
		if (max <= 0) {
			max = 500.0f;
		}
		// Correct exception if min > = max,
		if (min >= max) {
			System.out.println(
					"randomPoint(): min: " + min + " max: " + max
							+ "Warning: min should be minor to max => automatic reset applied");
			min = max - 10;
		}
		Random random = new Random();
		double angle = 2 * Math.PI * Math.random();
		double minRandom = min / max; // proportion: max / 1 = min / x
		double radius = Math.sqrt(random.nextDouble(minRandom, 1));
		float x = (float) (radius * Math.cos(angle));
		float y = (float) (radius * Math.sin(angle));

		return new Pair<>(x * max, y * max);
	}

	/**
	 * Return closest enemy to the player.
	 *
	 * @param Handler game handler
	 * 
	 * @return GameObject player
	 */
	public static GameObject findClosestEnemy(final Handler handler) {
		GameObject player = handler.getPlayer();
		GameObject closestEnemy = null;
		float closestDistance = Float.MAX_VALUE;
		float distance;
		ArrayList<GameObject> tmpObjects = handler.getObjects();
		GameObject tmpObject;
		float px, py;
		px = player.getX();
		py = player.getY();

		for (int i = 0; i < tmpObjects.size(); i++) {
			tmpObject = tmpObjects.get(i);
			if (tmpObject.getId() == ID.Monster) {
				float ex, ey;

				ex = tmpObject.getX();
				ey = tmpObject.getY();

				distance = (float) Point2D.distance(px, py, ex, ey);
				if (distance < closestDistance) {
					closestDistance = distance;
					closestEnemy = tmpObject;
				}
			}
		}
		return closestEnemy;
	}

	/**
	 * Return angle of from point A to B.
	 *
	 * @param flaot A's x coordinate
	 * @param flaot A'y x coordinate
	 * @param flaot B's x coordinate
	 * @param flaot B'y x coordinate
	 * @return Pair angle
	 */
	// public static Pair<Float, Float> findAngle(final float ax, final float ay,
	// final float bx, final float by) {
	// float angle = (float) Math.atan2(by - ay, bx - ax);
	// return new Pair<Float, Float>((float) Math.cos(angle), (float)
	// Math.sin(angle));
	// }

	/**
	 * Return angle of from point A to B.
	 *
	 * @param origin GameObject A
	 * @param aimed  GameObject B
	 * @return Pair angle
	 */
	public static Pair<Float, Float> findAngle2(final GameObject a, final GameObject b) {
		float ax = a.getX();
		float ay = a.getY();
		float bx = b.getX();
		float by = b.getY();
		float angle = (float) Math.atan2(by - ay, bx - ax);
		return new Pair<Float, Float>((float) Math.cos(angle), (float) Math.sin(angle));
	}

}
