package primitives;

import static primitives.Util.isZero;

/**
 * Class which represents a vector which starts at the origin point.  The point where the vector ends is kept in Vector which derives from Point.
 */
public class Vector extends Point {
    /**
     * constant vector pointing in the positive x direction
     */
    public static final Vector AXIS_X = new Vector(1, 0 ,0);

    /**
     * constant vector pointing in the positive y direction
     */
    public static final Vector AXIS_Y = new Vector(0, 1 ,0);

    /**
     * constant vector pointing in the positive z direction
     */
    public static final Vector AXIS_Z = new Vector(0, 0 ,1);

    /**
     * creates a new vector from x y z coordinates
     * @param x coordinate on x axis
     * @param y coordinate on y axis
     * @param z coordinate on z axis
     * @throws IllegalArgumentException if the vector is zero
     */
    public Vector (double x, double y, double z) {
        super(x, y, z);
        if(isZero(x) && isZero(y) && isZero(z))
            throw new IllegalArgumentException("vector 0 is not allowed");
    }

    /**
     * creates a new vector from double3 coordinates
     * @param xyz the coordinates for the vector
     * @throws IllegalArgumentException if the vector is zero
     */
    public Vector (Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("vector 0 is not allowed");
    }

    /**
     * calculates the squared length of this vector
     * @return the squared length value
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * calculates the length of this vector
     * @return the length value
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * adds another vector to this vector
     * @param vector the vector to add
     * @return a new vector representing the sum
     */
    public Vector add(Vector vector) {
        return new Vector(this.xyz.add(vector.xyz));
    }

    /**
     * multiplies this vector by a scalar value
     * @param scalar the multiplication factor
     * @return a new scaled vector
     */
    public Vector scale(double scalar) {
        return new Vector(this.xyz.scale(scalar));
    }

    /**
     * calculates the dot product with another vector
     * @param vector the other vector
     * @return the dot product value
     */
    public double dotProduct(Vector vector) {
        return this.xyz.d1() * vector.xyz.d1() +
                this.xyz.d2() * vector.xyz.d2() +
                this.xyz.d3() * vector.xyz.d3();
    }

    /**
     * calculates the cross product with another vector
     * @param vector the other vector
     * @return a new vector representing the cross product
     */
    public Vector crossProduct(Vector vector) {
        return new Vector(xyz.d2() * vector.xyz.d3() - xyz.d3() * vector.xyz.d2(),
                xyz.d3() * vector.xyz.d1() - xyz.d1() * vector.xyz.d3(),
                xyz.d1() * vector.xyz.d2() - xyz.d2() * vector.xyz.d1());
    }

    /**
     * normalizes this vector to a unit vector
     * @return a new normalized vector
     */
    public Vector normalize() {
        return this.scale(1/this.length());
    }
    @Override
    public String toString()    {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Vector other)
                && super.equals(other); //in this case super is a Point, and because other derives from Point super can take other as a parameter
    }
}
