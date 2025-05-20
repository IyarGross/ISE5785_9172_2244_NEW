package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite design pattern that represents a collection of geometrical shapes (Intersectables).
 * Allows finding intersections with all contained shapes using a single method call.
 */
public class Geometries implements Intersectable {
    // List holding all the geometrical shapes in the collection
    private List<Intersectable> geometriesList;
    /**
     * Default constructor, creates an empty collection of geometries
     */
    public Geometries() {
        geometriesList = new ArrayList<Intersectable>();
    }
    /**
     * Constructor that initializes the collection with one or more geometries
     *
     * @param geometries one or more geometries to add to the collection
     */
    public Geometries(Intersectable... geometries) {
        geometriesList = new ArrayList<>(List.of(geometries));
    }
    /**
     * Adds one or more geometries to the collection
     *
     * @param geometries geometries to be added
     */
    public void add(Intersectable... geometries) {
        geometriesList.addAll(List.of(geometries));
    }
    /**
     * Implements {@link Intersectable#findIntersections(Ray)}.
     * Finds all intersection points between a ray and all geometries in the collection.
     *
     * @param ray the ray to intersect with the geometries
     * @return a list of all intersection points found, or {@code null} if no intersections were found
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        // List to collect all intersection points
        List<Point> result = new ArrayList<>();
        // Iterate over each geometry in the collection
        for (Intersectable geometry : geometriesList) {
            // Find intersections of the current geometry with the ray
            List<Point> intersections = geometry.findIntersections(ray);
            // Find intersections of the current geometry with the ray
            if (intersections != null) {
                result.addAll(intersections);
            }
        }
        // Return null if no intersections were found
        return result.isEmpty() ? null : result;
    }

    }
