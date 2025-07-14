//cylinder.java
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;
import java.util.List;

/**
 * Represents a cylinder in 3D space, extending from a given axis with a certain radius and height.
 * Inherits from the Tube class.
 */
public class Cylinder extends Tube{

    private final double height; // The height of the cylinder

    /**
     * Constructs a cylinder with the given radius, axis, and height.
     * @param radius The radius of the cylinder.
     * @param axis The axis of the cylinder.
     * @param height The height of the cylinder.
     */
    public Cylinder(double radius, Ray axis, double height) {
        super(radius,axis);
        this.height=height;
    }

    /**
     * calculates the normal to the cylinder in point p
     *
     * @param point the point that the normal is going throw
     * @return the normal to the cylinder in point p
     */
    @Override
    public Vector getNormal(Point point) {
        Point p0 = axis.getPoint(0);
        Vector dir = this.axis.getDirection();
        Vector dirP1 = dir.scale(height);

        //  If p0 is the head of the axis
        if (point.equals(p0))
            return dir.scale(-1);

        // If p1 is the end of the axis
        if (point.equals(p0.add(dirP1)))
            return dir;

        // If the point is on the top or bottom surface of the cylinder
        if (p0.subtract(point).dotProduct(dir) == 0)
            return dir.scale(-1);

        if (p0.add(dirP1).subtract(point).dotProduct(dir) == 0)
            return dir;

        // Otherwise, call the superclass method
        return super.getNormal(point);
    }

    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        return null;

    }
}