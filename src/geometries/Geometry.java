package geometries;

import primitives.*;

/**
 * public interface for geometries with normal
 * @author Shulman and Yonatan
 *
 */
public abstract class Geometry extends Intersectable{
	/**
	 * emission light of the object
	 */
	protected Color emission = Color.BLACK;
	/**
	 * calculate normal vector the the geometry at given point
	 * @param point on the geometry
	 * @return the normal
	 */
	abstract public Vector getNormal(Point point);
	/**
	 * getter for the color of the emission light
	 * @return the color of the emission light
	 */
	public Color getEmission() {
		return emission;
	}
	/**
	 * setter for the color of the emission light
	 * @param emission the color of the emission light
	 * @return this object
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}
}
