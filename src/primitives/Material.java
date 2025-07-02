package primitives;
/**
 * Material class represents a material of a geometric body or shape in Cartesian 3-Dimensional coordinate system.
 */
public class Material {
    /** the attenuation coefficient of the diffusion of the light on the material */
    public Double3 kD = Double3.ZERO;
    /** the attenuation coefficient of the specularity of the light on the material */
    public Double3 kS = Double3.ZERO;
    /** the material's shininess */
    public int nShininess = 0;

   /** public Material setkA(Double3 kA) {
        this.kA = kA;
        return this;
    }

    public Material setkA(double kA) {
        this.kA = new Double3(kA);
        return this;
    }
    public Double3 kA = Double3.ONE;
    /**
     * setter method for kD that receives a Double3
     * @param kD the attenuation coefficient of the diffusion
     * @return the material object
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter method for kD that receives a double
     * @param kD the attenuation coefficient of the diffusion
     * @return the material object
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * setter method for kS that receives a Double3
     * @param kS the attenuation coefficient of the specularity
     * @return the material object
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }


    /**
     * setter method for kS that receives a double
     * @param kS the attenuation coefficient of the specularity
     * @return the material object
     */
    public Material setkS(Double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * setter method for nShininess
     * @param nShininess the material's shininess
     * @return the material object
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
