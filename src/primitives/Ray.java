package primitives;

import java.util.List;

/**
 * This class represents a ray using starting point and direction vector
 * 
 * @author Shulman and Yonatan
 *
 */
public class Ray {

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
		if (Util.isZero(t))
			return p0;
		return p0.add(dir.scale(t));
	}

	/**
	 * finds the closest point to the head point of the ray
	 * 
	 * @param points list of points to check
	 * @return the closest point
	 */
	public Point findClosestPoint(List<Point> points) {
		if (points == null || points.size() == 0)
			return null;
		Point closestPoint = points.get(0);
		double minDistance = p0.distanceSquared(closestPoint);
		for (Point point : points) {
			double currentDistance = p0.distanceSquared(point);
			if (currentDistance < minDistance) {
				minDistance = currentDistance;
				closestPoint = point;
			}
		}
		return closestPoint;
	}
}
