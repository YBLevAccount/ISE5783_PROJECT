package geometries;

import primitives.*;

/** 
 * represents a tube in 3D world using ray and radius that goes around the ray
 * @author Yonatan
 *
 */
public class Tube extends RadialGeometry {

	/**
	 * each point on the ray is the center of a circle with the given radius
	 * together they make the tube
	 */
	protected final Ray axisRay;
	
	/** construct Tube using axis ray and radius
	 * @param axisRay
	 * @param radius
	 */
	public Tube(Ray axisRay, double radius) {
		super(radius);
		this.axisRay = axisRay;
	}
	
	/**
	 * getter for the axis ray
	 * @return the axis ray
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	@Override
	public Vector getNormal(Point point) {
		return null;
	}

}
