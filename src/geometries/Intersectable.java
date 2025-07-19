package geometries;

import lighting.LightSource;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;
/**
 * Interface for geometries that can be intersected by a ray.
 * Provides a method to find intersection points of a ray with the geometry.
 */
public abstract class Intersectable {

    /**
     * Finds all intersection points between the given ray and the geometry.
     * @param ray the ray we will be checking all the intersections with
     * @return a list of all point which the ray intersects with the geometry
     * @author me
     */
    public  List<Point> findIntersections(Ray ray) {
        var list = calculateIntersections(ray);
        return list == null ? null : list.stream().map(intersection -> intersection.point).toList();
    }

    /**
     * finds all intersection points between the given ray and all geometries in this collection of geometries
     * @param ray the ray to intersect with the geometries
     * @return a list of intersection (point and geometry) if any are found; otherwise, {@code null}
     */
    protected abstract List<Intersection> calculateIntersectionsHelper(Ray ray);

    /** the same purpose of calculateIntersectionsHelper(), but not abstract for NVI design pattern
     * @param ray the ray to intersect with the geometries
     * @return a list of intersection (point and geometry) if any are found; otherwise, {@code null}*/
    final public List<Intersection> calculateIntersections(Ray ray) {
        return calculateIntersectionsHelper(ray);
    }

    /** A PDS for keeping a Point and the Geometry its on */
    public static class Intersection {
        public Geometry geometry;
        public Point point;

       // public final Material material = new Material();
        // not according to instruction!!!
        public final Material material;
        public Vector rayDirection;
        public Vector normal;
        public double directionDotN;
        public LightSource light;
        public Vector l;
        public double lDotN;


        /** A simple constructor
         * @param geometry
         * @param point */
        public Intersection(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;

            this.material = geometry != null ? geometry.getMaterial() : null;

        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return (obj instanceof Intersection other)
                    && this.geometry == other.geometry
                    && this.point.equals(other.point);
        }

        @Override
        public String toString() {
            return geometry.toString() + " intersection " + point.toString();
        }
    }
}