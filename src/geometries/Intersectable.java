//Intersectable.java
package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.List;

/**
 * The Intersectable interface declares a method for finding intersections between a Ray and a geometric object.
 */
public interface Intersectable {
    /**
     * Finds all intersection points of a given ray with the geometric object.
     *
     * @param ray the ray to intersect with.
     * @return a list of intersection points between the ray and the object, or null if there are none.
     */
    List<Point> findIntersections(Ray ray);
}