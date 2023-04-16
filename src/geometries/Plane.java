package geometries;

import primitives.*;

/**
 * 2D plane in a 3D world
 * @author Yonatan
 *
 */
public class Plane implements Geometry {

	private final Point p0;
	private final Vector normal;
	
	/**
	 * create a plane using 3 points on the plane
	 * @param p1
	 * @param p2
	 * @param p3
	 * points on the plane
	 */
	public Plane(Point p1, Point p2, Point p3) {
		this.p0 = p1;
		this.normal = null;
		if (p1.equals(p2)) throws(IllegalArgumentException(""));
	}
	
	/**
	 * create plane using point in the plane and normal to the plane
	 * @param p0
	 * @param normal
	 */
	public Plane(Point p0, Vector normal) {
		this.p0 = p0;
		this.normal = normal.normalize();
	}

	/**
	 * getter for the normal to the Plane
	 * @return the normal
	 */
	public Vector getNormal() {
		return normal;
	}

	/**
	 * getter for the point in the Plane
	 * @return the point
	 */
	public Point getP0() {
		return p0;
	}
	
	@Override
	public Vector getNormal(Point point){
		return null;
	}
}
