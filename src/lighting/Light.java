package lighting;

import primitives.*;

/**
 * the base class for light objects
 * 
 * @author Yonatan and Shulman
 *
 */
abstract class Light {
	/**
	 * the source color of the light 
	 */
	protected final Color intensity;

	/**
	 * construct Light with intensity color
	 * 
	 * @param intensity the intensity color
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
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
