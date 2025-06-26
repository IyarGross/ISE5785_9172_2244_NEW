//Geometry.java
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * The Geometry interface represents a geometric shape in 3D space.
 * Now it extends the Intersectable interface.
 */
public interface Geometry extends Intersectable {
    /**
     * Computes and returns the normal vector to the geometry at a given point.
     *
     * @param p the point at which to compute the normal vector.
     * @return the normal vector to the geometry at the given point.
     */
    Vector getNormal(Point p);

    @Override
    List<Point> findIntersections(Ray ray);
}