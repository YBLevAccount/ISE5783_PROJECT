package geometries;

import primitives.*;

/**
 * 2D plane in a 3D world
 * @author יונתן
 *
 */
public class Plane implements Geometry {

	private Point p0;
	private Vector normal;
	
	/**
	 * create a plane using 3 points on the plane
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public Plane(Point p1, Point p2, Point p3) {
		this.p0 = p1;
		this.normal = null;
	}
	
	/**
	 * create plane using point in the plane and normal to the plane
	 * @param p0 point in the plane
	 * @param normal to the plane
	 */
	public Plane(Point p0, Vector normal) {
		this.p0 = p0;
		this.normal = normal.normalize();
	}

	/**
	 * @return the normal
	 */
	public Vector getNormal() {
		return normal;
	}

	/**
	 * @return return a point in the plane
	 */
	public Point getP0() {
		return p0;
	}
	
	@Override
	public Vector getNormal(Point point){
		return null;
	}
}
