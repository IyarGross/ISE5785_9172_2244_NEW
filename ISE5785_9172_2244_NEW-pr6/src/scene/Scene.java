package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
import lighting.LightSource;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a scene
 *
 * @author Yahel Sayedoff
 * @author Hodaya Guama
 */
public class Scene {
    /** the name of the scene */
    public String name;
    /** the background color of the scene */
    public Color background = new Color(java.awt.Color.BLACK);
    /** the ambient light of the scene */
    public AmbientLight ambientLight = AmbientLight.NONE;
    /** a collection of the geometries in the scene */
    public Geometries geometries = new Geometries();
    /** a list of the lights sources of the scene */
    public List<LightSource> lights = new LinkedList<>();

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * constructor for scene's name
     *
     * @param name the name
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * setter for scene's background color
     *
     * @param background the color
     * @return this
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter for scene's ambient lighting
     *
     * @param ambientLight the light
     * @return this
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setter for scene's geometries set
     *
     * @param geometries the geometries
     * @return this
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
    /**
     * a setter function for the light sources of the scene
     * @param lights a list of the lights sources of the scene
     * @return the Scene object
     */
}