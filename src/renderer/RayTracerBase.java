package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * abstract base class for ray tracing functionality
 * this class serves as the foundation for all ray tracers that compute the color
 * of a given ray as it intersects with the scene it holds a reference to the
 * scene being rendered and defines the abstract method for tracing rays
 */
public abstract class RayTracerBase {
    /**
     * the scene containing geometries lighting and background for rendering
     */
    protected final Scene scene;

    /**
     * constructs a ray tracer with the given scene
     * @param scene the scene to be used for ray tracing
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * traces the given ray and computes its resulting color based on the scene's elements
     * @param ray the ray to be traced
     * @return the computed color for the given ray
     */
    public abstract Color traceRay(Ray ray);
}
