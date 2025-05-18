package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/*
 * composite design pattern, allow to use the method findIntersection on a forms collection
 * */
public class Geometries implements Intersectable {
    private List<Intersectable> geometriesList;

    public Geometries() {
        geometriesList = new ArrayList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        geometriesList = List.of(geometries);
    }

    public void add(Intersectable... geometries) {
        geometriesList.addAll(List.of(geometries));
    }
    public List<Point> findIntersections(Ray ray){
    return null;
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return List.of();
    }
}