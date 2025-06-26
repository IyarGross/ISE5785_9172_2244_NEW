//tube.java
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;

import java.util.List;

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
    public List<Point> findIntersections(Ray ray) {
        Vector d = ray.getDirection();
        Vector v = axis.getDirection();
        Point P0 = ray.getHead();
        Point O = axis.getHead();
        Vector deltaP = P0.subtract(O);


        Vector A = d.subtract(v.scale(d.dotProduct(v)));
        Vector B = deltaP.subtract(v.scale(deltaP.dotProduct(v)));

        double a = A.dotProduct(A);
        double b = 2 * A.dotProduct(B);
        double c = B.dotProduct(B) - radius * radius;

        double disc = b * b - 4 * a * c;
        if (disc < 0 || Util.isZero(disc))
            return disc < 0 ? null : List.of(ray.getPoint(Util.alignZero(-b / (2 * a))));

        double sqrtDisc = Math.sqrt(disc);
        double t1 = Util.alignZero((-b + sqrtDisc) / (2 * a));
        double t2 = Util.alignZero((-b - sqrtDisc) / (2 * a));
        List<Point> points = new java.util.LinkedList<>();
        if (t1 > 0)
            points.add(ray.getPoint(t1));
        if (t2 > 0)
            points.add(ray.getPoint(t2));

        return points.isEmpty() ? null : points;
    }
}