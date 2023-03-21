package geometries;

import primitives.*;

/**
 * @author יונתן
 *
 */
public class Cylinder extends Tube {

	private double height;
	
	/**
	 * @param axisRay
	 * @param radius
	 */
	public Cylinder(double height, Ray axisRay, double radius) {
		super(axisRay, radius);
		this.height = height;
	}
	
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	
	@Override
	public Vector getNormal(Point point) {
		return null;
	}
}
