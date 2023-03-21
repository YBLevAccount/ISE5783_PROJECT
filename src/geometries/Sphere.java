package geometries;

import primitives.*;

/** this class represents a sphere
 * @author יונתן
 *
 */
public class Sphere extends RadialGeometry {

	private Point center;
	
	/** create a sphere using center point and radius
	 * @param center
	 * @param radius
	 */
	public Sphere(Point center,double radius) {
		super(radius);
		this.center = center;
	}

	/**
	 * @return the center point
	 */
	public Point getCenter() {
		return center;
	}

	@Override
	public Vector getNormal(Point point) {
		return null;
	}
}
