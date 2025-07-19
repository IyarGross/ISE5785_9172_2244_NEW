package geometries;

/**
 * abstract base class for geometries with a radius parameter
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * the radius of the geometry
     */
    protected final double radius;

    /**
     * creates a new radial geometry with given radius
     * @param radius the radius value must be positive
     * @throws IllegalArgumentException if radius is not positive
     */
    RadialGeometry(double radius) {
        if(radius <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.radius = radius;
    }
}