package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * represents ambient light in a scene
 * ambient light is a constant background light that affects all objects equally
 */
public class AmbientLight extends Light {
    /**
     * constant representing no ambient light black color
     */
    public static final AmbientLight NONE = new AmbientLight(new Color(java.awt.Color.BLACK));

    /**
     * creates a new ambient light with given color intensity
     * @param Ia the ambient light intensity
     */
    public AmbientLight(Color Ia) {
        super(Ia);
    }
}