package primitives;

import static java.lang.Math.sqrt;

/**
 * Point class represents a point in the 3D Cartesian coordinate system.
 */
public class Point {
    public static Point ZERO = new Point(Double3.ZERO);
    /**
     * The coordinates of the point, represented by a Double3 object.
     */
    final protected Double3 xyz;

    /**
     * Constructs a point using 3D Cartesian coordinates.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Point(double x, double y, double z) {

        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a point using a Double3 object.
     *
     * @param xyz the Double3 object representing the coordinates of the point
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Subtracts another point from this point.
     *
     * @param p the point to subtract
     * @return the resulting vector from this point to the given point
     */
    final public Vector subtract(Point p) {
        return new Vector(this.xyz.subtract(p.xyz));
    }

    /**
     * Adds a vector to this point.
     *
     * @param v the vector to add
     * @return a new point that is the result of the addition
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * Computes the squared distance between this point and another point.
     *
     * @param p1 the other point
     * @return the squared distance between the two points
     */
    final public double distanceSquared(Point p1) {
        return (this.xyz.d1() - p1.xyz.d1()) * (this.xyz.d1() - p1.xyz.d1()) + (this.xyz.d2() - p1.xyz.d2()) * (this.xyz.d2() - p1.xyz.d2()) + (this.xyz.d3() - p1.xyz.d3()) * (this.xyz.d3() - p1.xyz.d3());
    }

    /**
     * Computes the distance between this point and another point.
     *
     * @param p1 the other point
     * @return the distance between the two points
     */
    public double distance(Point p1) {
        return sqrt(distanceSquared(p1));
    }

    /**
     * Checks whether this point is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the object is a point with the same coordinates, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return Util.isZero(this.xyz.d1() - other.xyz.d1()) &&
                Util.isZero(this.xyz.d2() - other.xyz.d2()) &&
                Util.isZero(this.xyz.d3() - other.xyz.d3());
    }


    /**
     * Returns a string representation of the point.
     *
     * @return a string describing the point
     */
    @Override
    public String toString() {
        return xyz.toString();
    }


    public Double3 getXYZ() {
        return xyz;
    }
    public boolean equalsWithEpsilon(Point other, double epsilon) {
        if (other == null) return false;
        return Math.abs(this.xyz.d1() - other.xyz.d1()) < epsilon
                && Math.abs(this.xyz.d2() - other.xyz.d2()) < epsilon
                && Math.abs(this.xyz.d3() - other.xyz.d3()) < epsilon;
    }

}
