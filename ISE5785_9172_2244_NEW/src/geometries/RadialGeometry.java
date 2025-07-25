package geometries;

/**
 * RadialGeometry class represents any radial geometry by saving its radius
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * Radius represents the distance between the middle and the edge in a radial geometry
     */
    protected double radius;

    /**
     * Constructor of the abstract radial geometry class
     * Represents any radial geometry by saving its radius
     * @param radius the radial shape radius
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}