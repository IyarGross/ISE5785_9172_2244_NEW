package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

import java.util.List;

/**
 * represents a tube defined by a central axis ray and radius
 */
public class   Tube extends RadialGeometry {
    /**
     * the central axis ray of the tube
     */
    protected final Ray axis;

    /**
     * constructs a tube with given axis and radius
     * @param axis The central axis ray
     * @param radius The radius of the tube (must be positive)
     */
    Tube(Ray axis, double radius) {
        super(radius);
        this.axis = axis;
    }

    /**
     * gets the normal vector at a point on the tube's surface
     * @param point The point to calculate the normal at
     * @return The normal vector at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        double t = point.subtract(axis.getHead()).  //point-head = OP
                   dotProduct(axis.getDirection()); // <OP,V> = t
        if (Util.isZero(t)) {
            return point.subtract(axis.getHead()).normalize();
        }
        else {
            return (point.subtract( //point-(head+Vt) = normal
                    axis.getHead().add(axis.getDirection(). //head + Vt
                            scale(t))))
                    .normalize(); //normalize(normal)
        }
    }
    @Override
    protected List<Intersection> calculateIntersectionsHelper(Ray ray) {return null;}
}