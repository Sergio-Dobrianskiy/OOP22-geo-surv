package it.unibo.geosurv.model.utility;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Random;

import it.unibo.geosurv.model.GameObject;
import it.unibo.geosurv.model.Handler;
import it.unibo.geosurv.model.ID;

/**
 * contains utility functions.
 */

public final class Func {

	final static Random random = new Random();

	/**
	 * To prevent creation of instances.
	 */
	private Func() {
	}

	/**
	 * Return random point on a circumference or ring.
	 *
	 * @param min minimal radius
	 * @param max maxi mum radius
	 *            NOTE: min must be < than max
	 * @return Pair coordinates x, y
	 */
	public static Pair<Float, Float> randomPoint(final float min, final float max) {
		// if (max <= 0) {
		// max = 500.0f;
		// }
		// Correct exception if min > = max,
		// if (min >= max) {
		// System.out.println(
		// "randomPoint(): min: " + min + " max: " + max
		// + "Warning: min should be minor to max");
		// min = max - 10;
		// }
		final double angle = 2 * Math.PI * Math.random();
		final double minRandom = min / max; // proportion: max / 1 = min / x
		final double radius = Math.sqrt(random.nextDouble(minRandom, 1));
		final float x = (float) (radius * Math.cos(angle));
		final float y = (float) (radius * Math.sin(angle));

		return new Pair<>(x * max, y * max);
	}

	/**
	 * Return closest enemy to the player.
	 *
	 * @param handler game handler
	 * 
	 * @return GameObject player
	 */
	public static GameObject findClosestEnemy(final Handler handler) {
		final List<GameObject> tmpObjects = handler.getGameObjects();
		final GameObject player = handler.getPlayer();
		GameObject closestEnemy = null;
		float closestDistance = Float.MAX_VALUE;
		float distance;
		float px;
		float py;
		px = player.getX();
		py = player.getY();

		for (final GameObject tmpObject : tmpObjects) {
			if (tmpObject.getId() == ID.Monster) {
				float ex;
				float ey;

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
	 * @param a GameObject origin
	 * @param b GameObject aimed
	 * @return Pair angle
	 */
	public static Pair<Float, Float> findAngle2(final GameObject a, final GameObject b) {
		final float ax = a.getX();
		final float ay = a.getY();
		final float bx = b.getX();
		final float by = b.getY();
		final float angle = (float) Math.atan2(by - ay, bx - ax);
		return new Pair<Float, Float>((float) Math.cos(angle), (float) Math.sin(angle));
	}

}
