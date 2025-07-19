package scene;

import lighting.LightSource;
import primitives.Color;
import lighting.AmbientLight;
import geometries.Geometries;

import java.util.LinkedList;
import java.util.List;

/**
 * represents a 3d scene containing all necessary components for rendering
 * includes background color ambient light geometries and light sources
 */
public class Scene {
    /**
     * name of the scene
     */
    public String name;

    /**
     * background color of the scene defaults to black
     */
    public Color background = new Color(java.awt.Color.BLACK);

    /**
     * ambient light in the scene defaults to none
     */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /**
     * collection of geometries in the scene
     */
    public Geometries geometries = new Geometries();

    /**
     * list of light sources in the scene
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * creates a new scene with the given name
     * @param name the name for the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * sets the background color of the scene
     * @param background the color to set
     * @return the scene object for chaining
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * sets the ambient light of the scene
     * @param ambientLight the ambient light to set
     * @return the scene object for chaining
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * sets the geometries of the scene
     * @param geometries the geometries to set
     * @return the scene object for chaining
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * sets the light sources of the scene
     * @param lights the list of light sources to set
     * @return the scene object for chaining
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}