package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class represents a sphere at 3D
 */
public class Sphere extends RadialGeometry{
    final private Point center;
    /**
     * Constructs a sphere by a radius and a center point
     * @param radius the radius of the sphere
     * @param center the center point of the sphere
     */
    public Sphere(double radius, Point center)
    {
        super(radius);
        this.center=center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }
}
