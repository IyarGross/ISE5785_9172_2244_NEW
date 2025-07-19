package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a directional light source, like the sun, where light rays are parallel
 * and the intensity doesn't diminish with distance.
 */
public class DirectionalLight extends Light implements LightSource {

    /**
     * The normalized direction vector in which the light rays travel
     */
    private final Vector direction;

    /**
     * Constructs a directional light with specified intensity and direction
     *
     * @param intensity the color intensity of the light
     * @param direction the direction vector of the light rays (will be normalized)
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Gets the light intensity at a point
     * For directional light, intensity is constant regardless of position
     *
     * @param p the point at which to calculate the light intensity
     * @return the color intensity of the light at the point
     */
    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    /**
     * Gets the direction vector of light rays at a point
     * For directional light, direction is constant regardless of position
     *
     * @param p the point at which to get the light direction
     * @return the normalized direction vector of the light rays
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}