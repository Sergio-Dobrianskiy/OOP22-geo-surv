package it.unibo.geosurv.model.utility;


/**
 * Utility class to represent pairs of two instances of the same type.
 *
 * @param <X> the first type
 * @param <Y> the second type
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Creates a new pair given X and Y values.
     *
     * @param x the x value
     * @param y the y value
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X for this pair.
     *
     * @return the X
     */
    public X getX() {
        return x;
    }

    /**
     * Gets the Y for this pair.
     *
     * @return the Y
     */
    public Y getY() {
        return y;
    }

    /**
     * Generate a hash code for this Pair.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    /**
     * Test this Pair for equality with another Object.
     *
     * @param obj the other object
     * @return true if equals, false otherwise
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {

            return false;
        }

        final Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

    /**
     * String representation of this Pair.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
