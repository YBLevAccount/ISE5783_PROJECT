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
	 * @param height the distance between the upper and lower parts
 	 * @param axisRay a ray that the cylinder will go around
	 * @param radius for each circle that starts from each point on the ray
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
		double rSquared = radius * radius;
		Point bottomCenter = axisRay.getP0();
		// checks if the point is on the lowest circle using 
		if (point.distanceSquared(bottomCenter) < rSquared)
			return axisRay.getDir().scale(-1.d);
		// checks if the point is on the highest circle
		// to get the top center we add the direction vector with size height to the bottom 
		if (point.distanceSquared(bottomCenter.add((axisRay.getDir()).scale(height))) < rSquared)
			return axisRay.getDir();
		// the last case is just the same as tube
		return super.getNormal(point);
	}
}
