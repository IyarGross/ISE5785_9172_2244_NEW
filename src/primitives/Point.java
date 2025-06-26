//point.java
package primitives;

/**
 * This class will serve all primitive classes by representing a point
 */
public class Point {

    /**
     * a static zero point for calculations
     */
    public static final Point ZERO = new Point(Double3.ZERO);

    /**
     * parameter to represent the point
     */
    final Double3 xyz;

    /**
     * Constructor to initialize Double3 based object with its three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructor to initialize point based object with its Double3
     *
     * @param xyz for the three values of the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    public Double3 getXYZ() {
        return xyz;
    }

    /**
     * Subtract two points
     *
     * @param p right hand side operand for subtraction
     * @return result of subtraction
     */
    public Vector subtract(Point p) {
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * Add a vector to the point
     *
     * @param v right hand side operand for addition
     * @return result of add a point
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
    }

    /**
     * calculate the distance squared between two points
     *
     * @param p the point I want to find the distance squared from
     * @return result the calculation of the distance squared
     */
    public double distanceSquared(Point p) {
        return
                (this.xyz.d1 - p.xyz.d1) * (this.xyz.d1 - p.xyz.d1) +
                        (this.xyz.d2 - p.xyz.d2) * (this.xyz.d2 - p.xyz.d2) +
                        (this.xyz.d3 - p.xyz.d3) * (this.xyz.d3 - p.xyz.d3);
    }

    /**
     * calculate the distance between two points
     *
     * @param p the point I want to find the distance from
     * @return result the calculation of the distance
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && xyz.equals(other.xyz);

    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    public boolean isOnSameLine(Point other) {
        double t = xyz.d1 / other.xyz.d1;
        return xyz.d2 * t == other.xyz.d2 && xyz.d3 * t == other.xyz.d3;
    }
}
