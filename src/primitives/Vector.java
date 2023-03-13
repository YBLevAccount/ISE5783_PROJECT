package primitives;

/** the class represents a vector in a 3D world
 * @author יונתן
 *
 */
public class Vector extends Point {

	/** create vector using coordinates
	 * @param x x axis
	 * @param y y axis
	 * @param z z axis
	 */
	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z))
			throw new IllegalArgumentException("ZERO vector");
	}

	/** create vector using Double3
	 * @param xyz the instance
	 */
	Vector(Double3 xyz) {
		super(xyz);
		if (xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("ZERO vector");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Vector other)
			return super.equals(obj);
		return false;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	/**
	 * add 2 vectors using linear algebra
	 * @param rhs the second vector
	 */
	public Vector add(Vector rhs) {
		return new Vector(xyz.add(rhs.xyz));
	}
	
	/**
	 * scale every coordinate by the value
	 * @param value
	 * @return
	 */
	public Vector scale(double value) {
		return new Vector(xyz.scale(value));
	}
	
	/**
	 * return the cross product between 2 vectors:
	 * (y1 * z2 - z1 * y2, z1 * x2 - z2 * x1, x1 * y2 - x2 * y1)
	 * @param rhs
	 * @return orthogonal vector
	 */
	public Vector crossProduct(Vector rhs) {
		
		return new Vector(xyz.d2 * rhs.xyz.d3 - xyz.d3 * rhs.xyz.d2, 
				xyz.d3 * rhs.xyz.d1 - xyz.d1 * rhs.xyz.d3, 
				xyz.d1 * rhs.xyz.d2 - xyz.d2 * rhs.xyz.d1);
	}
	
	/**
	 * return the dot product between 2 vectors
	 * (x1 * x2 + y1 * y2 + z1 * z1)
	 * @param rhs second vector
	 * @return a scalar
	 */
	public double dotProduct(Vector rhs) {
		return xyz.d1 * rhs.xyz.d1 + xyz.d2 * rhs.xyz.d2 + xyz.d3 * rhs.xyz.d3;
	}
	
	/**
	 * calculate the vector length times itself
	 */
	public double lengthSquared() {
		return dotProduct(this);
	}
	
	/**
	 * calculate the vector length
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	/**
	 * @return vector with the same direction but with length 1
	 */
	public Vector normalize() {
		return scale(1 / length());
	}
}
