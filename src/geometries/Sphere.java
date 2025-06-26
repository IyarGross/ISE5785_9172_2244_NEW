//sphere.java
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * The Sphere class represents a sphere geometry in three-dimensional space.
 * A sphere is defined by its radius and center point.
 * This class extends the RadialGeometry class.
 */
public class Sphere extends RadialGeometry {

    private Point center;

    /**
     * Constructs a Sphere object with the given radius and center point.
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * calculates the sphere;s normal in point p
     *
     * @param p the point that the normal is going throw
     * @return the sphere;s normal in point p
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }


    /**
     * Finds all intersection points of a ray with the sphere.
     *
     * @param ray the ray to intersect with.
     * @return a list of intersection points, or null if there are no intersections.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getPoint(0);
        Vector dir = ray.getDirection();

        // if the ray starts at the center of the sphere
        if (center.equals(p0))
            return List.of(p0.add(dir.scale(radius)));

        Vector u = (center.subtract(p0));
        double tm = dir.dotProduct(u);
        double d = Util.alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
        if (d >= radius)
            return null;

        double th = Math.sqrt(radius * radius - d * d);
        double t1 = Util.alignZero(tm - th);
        double t2 = Util.alignZero(tm + th);

        // if the ray starts before the sphere
        if (t1 > 0 && t2 > 0)
            return List.of(p0.add(dir.scale(t1)), p0.add(dir.scale(t2)));

        // if the ray starts inside the sphere
        if (t1 > 0)
            return List.of(p0.add(dir.scale(t1)));
        if (t2 > 0)
            return List.of(p0.add(dir.scale(t2)));

        return null;
    }
}
