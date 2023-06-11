package geometries;

/**
 * this abstract class represents geometry with radius
 * @author Shulman and Yonatan
 *
 */
public abstract class RadialGeometry extends Geometry {

	/**
	 * the radius of the geometry
	 */
	protected final double radius;
	/**
	 * the squared radius of the geometry
	 */
	protected final double radius2;
	
	/**
	 * construct geometry with radius
	 * @param radius of the geometry
	 */
	public RadialGeometry(double radius) {
		this.radius = radius;
		this.radius2 = radius * radius;
	}
	
	/**
	 * getter for the radius
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
}
