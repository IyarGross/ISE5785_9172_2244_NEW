package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * Sphere class represents a sphere in 3D space.
 * Extends RadialGeometry which provides the radius property.
 */
public class Sphere extends RadialGeometry {
    final private Point center;

    /**
     * Constructs a sphere with given radius and center.
     *
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }
    /**
     * Returns the normal vector to the sphere at a given point on its surface.
     * The normal is the vector from the center of the sphere to the given point, normalized.
     *
     * @param point a point on the sphere surface
     * @return normalized normal vector at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }
    /**
     * Finds the intersection points of the sphere with the given ray.
     *
     * Algorithm explanation:
     * - If ray origin equals the center, return the point at radius distance along the ray direction.
     * - Calculate vector u from ray origin to sphere center.
     * - Project u on the ray direction to find tm (closest approach).
     * - Calculate distance d from center to the ray.
     * - If d >= radius, no intersections.
     * - Calculate th, the distance from closest approach to intersection points.
     * - Calculate intersection distances t1 = tm - th and t2 = tm + th.
     * - Return valid intersection points (where t > 0).
     *
     * @param ray the ray to intersect with the sphere
     * @return list of intersection points or null if none exist
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        // Special case: if ray origin is at the center of the sphere
        if (center.equals(ray.getP0()))
            return new ArrayList<>() {{
                add(ray.getPoint(radius));
            }};

        Vector u = center.subtract(ray.getP0());
        double tm = Util.alignZero(u.dotProduct(ray.getDir()));
        double d = sqrt(Util.alignZero(u.lengthSquared() - tm * tm));

        // If the shortest distance from center to ray is greater or equal to radius, no intersection
        if (d >= radius)
            return null;

        double th = sqrt(radius * radius - d * d);

        // Calculate potential intersection points along ray
        double t1 = Util.alignZero(tm - th);
        double t2 = Util.alignZero(tm + th);

        // If both intersections are behind the ray origin, no intersection
        if (t1 <= 0 && t2 <= 0)
            return null;

        // If only t1 is positive, return one intersection
        if (t1 > 0 && t2 <= 0)
            return new ArrayList<>() {{
                add(ray.getPoint(t1));
            }};

        // If only t2 is positive, return one intersection
        if (t2 > 0 && t1 <= 0)
            return new ArrayList<>() {{
                add(ray.getPoint(t2));
            }};

        // Both intersections are in front of the ray origin - return both
        return new ArrayList<>() {{
            add(ray.getPoint(t1));
            add(ray.getPoint(t2));
        }};
    }
}
