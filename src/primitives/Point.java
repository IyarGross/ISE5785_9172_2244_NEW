package primitives;

import static primitives.Util.isZero;

/**
 * Class which represents a point
 */
public class Point  {
    /**
     * the coordinates of the point stored as a double3
     */
    protected final Double3 xyz;

    /**
     * constant representing the origin point (0,0,0)
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * creates a new point with given coordinates
     * @param x coordinate on x axis
     * @param y coordinate on y axis
     * @param z coordinate on z axis
     */
    public Point(double x, double y,double z) {
        this.xyz = new Double3(x,y,z);
    }

    /**
     * creates a new point from double3 coordinates
     * @param xyz the coordinates for the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * subtracts another point from this point
     * @param point the point to subtract
     * @return a vector from the other point to this point
     */
    public Vector subtract(Point point) {
        return new Vector(this.xyz.subtract(point.xyz));
    }

    /**
     * adds a vector to this point
     * @param point the vector to add
     * @return a new point after addition
     */
    public Point add(Point point) {
        return new Point(this.xyz.add(point.xyz));
    }

    /**
     * calculates squared distance to another point
     * @param point the other point
     * @return the squared distance value
     */
    public double distanceSquared(Point point) {
        Double3 differenceOfCoordinates = new Double3(
                this.xyz.d1() - point.xyz.d1(),
                this.xyz.d2() - point.xyz.d2(),
                this.xyz.d3() - point.xyz.d3()
        );
        Double3 squareOfCoordinates = differenceOfCoordinates.product(differenceOfCoordinates);
        return squareOfCoordinates.d1() + squareOfCoordinates.d2() + squareOfCoordinates.d3();
    }

    /**
     * calculates distance to another point
     * @param point the other point
     * @return the distance value
     */
    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }

    @Override
    public String toString()    {
        return xyz.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other)
                && this.xyz.equals(other.xyz);
    }

}