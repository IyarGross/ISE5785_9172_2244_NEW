package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Interface for geometric shapes that can be intersected by rays.
 * Classes implementing this interface must provide an implementation of the
 * method {@link #findIntersections(Ray)} to find intersection points between
 * the shape and a given ray.
 */
public interface Intersectable {
    /**
     * Finds all intersection points between the geometry and the given ray.
     *
     * @param ray the ray to intersect with the geometry
     * @return a list of intersection points, or {@code null} if no intersections exist
     */
    List<Point> findIntersections(Ray ray);

}
