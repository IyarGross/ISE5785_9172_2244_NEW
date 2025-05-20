package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Triangle class represents a triangle in 3D Cartesian coordinate system.
 * Inherits from Polygon class.
 */
public class Triangle extends Polygon {
    /**
     * Constructs a triangle with exactly three vertices.
     *
     * @param vertices the three points that define the triangle
     * @throws IllegalArgumentException if the number of vertices is not exactly 3
     */
    public Triangle(Point... vertices) {
        super(vertices);
        if (vertices.length != 3)
            throw (new IllegalArgumentException("ERROR - triangle must get exactly three points"));
    }
    /**
     * Returns the normal vector to the triangle at a given point.
     * Delegates to the Polygon class's getNormal method.
     *
     * @param point a point on the triangle surface
     * @return normalized normal vector
     */
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
    /**
     * Finds the intersection points of a given ray with the triangle.
     *
     * Explanation:
     * - First, find the intersection points of the ray with the plane containing the triangle.
     * - If no intersection with the plane, return null.
     * - Otherwise, get the intersection point p on the plane.
     * - Use the inside-outside test for triangles:
     *   For each edge of the triangle, compute the vector from the edge to the point p,
     *   then compute the cross product with the edge vector.
     *   Check the sign of the dot product of these cross products to determine if p lies inside the triangle.
     * - If point is inside all three edges, return the intersection point list.
     * - Otherwise, return null (the intersection is outside the triangle).
     *
     * The method catches IllegalArgumentException which might occur at boundary cases and returns null.
     *
     * @param ray the ray to intersect with the triangle
     * @return list containing the intersection point(s) or null if no intersection
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        try {
            List<Point> list = plane.findIntersections(ray);
            if (list == null) return null;

            Point p = list.get(0);

            // Compute cross products for each edge with point p
            Vector n2 = vertices.get(2).subtract(vertices.get(1))
                    .crossProduct(vertices.get(1).subtract(p));

            boolean insideTest1 = (vertices.get(1).subtract(vertices.get(0))
                    .crossProduct(vertices.get(0).subtract(p)))
                    .dotProduct(n2) > 0;

            boolean insideTest2 = n2.dotProduct(
                    (vertices.get(0).subtract(vertices.get(2))
                            .crossProduct(vertices.get(2).subtract(p)))) > 0;

            // If point p is inside all edges, return intersection point
            if (insideTest1 && insideTest2)
                return list;

            // Otherwise, point is outside triangle
            return null;
        } catch (IllegalArgumentException i) {
            // Catch potential exceptions on boundary cases and return null
            return null;
        }
    }
}
