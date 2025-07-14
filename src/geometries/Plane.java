package geometries;
import primitives.*;
import static primitives.Util.isZero;

import java.util.List;

/**
 * Class Plane is the basic class representing a plane in a Cartesian
 * 3-Dimensional coordinate system.
 * @author Jeshurun and Binyamin
 */
public class Plane extends Geometry {
    /**
     * The point on the plane
     */
    private final Point q0;
    /**
     * The normal to the plane
     */
    private final Vector normal;

    /**
     * constructor creates the plane
     *
     * @param q0     the point on the plane
     * @param normal the normal to the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * constructor creates the plane
     *
     * @param p1 the first point on the plane
     * @param p2 the second point on the plane
     * @param p3 the third point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.q0 = p1;
        // Calculate the normal using the cross product of two vectors on the plane
        // the points should not be on the same line
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this.normal = v1.crossProduct(v2).normalize();
    }

    /**
     * normal getter
     *
     * @return the normal to the geometry
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        Vector rayDir = ray.getDirection();
        Point rayP0 = ray.getHead();

        double denominator = normal.dotProduct(rayDir);

        if (isZero(denominator)) {
            // Ray is parallel to the plane
            return null;
        }

        if (ray.getHead().equals(q0) ||
                isZero(normal.dotProduct(ray.getHead().subtract(q0)))) {
            // Ray starts on the plane or lies in it
            return null;
        }


        // 3. Calculate t
        double t = normal.dotProduct(q0.subtract(rayP0)) / denominator;

        // 4. Intersection behind ray start or on the plane
        if (t <= 0) {
            return null;
        }

        Point p = ray.getPoint(t);
        return List.of(new Intersection(this, p));
    }
}