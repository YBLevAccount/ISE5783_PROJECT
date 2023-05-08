package geometries;

import java.util.List;

import primitives.*;

/**
 * shapes that are intersectable by a ray
 * @author Shulman and Yonatan
 *  
 */
public interface Intersectable {
	/**
	 * find all points in a geometry that intersect with a ray
	 * @param ray to find intersections with
	 * @return list of intersection points
	 */
	public List<Point> findIntersections(Ray ray);
}
