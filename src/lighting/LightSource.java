package lighting;

import primitives.*;

/**
 * interface to represent a light source
 */
public interface LightSource {
    /**
     * a method to get the intensity of a color in the point
     *
     * @param point the point I want to get the color of
     * @return the color of the point
     */
    public Color getIntensity(Point point);

    /**
     * a method to get the direction of the light in the point
     *
     * @param point the point I want to get the color of
     * @return the direction of the light in point
     */
    public Vector getL(Point point);

    /**
     *
     * @param point
     * @return
     */
    /*double getDistance(Point point);*/
}
