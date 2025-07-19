package lighting;

import primitives.Color;

/**
 * base class for all light sources in the scene
 * provides common functionality for different types of lights
 */
public abstract class Light {
    /**
     * the intensity of the light represented as a color
     */
    protected final Color intensity;

    /**
     * creates a new light with given intensity
     * @param intensity the color intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * gets the intensity of the light
     * @return the light intensity as a color
     */
    public Color getIntensity() {
        return intensity;
    }
}