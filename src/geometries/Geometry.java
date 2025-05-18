package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public abstract class Geometry implements Intersectable {
    /**
     *
     * @param point
     * @return nothing abstract class
     */
    public abstract Vector getNormal(Point point);

    public abstract double findIntersections(Ray ray);
}
