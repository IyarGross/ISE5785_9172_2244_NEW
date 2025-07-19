package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    private final Point position;
    private double kC = 1, kL = 0, kQ = 0;

    public PointLight(Color intensity, Point position){
        super(intensity);
        this.position = position;
    }
    public PointLight setKC(double kC){
        this.kC = kC;
        return this;
    }
    public PointLight setKl(double kL){
        this.kL = kL;
        return this;
    }
    public PointLight setKq(double kQ){
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        return intensity.reduce(kC + kL*d + kQ*d*d);
    }
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

}