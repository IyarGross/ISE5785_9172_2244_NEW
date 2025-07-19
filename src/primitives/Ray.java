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
        return (points == null || points.isEmpty()) ? null
                : findClosestIntersection(points.stream()
                .map(p -> new Intersection(null, p))
                .toList()).point;
    }

    /**
     * finds the closest intersection to the ray head
     * @param intersections list of intersections to check
     * @return the closest intersection or null if list is empty
     */
    public Intersection findClosestIntersection(List<Intersection> intersections) {
        if (intersections == null || intersections.isEmpty()) {
            return null;
        }

        Intersection closestIntersection = intersections.getFirst();
        double minDistance = closestIntersection.point.distance(this.head);

        for (Intersection intersection : intersections) {
            double distance = intersection.point.distance(this.head);
            if (distance < minDistance) {
                minDistance = distance;
                closestIntersection = intersection;
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

