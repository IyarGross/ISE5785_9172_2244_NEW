package renderer;


import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * This class represents a simple ray tracer
 *
 * @author Yahel Sayedoff
 * @author Hodaya Guma
 */

public class SimpleRayTracer extends RayTracerBase {

    /**
     * constructor for scene
     *
     * @param scene the scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        Point point = ray.findClosestPoint(scene.geometries.findIntersections(ray));
        if (point == null)
            return scene.background;
        return calcColor(point);
    }

    /**
     * this function calculates color of a point
     *
     * @param point the point
     * @return the color
     */
    private Color calcColor(Point point) {
        return scene.ambientLight.getIntensity();
    }
}
