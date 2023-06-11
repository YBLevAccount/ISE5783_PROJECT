package geometries;

import java.util.List;

import primitives.*;

/**
 * this class represents a sphere
 * 
 * @author Shulman and Yonatan
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
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		Point p0 = ray.getP0();
		if (center.equals(p0))
			return List.of(new GeoPoint(this, ray.getPoint(radius)));

		Vector u = center.subtract(p0);
		double tm = u.dotProduct(ray.getDir());
		double d2 = u.lengthSquared() - tm * tm;
		double th2 = Util.alignZero(radius2 - d2);
		if (th2 <= 0)
			return null;

		double th = Math.sqrt(th2);
		double t2 = Util.alignZero(tm + th);
		double t1 = Util.alignZero(tm - th);
		if (t2 <= 0 || Util.alignZero(t1 - maxDistance) >= 0)
			return null;

		if (Util.alignZero(t2 - maxDistance) >= 0)
			return t1 > 0 ? List.of(new GeoPoint(this, ray.getPoint(t1))) : null;

		return t1 > 0 //
				? List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2))) //
				: List.of(new GeoPoint(this, ray.getPoint(t2)));
	}
}
