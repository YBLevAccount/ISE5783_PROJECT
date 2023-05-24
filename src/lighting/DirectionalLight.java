package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * light that have constant direction and don't have starting point
 * 
 * @author Yonatan and Shulman
 *
 */
public class DirectionalLight extends Light implements LightSource {
	private Vector direction; 
	
	/**
	 * construct Directional light using light color and direction of the light
	 * @param intensity the light color
	 * @param direction the direction of the light
	 */
	public DirectionalLight(Color intensity, Vector direction) {
		super(intensity);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		return super.getIntensity();
	}

	@Override
	public Vector getL(Point p) {
		return direction;
	}

}
