package lighting;

import primitives.Color;

/**
 * This class represents ambient light
 *
 * @author Yahel Sayedoff
 * @author Hodaya Guama
 */
public class AmbientLight {
    private static final double KA = 0.1;

    private final Color intensity;

    /**
     * black ambient light (no light)
     */
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, 0d);

    /**
     * constructor for the intensity
     *
     * @param IA original intensity of the lighting
     */
    public AmbientLight(Color IA) {
        intensity = IA.scale(KA);
    }

    /**
     * constructor for the intensity
     *
     * @param IA original intensity of the lighting
     * @param KA attenuation coefficient of the lighting
     */
    public AmbientLight(Color IA, double KA) {
        intensity = IA.scale(KA);
    }

    /**
     * getter for intensity
     *
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
