package primitives;
import static java.lang.Math.sqrt;
/**
 * Vector construction by three points in the 3D Cartesian coordinate
 *
 */
public class Vector extends Point{
    /**
     * Vector construction by three points in the 3D Cartesian coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Vector(double x, double y, double z) {
        super(x,y,z);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector can not be accepted");
    }

    /**
     * Vector constructions by the Double3 constructor
     * @param xyz point of the end of the vector while the start is at zero point
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO)) throw new IllegalArgumentException("Zero Vector can not be tolerate");
    }
    /**
     * Vector addition
     * @param v vector to add
     * @return new vector of sum of vectors
     */
    final public Vector add(Vector v)
    {
        return new Vector(this.xyz.add(v.xyz));
    }
    /**
     *
     * @param m multiplier
     * @return new scaled vector
     */
    final public Vector scale (double m)
    {
        return new Vector(this.xyz.scale(m));
    }
    /**
     * Calculates dot product of two vectors
     * @param v vector to multiply with
     * @return the dot product of two vectors
     */
    final public double dotProduct (Vector v){
        return this.xyz.d1()*v.xyz.d1()+this.xyz.d2() * v.xyz.d2()+this.xyz.d3() *v.xyz.d3();
    }

    /**
     * Calculates cross product of two vectors
     * @param v vector to multiply with
     * @return the cross product of two vectors
     */
    final public Vector crossProduct (Vector v) {
        return new Vector(this.xyz.d2()*v.xyz.d3() - this.xyz.d3()*v.xyz.d2(),this.xyz.d3()*v.xyz.d1()-this.xyz.d1()*v.xyz.d3(),this.xyz.d1()*v.xyz.d2()-this.xyz.d2()*v.xyz.d1());
        //calculate the cross product by this formula
    }
    /**
     * Calculates the squared length of the vector
     * @return squared length of the vector
     */
    final  public double lengthSquared (){
        return this.xyz.d1()*this.xyz.d1()+ this.xyz.d3()*this.xyz.d3()+this.xyz.d2()*this.xyz.d2();
    }
    /**
     * Calculates the length of the vector
     * @return length of vector
     */
    final  public double length() {
        return sqrt(lengthSquared());
    }

    /**
     * Calculates the normalization of the vector
     * @return the normalized vector
     */
    final public Vector normalize ()
    {
        return new Vector (this.xyz.reduce(this.length()));
    }

    @Override
    final public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector other)) return false;//if the type isnt vector
        return super.equals((Point) obj);
    }
}
