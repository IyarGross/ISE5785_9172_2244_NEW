package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * Finite tube delimited by two planes
 */
public class Cylinder extends Tube {

    /**
     * height of cylinder
     */
    final private double height;

    /**
     * cylinder constructor based on a radius , ray (direction), and a height
     *
     * @param axisRay ray originating from base of cylinder
     * @param radius  radius of cylinder
     * @param height  height of cylinder
     * @throws IllegalArgumentException <p>if height sent as parameter is not a positive value</p>
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(radius, axisRay);
        if (height <= 0)
            throw new IllegalArgumentException("height must be positive value");
        this.height = height;
    }

    /**
     * Implementation of {@link Geometry#getNormal(Point)}.
     * Returns the normal vector to the cylinder at a given point.
     * The method distinguishes between 3 cases:
     * - The point is on the bottom base
     * - The point is on the top base
     * - The point is on the curved lateral surface (delegated to Tube)
     *
     * @param point point to calculate normal from/to
     * @return normal vector to the surface at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        // direction vector of the cylinder axis
        Vector direction = _axisRay.getDir();
        // base point of the cylinder (start of the axis ray)
        Point P0 = _axisRay.getP0();

        // ========== Case 1: Point lies on the bottom base of the cylinder ==========
        // either exactly equals to P0, or lies in the plane orthogonal to direction through P0
        if (point.equals(P0) || isZero(point.subtract(P0).dotProduct(direction)))
            return direction.normalize();// normal is the axis direction

        // ========== Case 2: Point lies on the top base of the cylinder ==========
        // calculate top base center: P0 + direction * height
        Point topBaseCenter = P0.add(direction.scale(height));

        // either exactly equals to top center, or lies in the plane orthogonal to direction through top center
        if (point.equals(topBaseCenter) || isZero(point.subtract(topBaseCenter).dotProduct(direction)))
            return direction.normalize(); // normal is also the axis direction (same as bottom)


        // ========== Case 3: Point lies on the lateral surface of the cylinder ==========
        // delegate to Tube's implementation (normal is orthogonal to axis)
        return super.getNormal(point);
    }
}
