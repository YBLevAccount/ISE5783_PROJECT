package primitives;

/**
 * This class represents a point in a 3D world
 * 
 * @author Shulman and Yonatan
 *
 */
public class Point {
	/**
	 * the 3 coordinates
	 */
	final Double3 xyz;

	/**
	 * create point using coordinates
	 * 
	 * @param x axis
	 * @param y axis
	 * @param z axis
	 */
	public Point(double x, double y, double z) {
		xyz = new Double3(x, y, z);
	}

	/**
	 * create point using Double3 class
	 * 
	 * @param xyz the instance
	 */
	Point(Double3 xyz) {
		this.xyz = xyz;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Point other)
			return this.xyz.equals(other.xyz);
		return false;
	}

	@Override
	public String toString() {
		return xyz.toString();
	}

	/**
	 * create new point by moving this point by vector
	 * 
	 * @param rhs the vector we add
	 * @return the moved point
	 */
	public Point add(Vector rhs) {
		return new Point(xyz.add(rhs.xyz));
	}

	/**
	 * create vector from the other point to our point
	 * 
	 * @param rhs the other point
	 * @return the vector
	 */
	public Vector subtract(Point rhs) {
		return new Vector(xyz.subtract(rhs.xyz));
	}

	/**
	 * calculate distance from the other point
	 * 
	 * @param other second point
	 * @return the distance
	 */
	public double distance(Point other) {
		return Math.sqrt(distanceSquared(other));
	}

	/**
	 * calculate distance times itself from the other point
	 * 
	 * @param other second point
	 * @return the distance squared
	 */
	public double distanceSquared(Point other) {
		// using Euclidean distance formula
		double result = this.xyz.d1 - other.xyz.d1;
		result *= result;
		double dif = this.xyz.d2 - other.xyz.d2;
		result += dif * dif;
		dif = this.xyz.d3 - other.xyz.d3;
		result += dif * dif;
		return result;
	}
	
	/**
	 * getter for x value
	 * @return x value
	 */
	public double getX() {
		return xyz.d1;
	}
	
	/**
	 * getter for y value
	 * @return y value
	 */
	public double getY() {
		return xyz.d2;
	}
	
	/**
	 * getter for z value
	 * @return z value
	 */
	public double getZ() {
		return xyz.d3;
	}
}
