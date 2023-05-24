package primitives;

/**
 * represents a material with attenuation coefficients for light in Phong model
 * 
 * @author Yonatan and Shulman
 *
 */
public class Material {
	/**
	 * the coefficient for diffusive part
	 */
	public Double3 kD = Double3.ZERO;
	/**
	 * the coefficient for specular part
	 * 
	 * @return this object
	 */
	public Double3 kS = Double3.ZERO;
	/**
	 * the shininess power in Phong model
	 * 
	 * @return this object
	 */
	public int nShininess = 0;

	/**
	 * setter for kD using Double3
	 * 
	 * @param kD the attenuation in Double3 format
	 * @return this object
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * setter for kD using scalar
	 * 
	 * @param kD he scalar attenuation coefficient
	 * @return this object
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * setter for kS using Double3
	 * 
	 * @param kS the kS to set
	 * @return this object
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * setter for kS using scalar
	 * 
	 * @param kS the scalar attenuation coefficient
	 * @return this object
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * @param nShininess the nShininess to set
	 * @return this object
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
