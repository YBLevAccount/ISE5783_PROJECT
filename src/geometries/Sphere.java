package geometries;

import primitives.*;

/** this class represents a sphere
 * @author יונתן
 *
 */
public class Sphere extends RadialGeometry {

	private Point p0;
	
	/** create a sphere using center point and radius
	 * @param p0 the center point
	 * @param radius
	 */
	public Sphere(Point p0,double radius) {
		super(radius);
		this.p0 = p0;
	}

	@Override
	public Vector getNormal() {
		return null;
	}

	/**
	 * @return the center point
	 */
	public Point getP0() {
		return p0;
	}

}
