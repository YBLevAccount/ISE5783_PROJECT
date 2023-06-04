package lighting;

import primitives.*;

/**
 * the functionality light objects should have
 * 
 * @author Yonatan and Shulman
 *
 */
public interface LightSource {
	/**
	 * calculate the intensity of the light at given point
	 * 
	 * @param p given point
	 * @return the intensity at the point
	 */
	public Color getIntensity(Point p);

	/**
	 * calculate the normalized vector from the light source to given point
	 * 
	 * @param p the point
	 * @return the normalized vector
	 */
	public Vector getL(Point p);

	/**
	 * calculates the distance between the light source and a given point
	 * 
	 * @param p the given point
	 * @return the distance
	 */
	public double getDistance(Point p);

}
