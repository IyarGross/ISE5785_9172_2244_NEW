package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Tube extends RadialGeometry {


    protected final double _radius;
    /**
     * radius of tube
     */
    protected final Ray _axisRay;
    /**
     * ray originating from base of tube
     */
    /**
     * @param radius
     * @param axisRay
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        _axisRay = axisRay;
        _radius = radius;
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
        Vector direction = _axisRay.getDir();
        Point P0 = _axisRay.getP0();

        // find distance of point from origin and scale direction
        // to get the point on tube parallel to given point
        double t = (direction.dotProduct(point.subtract(P0)));
        Point O = P0.add(direction.scale(t));

        //given point is on axis ray
        if (point.equals(O))
            throw new IllegalArgumentException("point cannot be on the axis ray");

        // point is against tube origin point
        if (isZero(t))
            return point.subtract(P0).normalize();
            // any other point
        else
            return point.subtract(O).normalize();
    }
}
