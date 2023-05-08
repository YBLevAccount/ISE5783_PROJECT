/**
 * 
 */
package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * this class represents and ambient light for the scene
 * 
 * @author Shulman and Yonatan
 *
 */
public class AmbientLight {
	private final Color intensity;
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

	/**
	 * construct ambient light using a color and an attenuation vector
	 * 
	 * @param iA the color
	 * @param kA the attenuation vector
	 */
	public AmbientLight(Color iA, Double3 kA) {
		intensity = iA.scale(kA);
	}

	/**
	 * construct ambient light using a color and an attenuation scalar
	 * 
	 * @param iA the color
	 * @param kA the attenuation scalar
	 */
	public AmbientLight(Color iA, double kA) {
		intensity = iA.scale(kA);
	}

	/**
	 * getter for the intensity
	 * 
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

}
