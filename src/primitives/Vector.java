package primitives;

/** 
 * the class represents a vector in a 3D world
 * @author Yonatan
 *
 */
public class Vector extends Point {

	/** create vector using coordinates
	 * @param x axis
	 * @param y axis
	 * @param z axis
	 * @throws IllegalArgumentException if the vector is zero vector
	 */
	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (Util.isZero(x) && Util.isZero(y) && Util.isZero(z))
			throw new IllegalArgumentException("ZERO vector");
	}

	/** create vector using Double3
	 * @param xyz the instance
	 * @throws IllegalArgumentException if the vector is zero vector
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
	 * add 2 vectors
	 * @param rhs second vector
	 * @return the result vector
	 */
	public Vector add(Vector rhs) {
		return new Vector(xyz.add(rhs.xyz));
	}
	
	/**
	 * scale every coordinate by the value
	 * @param value
	 * @return the scaled vector
	 */
	public Vector scale(double value) {
		return new Vector(xyz.scale(value));
	}
	
	/**
	 * return the cross product between 2 vectors
	 * @param rhs second vector
	 * @return orthogonal vector
	 */
	public Vector crossProduct(Vector rhs) {
		// using cross product formula
		return new Vector(xyz.d2 * rhs.xyz.d3 - xyz.d3 * rhs.xyz.d2, 
				xyz.d3 * rhs.xyz.d1 - xyz.d1 * rhs.xyz.d3, 
				xyz.d1 * rhs.xyz.d2 - xyz.d2 * rhs.xyz.d1);
	}
	
	/**
	 * return the dot product between 2 vectors
	 * @param rhs second vector
	 * @return the scalar
	 */
	public double dotProduct(Vector rhs) {
		// using dot product formula
		return xyz.d1 * rhs.xyz.d1 + xyz.d2 * rhs.xyz.d2 + xyz.d3 * rhs.xyz.d3;
	}
	
	/**
	 * calculate the vector length times itself
	 * @return the length squared
	 */
	public double lengthSquared() {
		return dotProduct(this);
	}
	
	/**
	 * calculate the vector length
	 * @return the length
	 */
	public double length() {
		return Math.sqrt(lengthSquared());
	}

	/**
	 * calculate vector with the same direction but with length 1
	 * @return the normalized vector 
	 */
	public Vector normalize() {
		return scale(1 / length());
	}
}
