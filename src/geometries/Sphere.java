package geometries;

import java.util.List;

import primitives.*;

/**
 * this class represents a sphere
 * 
 * @author Yonatan
 *
 */
public class Sphere extends RadialGeometry {

	private final Point center;

	/**
	 * create a sphere using center point and radius
	 * 
	 * @param center
	 * @param radius
	 */
	public Sphere(Point center, double radius) {
		super(radius);
		this.center = center;
	}

	/**
	 * getter for the center point
	 * 
	 * @return the point
	 */
	public Point getCenter() {
		return center;
	}

	@Override
	public Vector getNormal(Point point) {
		return (point.subtract(center).normalize());
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		return null;
	}
}
