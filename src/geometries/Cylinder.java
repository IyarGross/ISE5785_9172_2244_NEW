package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * represents a cylinder by defining a height and an axis ray and a radius
 */
public class Cylinder extends Tube {
    /**
     * the height of the cylinder
     */
    private final double height;

    /**
     * creates a new cylinder with given parameters
     * @param height the cylinder height
     * @param axis the central axis ray
     * @param radius the radius of the cylinder
     * @throws IllegalArgumentException if height is not positive
     */
    public Cylinder(double height, Ray axis, double radius) {
        super(axis, radius);
        if(height <= 0)
            throw new IllegalArgumentException("height must be positive");
        this.height = height;
    }

    /**
     * calculates the normal vector at given point on cylinder
     * @param point the point to calculate normal at
     * @return the normal vector at the point
     */
    @Override
    public Vector getNormal(Point point) {
        Point p0 = axis.getPoint(0d);
        Vector direction = this.axis.getDirection();

        if (point.equals(p0))
            return direction.scale(-1);

        if (point.equals(axis.getPoint(height)))
            return direction;

        if (Util.isZero(p0.subtract(point).dotProduct(direction)))
            return direction.scale(-1d);

        if (Util.isZero(axis.getPoint(height).subtract(point)
                .dotProduct(direction)))
            return direction;

        return super.getNormal(point);
    }
    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray) {return null;}
}
