//plane.java
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The Plane class represents a plane geometry in 3D space.
 * It implements the Geometry interface.
 */
public class Plane extends Geometry {

    /** A point on the plane. */
    private Point p0;

    /** The normal vector to the plane. */
    private Vector normal;

    /**
     * Constructs a Plane object from three points on the plane.
     * @param a The first point on the plane.
     * @param b The second point on the plane.
     * @param c The third point on the plane.
     */
    public Plane(Point a, Point b, Point c) {
        this.p0 = a;
        Vector vec1 = a.subtract(b);
        Vector vec2 = a.subtract(c);
        this.normal = vec1.crossProduct(vec2).normalize();
    }

    /**
     * Constructs a Plane object from a point on the plane and its normal vector.
     * @param p0 A point on the plane.
     * @param normal The normal vector to the plane.
     */
    public Plane(Point p0, Vector normal) {
        this.p0 = p0;
        this.normal = normal.normalize();
    }


    @Override
    public Vector getNormal(Point q) {
        return normal;
    }

    /**
     * Returns the normal vector to the plane.
     * @return The normal vector to the plane.
     */
    public Vector getNormal() {
        return normal;
    }


    /**
     * finds all the intersections of a ray and the plane
     * @param ray the ray that we want to check intersections with
     * @return a list of the intersections of ray and plane
     */

    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        Vector direction = ray.getDirection();
        Point head = ray.getHead();
        double dotProduct = alignZero(direction.dotProduct(normal));
        if (dotProduct == 0) {
            return null;
        }
        if (p0.equals(head)) {
            return null;
        }
        double t = alignZero(normal.dotProduct(p0.subtract(head)) / dotProduct);
        if (t <= 0) {
            return null;
        } else {
            return List.of(new Intersection(this, ray.getPoint(t)));
        }
    }
}