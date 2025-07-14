package geometries;
import java.util.List;
import java.util.ArrayList;
import primitives.*;

/**
 * plane projects a plane in 3d
 */
public class Plane extends Geometry {
    protected final Point p0;
    protected final Vector normal;

    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1); // two new vectors
        Vector v2 = p3.subtract(p2); // two new vectors ;
        this.normal = v1.crossProduct(v2).normalize();
        this.p0 = p1;
    }

    /**
     * @param normal
     * @param p0
     */
    public Plane(Vector normal, Point p0) {
        this.normal = normal;
        this.p0 = p0;
    }

    /**
     * @param point
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }


    @Override
    public List<Point> findIntsersections(Ray ray) {
        if(p0.equals(ray.getP0())) return null;
        Vector n = getNormal();
        double nv =  ray.getDir().dotProduct(getNormal());
        if (Util.isZero(nv)) return null;//the ray parallel or on the plane
        double t= Util.alignZero((p0.subtract(ray.getP0() ).dotProduct(getNormal()))/nv) ;
        if (t<=0) return null;//the ray points on the other direction
        return  new ArrayList() {{add(ray.getPoing(t));}};
    }

    private Vector getNormal() {
        return normal;
    }

}

