package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * represents a collection of geometrical shapes that implement the {@link Intersectable} interface
 * which allows grouping multiple geometries and querying intersections collectively
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> data = new LinkedList<>();
    /**
     * default constructor initializing an empty collection
     */
    public Geometries() { }
    /**
     * constructs a collection containing the given geometries
     *
     * @param geometries one or more geometries to add to the collection
     */
    public Geometries (Intersectable... geometries) {
        add(geometries);
    }

    /**
     * adds one or more geometries to this collection of geometries
     * @param geometries one or more geometries to add
     */
    public void add(Intersectable... geometries) {
        data.addAll(Arrays.asList(geometries));
    }
    @Override
    public List<Intersection> calculateIntersectionsHelper(Ray ray){
        List<Intersection> totalList = null;
        for (Intersectable geometry : data) {
            var list = geometry.calculateIntersectionsHelper(ray);
            if (list != null)
                if (totalList == null)
                    totalList = new LinkedList<>(list);
                else
                    totalList.addAll(list);
        }
        return totalList;
    }
}
