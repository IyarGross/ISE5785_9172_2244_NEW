package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * a class that represent the scene
 */
public class Scene {

    //the name of the scene
    public String name;

    //the background of the scene with default color black
    public Color background = Color.BLACK;

    //the ambient light of the scene
    public AmbientLight ambientLight = AmbientLight.NONE;

    //the geometries in the scene
    public Geometries geometries = new Geometries();

    public Scene(String name) {
        this.name = name;
    }

    /**
     * sets th geometries of the scene
     *
     * @param geometries the geometries in the scene
     * @return the scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;

    }

    /**
     * sets th background of the scene
     *
     * @param background the color of the background of the scene
     * @return the scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;

    }


    /**
     * sets th ambientLight of the scene
     *
     * @param ambientLight the color of the ambientLight of the scene
     * @return the scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
}
