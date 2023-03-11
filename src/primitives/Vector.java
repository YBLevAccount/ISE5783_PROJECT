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
	}

	/** create vector using Double3
	 * @param xyz the instance
	 */
	Vector(Double3 xyz) {
		super(xyz);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Vector other)
			return super.equals(obj);
		return false;
	}

	
	
}
