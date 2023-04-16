package geometries;

import primitives.*;

/**
 * Cylinder is Tube with finite height
 * 
 * @author Yonatan
 *
 */
public class Cylinder extends Tube {

	private final double height;

	/**
	 * construct Cylinder using height, axis ray and radius
	 * 
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
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	@Override
	public Vector getNormal(Point point) {
		Point pBottom = axisRay.getP0();
		Point pTop = axisRay.getP0().add((axisRay.getDir()).normalize().scale(height));
		if (point.equals(pBottom))
			return (axisRay.getDir().scale(-1));
		if (Util.isZero(point.subtract(pBottom).dotProduct(axisRay.getDir())))
			return (axisRay.getDir().scale(-1));
		if (point.equals(pTop))
			return (axisRay.getDir());
		if (Util.isZero(point.subtract(pTop).dotProduct(axisRay.getDir())))
			return (axisRay.getDir());
		return super.getNormal(point);
	}
}
