package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;
/**
 * Tube class represents an infinite tube (cylindrical surface) in 3D space.
 * It extends RadialGeometry, so it has a radius and an axis represented by a Ray.
 */
public class Tube extends RadialGeometry {


    protected final double _radius;
    /**
     * radius of tube
     */
    protected final Ray _axisRay;
    /**
     * axis ray representing the central axis of the tube
     */

    /**
     * Constructor for Tube based on a radius and an axis ray.
     *
     * @param radius  radius of the tube
     * @param axisRay ray representing the tube's central axis, originating from base of tube
     * @throws IllegalArgumentException if the radius is not positive (handled in superclass)
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        _axisRay = axisRay;
        _radius = radius;
    }
    /**
     * Calculates the normal vector to the tube surface at a given point.
     *
     * Explanation:
     * - Project the vector from the axis ray's origin (P0) to the given point onto the axis ray direction.
     * - The projection scalar t is the dot product of the axis direction and the vector from P0 to the point.
     * - Compute point O on the axis ray corresponding to this projection:
     *   If t == 0 (using isZero utility), O = P0 (base point).
     *   Else, O = P0 + t * direction.
     * - The normal vector is then the vector from O to the given point, normalized.
     * - If the given point lies exactly on the axis ray (point.equals(O)), throw IllegalArgumentException,
     *   since a tube's surface normal is undefined on the axis.
     *
     * @param point point on the tube surface
     * @return normalized normal vector at the given point
     * @throws IllegalArgumentException if the point lies exactly on the tube's axis ray
     */
    @Override
    public Vector getNormal(Point point) {
        Vector direction = _axisRay.getDir();
        Point P0 = _axisRay.getP0();

        double t = direction.dotProduct(point.subtract(P0));
        Point O;
        if (isZero(t)) {
            O = P0;
        } else {
            O = P0.add(direction.scale(t));
        }

        if (point.equals(O))
            throw new IllegalArgumentException("point cannot be on the axis ray");

        return point.subtract(O).normalize();
    }

    /**
     * Finds the intersection points of a given ray with the tube.
     *
     * Note:
     * This method currently returns an empty list, meaning no intersections are calculated yet.
     * Proper implementation should compute intersections between the ray and the tube surface.
     *
     * @param ray the ray to find intersections with the tube
     * @return list of intersection points (currently always empty)
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return List.of();
    }
}
