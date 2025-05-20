package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Plane represents an infinite 2D plane in 3D space.
 */
public class Plane extends Geometry {
    protected final Point p0;
    protected final Vector normal;
    /**
     * Constructs a plane based on three points in space.
     * The plane is defined by the points p1, p2, p3.
     * The normal vector is calculated as the normalized cross product of vectors (p2-p1) and (p3-p2).
     *
     * @param p1 First point on the plane
     * @param p2 Second point on the plane
     * @param p3 Third point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1); // Vector from p1 to p2
        Vector v2 = p3.subtract(p2); // Vector from p2 to p3
        this.normal = v1.crossProduct(v2).normalize(); // Normal vector to the plane
        this.p0 = p1; // Reference point on the plane
    }

    /**
     * Constructs a plane based on a normal vector and a point on the plane.
     *
     * @param normal the normal vector to the plane
     * @param p0     a point on the plane
     */
    public Plane(Vector normal, Point p0) {
        this.normal = normal;
        this.p0 = p0;
    }

    /**
     * Returns the normal vector to the plane.
     * Since a plane has the same normal everywhere, the input point is unused.
     *
     * @param point any point on the plane (unused)
     * @return the normalized normal vector of the plane
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * Finds the intersection points of the plane with the given ray.
     *
     * @param ray the ray to intersect with the plane
     * @return a list containing the intersection point if one exists; otherwise, null.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        // If the ray origin is exactly on the plane's reference point, no intersections to consider
        if (p0.equals(ray.getP0())) return null;

        Vector n = getNormal();
        double nv = ray.getDir().dotProduct(n);

        // If dot product is zero, ray is parallel to the plane => no intersection
        if (Util.isZero(nv)) return null;

        // Calculate t parameter for the ray equation: P = P0 + t * v
        double t = Util.alignZero((p0.subtract(ray.getP0()).dotProduct(n)) / nv);

        // If t <= 0, intersection is behind the ray origin or at origin, so no valid intersection
        if (t <= 0) return null;

        // Return the single intersection point at distance t along the ray
        return new ArrayList<>() {{
            add(ray.getPoint(t));
        }};
    }

    /**
     * Private helper method to get the plane normal.
     *
     * @return the normal vector of the plane
     */
    private Vector getNormal() {
        return normal;
    }

}

