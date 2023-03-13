package geometries;

import primitives.*;

/**
 * public interface for geometries with normal
 * @author יונתן
 *
 */
public interface Geometry {
	/**
	 * @return the normal vector the the geometry
	 */
	public Vector getNormal();
}
