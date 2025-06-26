package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;
/**
 * a class to represents Spot-Light
 */
public class SpotLight extends PointLight{

    //the direction of the light
    private Vector direction;
    private double NarrowBeam=1;

    /**
     * a constructor that gets all the parameters
     *
     * @param color the intensity of the light
     * @param position  the point the light come from
     * @param direction the direction of the light
     */
    public SpotLight(Color color, Point position, Vector direction) {
        super(color, position);
        this.direction = direction.normalize();
    }

    /**
     * sets the kC
     *
     * @param kC
     * @return the spotlight
     */
    public SpotLight setkC(double kC) {
        super.setkC(kC);
        return this;
    }

    /**
     * sets the kL
     * @param kL
     * @return the spotlight
     */
    public SpotLight setkL(double kL) {
        super.setkL(kL);
        return this;
    }

    /**
     * sets the kQ
     * @param kQ
     * @return the spotlight
     */
    public SpotLight setkQ(double kQ) {
        super.setkQ(kQ);
        return this;
    }

    /**
     * sets the NarrowBeam
     * @param narrowBeam the narowness of the light
     * @return the spotlight
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.NarrowBeam=narrowBeam;
        return this;
    }

    /**
     * a method to get the intensity of the light
     *
     * @param point the point I want to get the color of
     * @return the intensity of the light
     */
    @Override
    public Color getIntensity(Point point) {
        double cos = alignZero(direction.dotProduct(getL(point)));
        return NarrowBeam != 1
                ? super.getIntensity(point).scale(Math.pow(Math.max(0, direction.dotProduct(getL(point))), NarrowBeam))
                : super.getIntensity(point).scale(Math.max(0, direction.dotProduct(getL(point))));
    }
}
