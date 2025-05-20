package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * Ray class represents a ray (a directed vector starting from a specific point)
 * in 3D Cartesian coordinates.
 */
public class Ray {
    final private Point point;
    final private Vector dir;

    /**
     * Constructs a ray from a starting point and a direction vector.
     * The direction vector is normalized upon construction.
     *
     * @param point starting point of the ray
     * @param dir   direction vector of the ray (will be normalized)
     */
    public Ray(Point point, Vector dir) {
        this.point = point;
        this.dir = dir.normalize();// Normalize direction to ensure consistent behavior
    }
    /**
     * Returns a string representation of the ray.
     * (currently not implemented – returns null)
     *
     * @return string representation of the ray
     */
    @Override
    public String toString() {
        return "Ray{point=P, dir=V}";
    }
    /**
     * Checks equality between this ray and another object.
     * Two rays are equal if both their starting points and direction vectors are equal.
     *
     * @param o the object to compare with
     * @return true if both rays have same point and direction, false otherwise
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;

        return Objects.equals(point, ray.point) && dir.equals(ray.dir);
    }

    /**
     * Returns the starting point of the ray.
     *
     * @return the origin point of the ray
     */
    final public Point getP0() {
        return point;
    }
    /**
     * Returns a point on the ray at a given distance from its origin.
     * The calculation is: P = P0 + t * dir
     *
     * @param t the distance along the direction vector (must be non-negative)
     * @return a point on the ray at distance t
     * @throws IllegalArgumentException if t is negative
     */
    public Point getPoint(double t) {
        if (t < 0)
            throw new IllegalArgumentException("Distance t must be non-negative");
        if (isZero(t))
            return this.point; // Optimization: avoid unnecessary calculation
        return point.add(dir.scale(t)); // Return point along the ray at distance t
    }

    /**
     * Returns the normalized direction vector of the ray.
     *
     * @return the ray's direction vector
     */
    final public Vector getDir() {
        return dir;
    }
}
