package primitives;
import static java.lang.Math.sqrt;

/**
 * Point class represents a point at the 3D Cartesian coordinate
 */
public class Point {
    public static  Point ZERO= new Point(0,0,0);
    /**
     * Point will be represented by double3 type
     */
    final protected Double3 xyz;
    /**
     * Constructs of a point by 3D Cartesian coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Point(double x, double y, double z) {

        this.xyz = new Double3(x,y,z);
    }

    /**
     * Constructs of a Point by a Double3
     * @param xyz the Double3 that will be the new point
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Subtracts a point from a point
     * @param p point to be reduced
     * @return Subtraction of a point from a point
     */
    final public Vector subtract(Point p) {
        return new Vector(this.xyz.subtract(p.xyz));
    }

    /**
     * Adds Vector to a point
     * @param v Vector
     * @return new point
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     *
     * @param p1
     * point p xyz- point p1 xyz
     * (p.x-p1.x)(p.x-p1.x)->(p.x-p1.x)^2
     * (p.x-p1.x)^2+(p.y-p1.y)^2+(p.z-p1.z)^2
     * @return
     */
    final public double distanceSquared(Point p1) {
        return (this.xyz.d1() - p1.xyz.d1())*(this.xyz.d1() - p1.xyz.d1())+(this.xyz.d2() - p1.xyz.d2())*(this.xyz.d2() - p1.xyz.d2())+(this.xyz.d3() - p1.xyz.d3())*(this.xyz.d3() - p1.xyz.d3());
    }
    /**
     * Calculates the distance between two points
     * @param p1 point to calculate distance with
     * @return distance between two points
     */
    public double distance(Point p1) {
        return sqrt(distanceSquared(p1));
    }

    /**
     *
     * @param obj
     * @return bool
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point)) {
            return false;
        }
        return xyz.equals(((Point) obj).xyz);
    }
    @Override
    public String toString() {
        return xyz.toString();
    }
}
