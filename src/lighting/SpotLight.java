package lighting;

import primitives.*;
import static primitives.Util.*;

/**
 * light that starts at given point and is strong in one direction
 * 
 * @author Yonatan and Shulman
 *
 */
public class SpotLight extends PointLight {
	private final Vector direction;
	private int narrowness = 1;

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
		double dotProduct = alignZero(direction.dotProduct(getL(p)));
		return dotProduct <= 0 ? Color.BLACK : super.getIntensity(p).scale(Math.pow(dotProduct, narrowness));
	}

	/**
	 * narrows the beam of the ray
	 * 
	 * @param narrowness the narrowness of the beam
	 * @return this object
	 */
	public SpotLight setNarrowBeam(int narrowness) {
		this.narrowness = narrowness;
		return this;
	}
}
