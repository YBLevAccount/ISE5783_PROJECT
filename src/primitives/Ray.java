package primitives;

/**
 * This class represents a ray using starting point and direction vector
 * @author Yonatan
 *
 */
public class Ray {
	
	private final Point p0;
	private final Vector dir;
	
	/**
	 * create a ray that starts at p0 and goes toward dir
	 * @param p0 the starting point
	 * @param dir the direction the ray goes
	 */
	public Ray(Point p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalize();
	}

	/**
	 * getter for the starting point
	 * @return the point
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * getter for the direction vector
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
}
