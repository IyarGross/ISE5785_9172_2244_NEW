package geometries;
import java.util.List;

import primitives.*;

/**
 * plane projects a plane in 3d
 */
public class Plane extends Geometry {
    protected final Point q0 ;
    protected final Vector normal ;
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1) ; // two new vectors
        Vector v2 =p3.subtract(p2) ; // two new vectors ;
        this.normal = v1.crossProduct(v2).normalize();
        this.q0 = p1 ;
    }

    /**
     *
     * @param normal
     * @param q0
     */
    public Plane(Vector normal, Point q0) {
        this.normal = normal;
        this.q0 = q0;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }
}
