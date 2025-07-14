//sphere.java
package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;

/**
 * The Sphere class represents a sphere geometry in three-dimensional space.
 * A sphere is defined by its radius and center point.
 * This class extends the RadialGeometry class.
 */
public class Sphere extends RadialGeometry {
    /** the center of the sphere */
    private Point center;

    /**
     * Constructs a Sphere object with the given radius and center point.
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * calculates the sphere;s normal in point p
     *
     * @param p the point that the normal is going throw
     * @return the sphere;s normal in point p
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }


    /**
     * finds all the intersections of a ray and the sphere
     *
     * @param ray the ray that we want to check intersections with
     * @return a list of the intersections of ray and sphere
     */

    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray) {
        if (center.equals(ray.getHead())) return List.of(new Intersection(this,ray.getPoint(radius))); //boundary value po is center
        Vector u= center.subtract(ray.getHead());
        double tm = Util.alignZero(u.dotProduct(ray.getDirection())) ;
        double d= sqrt(Util.alignZero(u.lengthSquared()-tm*tm));
        if (d>=radius) return null;
        double th = sqrt(radius*radius-d*d);
        if (Util.alignZero(tm+th)<=0 && Util.alignZero(tm-th)<=0 ) return null;
        if (Util.alignZero(tm+th)<=0) return List.of(new Intersection(this,ray.getPoint(tm-th))); //po inside the shpere
        if (Util.alignZero(tm-th)<=0) return List.of(new Intersection(this,ray.getPoint(tm+th))); ;//po inside the shpere
        return List.of(new Intersection(this,ray.getPoint(tm-th)),new Intersection(this,ray.getPoint(tm+th))); //regular case to intersection
    }
}
