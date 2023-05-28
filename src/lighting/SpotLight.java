package lighting;

import primitives.*;

/**
 * light that starts at given point and is strong in one direction
 * 
 * @author Yonatan and Shulman
 *
 */
public class SpotLight extends PointLight {
	private Vector direction;
	private double sinMaxAngle = 0;

	/**
	 * construct SpotLight using color, position, and direction vector
	 * 
	 * @param intensity the light color
	 * @param position  the light position
	 * @param direction the direction vector
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		double dotProduct = Util.alignZero(direction.dotProduct(getL(p)));
		return super.getIntensity().scale(dotProduct > sinMaxAngle ? dotProduct : 0);
	}

	@Override
	public Vector getL(Point p) {
		return super.getL(p);
	}

	/**
	 * narrows the beam of the ray
	 * 
	 * @param angle the angle to limit the ray
	 * @return this object
	 */
	public SpotLight setNarrowBeam(double angle) {
		sinMaxAngle = Math.sin(angle * Math.PI / 180d);
		return this;
	}
}
