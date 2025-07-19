package primitives;

import geometries.Intersectable;
import geometries.Intersectable.Intersection;
import java.util.List;

/**
 * Class which represents a Ray which is normalized
 */
public class Ray {
    /**
     * the starting point of the ray
     */
    private final Point head;

    /**
     * the normalized direction vector of the ray
     */
    private final Vector direction;

    private final double DELTA = 0.1;

    /**
     * creates a new ray with given starting point and direction
     * @param head the starting point
     * @param direction the direction vector which will be normalized
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }
    /**
     * constructor creates the ray
     * @param p0 the beginning of the ray
     * @param dir the direction of the ray
     * @param normal the normal vector to the surface
     */
    public Ray(Point p0, Vector dir, Vector normal) {
        this.direction= dir.normalize();
        double nv = normal.dotProduct(dir);
        if (Util.isZero(nv)) {
            this.head = p0;
        }
        else if (nv > 0) {
            this.head = p0.add(normal.scale(DELTA));
        }
        else {
            this.head = p0.subtract(normal.scale(DELTA));
        }
    }

    /**
     * gets the direction vector of the ray
     * @return the normalized direction vector
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * gets the starting point of the ray
     * @return the head point
     */
    public Point getHead() {
        return head;
    }

    /**
     * calculates a point on the ray at given distance
     * @param t the distance from head
     * @return the point at distance t
     */
    public Point getPoint(double t) {
        try {
            return head.add(direction.scale(t));
        } catch (IllegalArgumentException e) {
            return head;
        }
    }

    /**
     * finds the closest point to the ray head from a list of points
     * @param points list of points to check
     * @return the closest point or null if list is empty
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null
                ? null
                : findClosestIntersection(
                points.stream().map(
                        p -> new Intersection(null, p)).toList()).point;
    }

    public Intersection findClosestIntersection(List<Intersection> intersections) {
        if (intersections == null || intersections.isEmpty()) {
            return new Intersection(null, null);
        }
        Intersection closestIntersection = intersections.getFirst();
        double minDistance = head.distance(closestIntersection.point);
        for (Intersection intersection : intersections) {
            double distance = head.distance(intersection.point);
            if (distance < minDistance) {
                closestIntersection = intersection;
                minDistance = distance;
            }
        }
        return closestIntersection;
    }
    @Override
    public String toString()    {
        return "head: " + head.toString() + " direction: " + direction.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }
}

