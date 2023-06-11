package geometries;

import java.util.List;

import primitives.*;

/**
 * shapes that are intersectable by a ray
 * 
 * @author Shulman and Yonatan
 * 
 */
public abstract class Intersectable {
	/**
	 * find all points in a geometry that intersect with a ray
	 * 
	 * @param ray to find intersections with
	 * @return list of intersection points
	 */
	public final List<Point> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

	/**
	 * finds the closest intersection point to a given ray
	 * 
	 * @param ray the given ray
	 * @return the point and its geometry, null if there is no such point
	 */
	public final GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> intersections = findGeoIntersections(ray);
		return intersections == null ? null : ray.findClosestGeoPoint(intersections);
	}

	/**
	 * find all GeoPoints that intersect with a ray
	 * 
	 * @param ray to find intersections with
	 * @return list of intersection points
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}

	/**
	 * find all GeoPoints that intersect with a ray while ignoring the points that
	 * are further than a given distance
	 * 
	 * @param ray         to find intersections with
	 * @param maxDistance the given distane
	 * @return list of intersection points
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		return findGeoIntersectionsHelper(ray, maxDistance);
	}

	/**
	 * helper for the findGeoIntersections function that finds all points in a
	 * geometry that intersect with a ray while ignoring the points that are further
	 * than a given distance
	 * 
	 * @param ray         to find intersections with
	 * @param maxDistance the given distance
	 * @return list of intersection points
	 */
	abstract protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

	/**
	 * this class represents a point and the geometry that contains the point
	 * 
	 * @author Shulman and Yonatan
	 *
	 */
	public static class GeoPoint {
		/**
		 * the geometry that contains the point
		 */
		public Geometry geometry;
		/**
		 * the point
		 */
		public Point point;

		/**
		 * constructs a GeoPoint with a point and the geometry that contains the point
		 * 
		 * @param geometry the geometry that contains the point/
		 * @param point    the point
		 */
		public GeoPoint(Geometry geometry, Point point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj instanceof GeoPoint other)
				return this.geometry == other.geometry && this.point.equals(other.point);
			return false;
		}

		@Override
		public String toString() {
			return geometry + " / " + point;
		}

	}

}
