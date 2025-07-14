//Geometry.java
package geometries;

import primitives.*;

import java.util.List;

/**
 * The Geometry interface represents a geometric shape in 3D space.
 * Now it extends the Intersectable interface.
 */
public abstract class Geometry extends Intersectable {
    /** color of the geometry */
    protected Color emission= Color.BLACK;
    /** a set method for the emission
     *
     * @param emission the emission I want to add
     * @return the geometry
     */

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
    /** a get method for emission
     *
     * @return the emission
     */
    public Color getEmission() {
        return emission;
    }


    /**
     * Computes and returns the normal vector to the geometry at a given point.
     *
     * @param point the point at which to compute the normal vector.
     * @return the normal vector to the geometry at the given point.
     */
    public abstract Vector getNormal(Point point);
    /** the material of the geometry */
    private Material material = new Material();
    /**
     * setter method for geometry's material
     * @param material material of the geometry
     * @return the geometry object itself
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * getter method for geometry's material
     * @return the geometry's material
     */
    public Material getMaterial() {
        return material;
    }

}