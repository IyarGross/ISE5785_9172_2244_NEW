package geometries;
import primitives.*;
import renderer.Camera;

/**
 * abstract class representing a geometric object in 3D space
 * all geometries in the scene should inherit from this class and implement
 * the method to compute the normal vector at a given point on the geometry's surface
 */
public abstract class Geometry extends Intersectable {

    protected Color emission = Color.BLACK;
    private Material material = new Material();
    /**
     * a get function for emission
     * @return Color emission
     * */
    public Color getEmission() {
        return emission;
    }
    /**
     * a set function foe emission
     * @param emission
     * @return Geometry - for easier use...
     * */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }



    /**
     * a set function foe material
     * @param material
     * @return Geometry - for easier use...
     * */
    public Geometry setMaterial(Material material){
        this.material = material;
        return this;
    }
    /**
     * a get function for material
     * @return Material material
     * */
    public Material getMaterial() {
        return material;
    }


    /**
     * calculates and returns the normal vector to the geometry at the given point
     * the normal vector is a vector perpendicular to the surface at the specified point
     * @param point
     * @return the normal vector to the geometry at the specified point
     */
    public abstract Vector getNormal(Point point);
}