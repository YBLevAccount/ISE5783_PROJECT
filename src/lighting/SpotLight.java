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
		return super.getIntensity().scale(dotProduct > 0 ? dotProduct : 0);
	}

	@Override
	public Vector getL(Point p) {
		return super.getL(p);
	}

	/**
	 * 
	 * @param angle
	 * @return
	 */
	public SpotLight setNarrowBeam(double angle) {
		throw new UnsupportedOperationException("not implemented yet");
	}
}
