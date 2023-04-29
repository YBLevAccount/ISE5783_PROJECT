package geometries;

import java.util.List;

import primitives.*;

/**
 * 2D plane in a 3D world
 * 
 * @author Yonatan
 *
 */
public class Plane implements Geometry {

	private final Point p0;
	private final Vector normal;

	/**
	 * create a plane using 3 points on the plane
	 * 
	 * @param p1
	 * @param p2
	 * @param p3 points on the plane
	 */
	public Plane(Point p1, Point p2, Point p3) {
		this.p0 = p1;
		if (p1.equals(p2))
			throw new IllegalArgumentException("First and second points are equalS");
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p2);

		try {
			v1.crossProduct(v2);
		} catch (IllegalArgumentException e) {
			if (e.getMessage().equals("ZERO vector"))
				;
		}
		this.normal = (p2.subtract(p1).crossProduct(p3.subtract(p1))).normalize();
	}

	/**
	 * create plane using point in the plane and normal to the plane
	 * 
	 * @param p0 a point on the plane
	 * @param normal to the plane
	 */
	public Plane(Point p0, Vector normal) {
		this.p0 = p0;
		this.normal = normal.normalize();
	}

	/**
	 * getter for the normal to the Plane
	 * 
	 * @return the normal
	 */
	public Vector getNormal() {
		return normal;
	}

	/**
	 * getter for the point in the Plane
	 * 
	 * @return the point
	 */
	public Point getP0() {
		return p0;
	}

	@Override
	public Vector getNormal(Point point) {
		return normal;
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		return null;
	}
}
