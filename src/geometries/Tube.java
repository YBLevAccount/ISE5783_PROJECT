package geometries;

import primitives.*;

/** represents a tube in 3D world using ray and radius that goes around the ray
 * @author יונתן
 *
 */
public class Tube extends RadialGeometry {

	/**
	 * each point on the ray is the center of a circle with the given radius
	 * together they make the tube
	 */
	protected Ray axisRay;
	
	/** the constructor
	 * @param axisRay the center ray
	 * @param radius the radius of each circle
	 */
	public Tube(Ray axisRay, double radius) {
		super(radius);
		this.axisRay = axisRay;
	}
	
	/**
	 * @return the axisRay
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	@Override
	public Vector getNormal(Point point) {
		return null;
	}

}
