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
	 * @param center the center point of the sphere
	 * @param radius of the sphere
	 */
	public Sphere(Point center, double radius) {
		super(radius);
		this.center = center;
	}

	/**
	 * getter for the center point
	 * 
	 * @return the center point
	 */
	public Point getCenter() {
		return center;
	}

	@Override
	public Vector getNormal(Point point) {
		return point.subtract(center).normalize();
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		Point p0 = ray.getP0();
		if (center.equals(p0))
			return List.of(ray.getPoint(radius));
		Vector u = center.subtract(p0);
		double tm = u.dotProduct(ray.getDir());
		double d = Math.sqrt(u.lengthSquared() - tm * tm);
		double dif = Util.alignZero(d - radius);
		if (dif >= 0)
			return null;
		double th = Math.sqrt(radius * radius - d * d);
		double t2 = Util.alignZero(tm + th);
		if (t2 <= 0)
			return null;
		double t1 = Util.alignZero(tm - th);
		return t1 > 0 //
				? List.of(ray.getPoint(t1), ray.getPoint(t2)) //
				: List.of(ray.getPoint(t2));
	}
}
