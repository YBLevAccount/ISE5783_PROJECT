package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

/**
 * This class represents a ray using starting point and direction vector
 * 
 * @author Shulman and Yonatan
 *
 */
public class Ray {
	private static final double DELTA = 0.1;
	private final Point p0;
	private final Vector dir;

	/**
	 * create a ray that starts at p0 and goes toward dir
	 * 
	 * @param p0  the starting point
	 * @param dir the direction the ray goes
	 */
	public Ray(Point p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalize();
	}

	/**
	 * create a ray that starts at p0 in a direction and move it due to noraml
	 * @param normal to move by
	 * @param point the starting point before moving
	 * @param direction the direction of the ray
	 */
	public Ray(Vector normal, Point point, Vector direction) {
		this.dir = direction.normalize();
		double dotProduct = alignZero(this.dir.dotProduct(normal));
		this.p0 = dotProduct == 0 ? point : point.add(normal.scale(dotProduct < 0 ? -DELTA : DELTA));
	}

	/**
	 * getter for the starting point
	 * 
	 * @return the point
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * getter for the direction vector
	 * 
	 * @return the vector
	 */
	public Vector getDir() {
		return dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Ray other)
			return this.p0.equals(other.p0) && this.dir.equals(other.dir);
		return false;
	}

	@Override
	public String toString() {
		return p0.toString() + dir;
	}

	/**
	 * find point on the ray using distance from starting point
	 * 
	 * @param t distance from starting point
	 * @return the point
	 */
	public Point getPoint(double t) {
		return isZero(t) ? p0 : p0.add(dir.scale(t));
	}

	/**
	 * finds the closest point to the head point of the ray
	 * 
	 * @param points list of points to check
	 * @return the closest point
	 */
	public Point findClosestPoint(List<Point> points) {
		return points == null || points.isEmpty() ? null
				: findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}

	/**
	 * finds the closest geoPoint to a given geoPoint
	 * 
	 * @param geoPoints a list of geoPoints
	 * @return the closest geoPoint
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
		if (geoPoints == null)
			return null;
		GeoPoint closestGeoPoint = null;
		double minDistance = Double.POSITIVE_INFINITY;
		for (GeoPoint geoPoint : geoPoints) {
			double currentDistance = p0.distanceSquared(geoPoint.point);
			if (currentDistance < minDistance) {
				minDistance = currentDistance;
				closestGeoPoint = geoPoint;
			}
		}
		return closestGeoPoint;
	}
}
