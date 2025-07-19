package renderer;

/**
 * defines the available types of ray tracers in the system
 * used to specify which ray tracing algorithm to use
 */
public enum RayTracerType {
    /**
     * basic ray tracer with no optimizations
     */
    SIMPLE,

    /**
     * ray tracer using regular grid for optimization
     */
    GRID
}