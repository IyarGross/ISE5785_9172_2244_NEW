//vector.java
package primitives;

/**
 * This class will serve most primitive classes by representing a vector
 */
public class Vector extends Point {
    // Unit vectors in X, Y, and Z directions
    public static final Vector AXIS_X = new Vector(1, 0, 0);
    public static final Vector AXIS_Y = new Vector(0, 1, 0);
    public static final Vector AXIS_Z = new Vector(0, 0, 1);
    /**
     * Constructor to initialize vector based object with its three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        //Vector temp = new Vector(Double3.ZERO);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector can't be zero");
    }

    /**
     * Constructor to initialize vector based object with a point
     *
     * @param xyz the point for the initialization
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (this.xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector can't be zero");
    }

    /**
     * calculate the length Squared of ש vector
     *
     * @return result the calculation of the length Squared
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;

    }

    /**
     * calculate the length of the vector
     *
     * @return result the calculation of the length
     */
    public double length() {
        return Math.sqrt(lengthSquared());

    }

    /**
     * calculate the dot product of the two vectors
     *
     * @param v the second vector
     * @return result the calculation of the dot product-int
     */
    public double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3;
    }

    /**
     * calculate the cross product of the two vectors
     *
     * @param v the second vector
     * @return A new vector perpendicular to the two existing vectors
     */
    public Vector crossProduct(Vector v) {
        return new Vector(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2, xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3, xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1);
    }

    /**
     * calculate the normalization of the vector
     *
     * @return the vector after normalization
     */
    public Vector normalize() {
        return this.scale((1 / this.length()));
    }

    /**
     * calculate the vector multiply the scalar
     *
     * @param scalar
     * @return the vector multiply scalar
     */
    public Vector scale(double scalar) {
        return new Vector(xyz.scale(scalar));
    }

    /**
     * adds two vectors
     *
     * @param v the second vector
     * @return the vectors sum
     */
    public Vector add(Vector v) {
        if (this.equals(v.scale(-1)))
            throw new IllegalArgumentException("You cant do vector+-itself");
        return new Vector(xyz.add(v.xyz));
    }

    @Override
    public boolean equals(Object obj) {
        if ((this == obj)) return true;
        return ((obj instanceof Vector other)
                && super.equals(other));
    }

    @Override
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                "} " + super.toString();
    }
    /**
     * checks if the current vector is parallel to the parameter vector
     * @param v1 the vector that is checked
     * @return true if the current vector is parallel to the parameter vector, false if it isn't
     */
    public Boolean isParallel(Vector v1) {
        Vector n1 = normalize();
        Vector n2 = v1.normalize();
        return n1.equals(n2) || n1.equals(n2.scale(-1));
    }

}