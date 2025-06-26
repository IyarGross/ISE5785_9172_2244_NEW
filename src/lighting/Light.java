package lighting;

import primitives.Color;

/**
 * an abstract class to represents all the lights
 */
abstract class Light {
    //the intensity of the light
    protected Color intensity;

    /**
     * a constructor to Light
     *
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * gets the intensity of the ambient light
     *
     * @return the intensity of the light
     */
    public Color getIntensity() {

        return intensity;
    }
}
