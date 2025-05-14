package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class Geometry{
    /**
     *
     * @param point
     * @return nothing abstract class
     */
    public abstract Vector getNormal(Point point);
}
