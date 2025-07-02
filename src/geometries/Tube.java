//tube.java
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

import java.util.List;

import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;

/**
 * The Tube class represents a tube geometry in three-dimensional space.
 * A tube is defined by its radius and axis (a ray representing its center line).
 * This class extends the RadialGeometry class.
 */
public class Tube extends RadialGeometry {

    /**
     * The axis of the tube, represented by a ray.
     */
    protected Ray axis;

    /**
     * Constructs a new Tube object with the specified radius and axis.
     *
     * @param radius The radius of the tube.
     * @param axis   The axis of the tube.
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point p) {
        if (p.subtract(axis.getHead()).dotProduct(axis.getDirection()) == 0)
            throw new IllegalArgumentException("ERROR: the two points are vertical to the direction vector of the tube");
        double t = axis.getDirection().dotProduct(p.subtract(axis.getHead()));
        Point o = axis.getHead().add(axis.getDirection().scale(t));
        return p.subtract(o).normalize();
    }

    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        Vector n = ray.getDirection();
        Point o = ray.getHead();
        Vector a = axis.getDirection();
        double dot, A, B;
        if (a.isParallel(n)) { //if the ray is parallel to the tube, there are no intersections
            return null;
        }
        Vector cross = n.crossProduct(a);
        A = alignZero(cross.lengthSquared());
        if (o.equals(axis.getHead())) {
            dot = 0;
            B = 0;
        } else {
            Vector b = axis.getHead().subtract(o);
            dot = alignZero(b.dotProduct(cross));
            if (a.isParallel(b)) {
                B = 0;
            } else {
                B = alignZero(cross.dotProduct(b.crossProduct(a)));
            }
        }
        double discriminant = alignZero(A * radius * radius - a.lengthSquared() * dot * dot);
        if (discriminant <= 0) {
            //either there are no intersections, or there is only one which means the ray is tangent to the tube
            return null;
        }
        double sqrt = sqrt(discriminant);
        double d1 = (B - sqrt) / A; //offset of the first intersection point
        double d2 = (B + sqrt) / A; //offset of the second intersection point
        if (alignZero(d1) <= 0 ) { //the ray starts on the tube
            if (alignZero(d2) <= 0) {
                return null;
            }
            return List.of(new Intersection(this, ray.getPoint(d2))); //there is only one intersection point
        }
        return List.of(
                new Intersection(this, ray.getPoint(d1)),
                new Intersection(this, ray.getPoint(d2))
        );
    }

}