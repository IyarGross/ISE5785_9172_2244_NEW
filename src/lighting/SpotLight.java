package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * represents a spot light source which is a point light with direction
 * the light intensity decreases with distance and angle from direction
 */
public class SpotLight extends PointLight implements LightSource {
    /**
     * the direction vector of the spot light
     */
    private final Vector direction;
    private int narrowBeam = 1;

    /**
     * creates a new spot light with given parameters
     * @param intensity the color intensity of the light
     * @param position the position of the light source
     * @param direction the direction the light is pointing
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * sets the constant attenuation factor
     * @param kC the constant attenuation factor
     * @return this spot light for chaining
     */
    @Override
    public SpotLight setKC(double kC) {
        return (SpotLight) super.setKC(kC);
    }

    /**
     * sets the linear attenuation factor
     * @param kL the linear attenuation factor
     * @return this spot light for chaining
     */
    @Override
    public SpotLight setKl(double kL) {
        return (SpotLight) super.setKl(kL);
    }

    /**
     * sets the quadratic attenuation factor
     * @param kQ the quadratic attenuation factor
     * @return this spot light for chaining
     */
    @Override
    public SpotLight setKq(double kQ) {
        return (SpotLight) super.setKq(kQ);
    }

    public SpotLight setNarrowBeam(int nBeam) {
        this.narrowBeam = nBeam;
        return this;
    }

    /**
     * gets the direction vector to a point
     * @param p the point to calculate direction to
     * @return the normalized direction vector
     */
    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }

    /**
     * calculates light intensity at a specific point
     * @param p the point to calculate intensity at
     * @return the color intensity at the point
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(getL(p))));
    }
}