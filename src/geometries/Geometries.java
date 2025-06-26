//Geometries.java
package geometries;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import primitives.*;
public class Geometries implements Intersectable {
    List<Intersectable> geometries = new LinkedList<Intersectable>();

    public Geometries() {

    }

    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(this.geometries, geometries);
    }

    /**
     * finds all the intersections of a ray with all the geometries in the list
     *
     * @param ray the ray that we want to check intersections with
     * @return a list of the intersections of ray and the geometries from the list
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        for (Intersectable geometry : geometries) {
            List<Point> temp_points = geometry.findIntersections(ray);
            if (temp_points != null && intersections == null)
                intersections = new LinkedList<>(temp_points);
            else if (temp_points != null)
                intersections.addAll(temp_points);
        }
        return intersections;
    }

}
