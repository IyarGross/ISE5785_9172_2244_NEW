package geometries;

import primitives.Ray;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.isZero;

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
     /**
     * implementation {@link Geometry#getNormal(Point)}
     *
     * @param point point to calculate normal from/to
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        Vector direction = _axisRay.getDir();
        Point P0 = _axisRay.getP0();

        //given point is on base of cylinder
        if(point.equals(P0)||isZero(point.subtract(P0).dotProduct(direction)))
            return direction.normalize();

        // given point is on top base of the cylinder
        if (point.equals(P0.add(direction.scale(height)))||isZero(point.subtract(P0.add(direction.scale(height))).dotProduct(direction)))
            return direction.normalize();

        // given point is on the circumference of cylinder
        return super.getNormal(point);
    }
}
