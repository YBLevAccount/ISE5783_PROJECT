package primitives;

/**
 * This class represents a ray using starting point and direction vector
 * 
 * @author יונתן
 *
 */
public class Ray {
	
	private Point p0;
	private Vector dir;
	
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
	 * @return the starting point
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * @return the direction vector
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
		return p0.toString() + " " + dir.toString();
	}
	
	
}
