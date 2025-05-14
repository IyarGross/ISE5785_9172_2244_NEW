package geometries;

import primitives.Ray;
import primitives.Point;
import primitives.Vector;
/**
 * Finite tube delimited by two planes
 */
public class Cylinder extends Tube{

    /**
     * height of cylinder
     */
    final private double height;

    /**
     * cylinder constructor based on a radius , ray (direction), and a height
     * @param axisRay ray originating from base of cylinder
     * @param radius radius of cylinder
     * @param height height of cylinder
     * @throws IllegalArgumentException <p>if height sent as parameter is not a positive value</p>
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(radius, axisRay);
        if(height<= 0)
            throw new IllegalArgumentException("height must be positive value");
        this.height=height;
    }

    /**
     *
     * @param point
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
}
