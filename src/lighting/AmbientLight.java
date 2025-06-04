package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * a class that represents the ambient light
 */
public class AmbientLight {

    public final static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    private final Color intensity;

    /**
     * constructor with Double3 coefficient
     *
     * @param iA the base color
     * @param kI the attenuation factor
     */
    public AmbientLight(Color iA, Double3 kI) {
        intensity = iA.scale(kI);
    }

    /**
     * constructor with double coefficient
     *
     * @param iA the base color
     * @param kI the attenuation factor
     */
    public AmbientLight(Color iA, double kI) {
        intensity = iA.scale(kI);
    }

    public Color getIntensity() {
        return intensity;
    }
}
