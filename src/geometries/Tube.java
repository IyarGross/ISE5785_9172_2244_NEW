package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
    /**
     * ray originating from base of tube
     */
    protected final Ray axisRay;

    /**
     * @param radius
     * @param axisRay
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }
    /**
     * tube constructor based on a radius and a ray from base of tube
     * @param axisRay ray originating from base of tube
     * @throws IllegalArgumentException <p>if radius sent as parameter is not a positive value</p>
     */

    /**
     * @param point
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
