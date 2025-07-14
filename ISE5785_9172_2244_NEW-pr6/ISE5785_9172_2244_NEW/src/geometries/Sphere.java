package geometries;
import java.util.ArrayList;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static java.lang.Math.sqrt;

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

    @Override
    public List<Point> findIntsersections(Ray ray) {
        if (center.equals(ray.getP0())) return new ArrayList() {{add(ray.getPoing(radius));}};//boundary value po is center
        Vector u= center.subtract(ray.getP0());
        double tm = Util.alignZero(u.dotProduct(ray.getDir())) ;
        double d= sqrt(Util.alignZero(u.lengthSquared()-tm*tm));
        if (d>=radius) return null;
        double th = sqrt(radius*radius-d*d);
        if (Util.alignZero(tm+th)<=0 && Util.alignZero(tm-th)<=0 ) return null;
        if (Util.alignZero(tm+th)<=0) return  new ArrayList() {{add(ray.getPoing(tm-th));}};//po inside the shpere
        if (Util.alignZero(tm-th)<=0) return  new ArrayList() {{add(ray.getPoing(tm+th));}};//po inside the shpere
        return new ArrayList() {{add(ray.getPoing(tm-th));add(ray.getPoing(tm+th));}};//regular case to intersection

    }

}
