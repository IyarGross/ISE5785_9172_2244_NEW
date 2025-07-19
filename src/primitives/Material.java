package primitives;

/**
 * represents material properties of geometric objects
 * defines coefficients for ambient diffuse and specular lighting
 */
public class Material {
    /**
     * ambient light reflection coefficient
     */
    public Double3 kA = Double3.ONE;

    /**
     * specular light reflection coefficient
     */
    public Double3 kS = Double3.ZERO;

    /**
     * diffuse light reflection coefficient
     */
    public Double3 kD = Double3.ZERO;

    /**
     * shininess factor for specular reflection
     */
    public int nSh = 0;

    /**
     * sets the ambient reflection coefficient using a double3
     * @param kA ambient reflection coefficient
     * @return this material for chaining
     */
    public Material setkA(Double3 kA) {
        this.kA = kA;
        return this;
    }

    /**
     * sets the ambient reflection coefficient using a single value
     * @param ka ambient reflection coefficient
     * @return this material for chaining
     */
    public Material setkA(double ka) {
        this.kA = new Double3(ka);
        return this;
    }

    /**
     * sets the specular reflection coefficient using a double3
     * @param kS specular reflection coefficient
     * @return this material for chaining
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * sets the specular reflection coefficient using a single value
     * @param kS specular reflection coefficient
     * @return this material for chaining
     */
    public Material setKS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * sets the diffuse reflection coefficient using a double3
     * @param kD diffuse reflection coefficient
     * @return this material for chaining
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * sets the diffuse reflection coefficient using a single value
     * @param kD diffuse reflection coefficient
     * @return this material for chaining
     */
    public Material setKD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * sets the shininess factor for specular reflections
     * @param nSh shininess coefficient
     * @return this material for chaining
     */
    public Material setShininess(int nSh) {
        this.nSh = nSh;
        return this;
    }
}