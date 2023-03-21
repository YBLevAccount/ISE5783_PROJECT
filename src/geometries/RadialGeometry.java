package geometries;

/** this abstract class represents geometry with radius
 * @author יונתן
 *
 */
public abstract class RadialGeometry implements Geometry {

	/**
	 * the radius of the geometry
	 */
	protected double radius;
	
	/**
	 * the constructor
	 * @param radius of the geometry
	 */
	public RadialGeometry(double radius) {
		this.radius = radius;
	}
	
	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
}
