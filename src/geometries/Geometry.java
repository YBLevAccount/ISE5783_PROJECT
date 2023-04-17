package geometries;

import primitives.*;

/**
 * public interface for geometries with normal
 * @author Yonatan
 *
 */
public interface Geometry extends Intersectable{
	/**
	 * calculate normal vector the the geometry at given point
	 * @param point on the geometry
	 * @return the normal
	 */
	public Vector getNormal(Point point);
}
