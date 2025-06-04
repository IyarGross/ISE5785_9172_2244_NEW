package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * an  abstract class for the basic ray in the scene
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * a constructor for ray tracer base
     *
     * @param scene the scene of the photo
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * receives a ray in the parameter and returns a color
     *
     * @param ray the ray
     * @return the color of the point the ray gets to
     */
    public abstract Color traceRay(Ray ray);

}
