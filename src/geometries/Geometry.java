package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Abstract base class for all geometric objects that are able to be intersected with a ray
 * and able to provide a normal vector at a given point on their surface.
 * Implements the {@link Intersectable} interface.
 */
public abstract class Geometry implements Intersectable {
    /**
     * Returns the normal vector to the geometry at the given point on its surface.
     *
     * @param point the point on the surface of the geometry
     * @return the normal vector
     */
    public abstract Vector getNormal(Point point);

    /**
     * Finds the intersection points of a ray with the geometry.
     * This method overrides the one from {@link Intersectable}.
     *
     * @param ray the ray to intersect with
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    @Override
    public abstract List<Point> findIntersections(Ray ray);
}
