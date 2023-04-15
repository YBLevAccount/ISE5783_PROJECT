package primitives;

/**
 * This class represents a point in a 3D world
 * @author Yonatan
 *
 */
public class Point {
	/**
	 * the 3 coordinates
	 */
	 final Double3 xyz;
	
	/**
	 * create point using coordinates
	 * @param x axis
	 * @param y axis
	 * @param z axis
	 */
	public Point(double x, double y, double z) {
		xyz = new Double3(x, y, z);
	}
	
	/**
	 * create point using Double3 class
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
	 * @param rhs the vector we add
	 * @return the moved point
	 */
	public Point add(Vector rhs) {
		return new Point(xyz.add(rhs.xyz));
	}

	/**
	 * create vector from the other point to our point
	 * @param rhs the other point
	 * @return the vector
	 */
	public Vector subtract(Point rhs) {
		return new Vector(xyz.subtract(rhs.xyz));
	}

	/**
	 * calculate distance from the other point
	 * @param other second point
	 * @return the distance
	 */
	public double distance(Point other){
		return Math.sqrt(distanceSquared(other));
	}
	
	/**
	 * calculate distance times itself from the other point
	 * @param other second point
	 * @return the distance squared
	 */
	public double distanceSquared(Point other) {
		// using Euclidean distance formula
		return (this.xyz.d1 - other.xyz.d1)* (this.xyz.d1 - other.xyz.d1)
		+ (this.xyz.d2 - other.xyz.d2) * (this.xyz.d2 - other.xyz.d2)
		+ (this.xyz.d3 - other.xyz.d3) * (this.xyz.d3 - other.xyz.d3);
	}
}
