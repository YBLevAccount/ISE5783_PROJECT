package geometries;

import primitives.*;

/** 
 * Cylinder is Tube with finite height
 * @author Yonatan
 *
 */
public class Cylinder extends Tube {

	private final double height;
	
	/** construct Cylinder using height, axis ray and radius
	 * @param height
	 * @param axisRay
	 * @param radius
	 */
	public Cylinder(double height, Ray axisRay, double radius) {
		super(axisRay, radius);
		this.height = height;
	}
	
	/**
	 * getter for height
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
