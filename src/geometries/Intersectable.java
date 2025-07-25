
package geometries;
import lighting.LightSource;
import primitives.Vector;
import primitives.Point;
import primitives.Material;
import primitives.Ray;
import java.util.List;
import java.util.Objects;

/**
 * The Intersectable interface declares a method for finding intersections between a Ray and a geometric object.
 */
public abstract class Intersectable  {
    /**
     * a class to represent a geometry point
     */
    public static class Intersection {
            public final Geometry geometry;
            public final Point point;

            public final Material material;
            public Vector normal;
            public Vector v1;
            public Vector v2;
            public double Dot_Product;
            public LightSource light;
            public Vector v3;
            public double Dot_Product_light;                                                    ;

        /**
         * a constructor for geoPoint
         * @param geometry the geometry
         * @param point the point
         */
        public Intersection(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
            if (geometry != null) {
                this.material = geometry.getMaterial();
            } else {
                this.material = null;
            }
        }


            @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Intersection geoPoint = (Intersection) obj;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "Intersection{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
    /**
     * A method that receives a ray and return a list of intersection points between the ray and the current geometry.
     * @param ray a ray that is thrown to the geometry.
     * @return a list of all the intersection points between 'ray' and the geometry.
     */
    public final List<Point> findIntersections(Ray ray) {
        var list = calculateIntersections(ray);
        return list == null ? null : list.stream().map(intersection -> intersection.point).toList();
    }

    /**
     * A method that receives a ray and return a list of intersection GeoPoints between the ray and the current geometry.
     * @param ray a ray that is thrown to the geometry.
     * @return a list of all the intersection GeoPoints between 'ray' and the geometry.
     */
    protected List<Intersection> calculateIntersectionsHelper(Ray ray){return null;}

    /**
     * Helper method for findGeoIntersections.
     * @param ray a ray that is thrown to the geometry.
     * @return a list of all the intersection GeoPoints between 'ray' and the geometry.
     */
    public List<Intersection> calculateIntersections(Ray ray) {
        return calculateIntersectionsHelper(ray);
    }
}