package primitives;

/**
 * This class represents a point in a 3 dimensional world
 * 
 * @author יונתן
 *
 */
public class Point {
	/**
	 * the 3 coordinates
	 */
	 final Double3 xyz;
	
	/**
	 * create point using coordinates
	 * @param x x axis
	 * @param y y axis
	 * @param z z axis
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
	 * move a point by vector
	 * @param rhs the diraction we add
	 * @return
	 */
	public Point add(Vector rhs) {
		return new Point(xyz.add(rhs.xyz));
	}

	/**
	 * move a point by the opposite of a vector 
	 * @param rhs the diraction we subtract
	 * @return
	 */
	public Point subtract(Vector rhs) {
		return new Point(xyz.subtract(rhs.xyz));
	}

	/**
	 * calculate distance from the other point:
	 * sqrt((x1-x2)^2+(y1-y2)^2+(z1-z2)^2)
	 * @param other second point
	 * @return
	 */
	public double distance(Point other){
		return Math.sqrt(distanceSquared(other));
	}
	
	/**
	 * calculate distance times itself from the other point:
	 * (x1-x2)^2+(y1-y2)^2+(z1-z2)^2
	 * @param other second point
	 * @return
	 */
	public double distanceSquared(Point other) {
		return (this.xyz.d1 - other.xyz.d1)* (this.xyz.d1 - other.xyz.d1)
		+ (this.xyz.d2 - other.xyz.d2) * (this.xyz.d2 - other.xyz.d2)
		+ (this.xyz.d3 - other.xyz.d3) * (this.xyz.d3 - other.xyz.d3);
	}
}
