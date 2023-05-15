package geometries;

import java.util.LinkedList;

import java.util.List;

import primitives.Ray;

/**
 * @author Shulman and Yonatan
 *
 */
public class Geometries extends Intersectable {

	private List<Intersectable> shapes;

	/**
	 * default constructor
	 */
	public Geometries() {
		shapes = new LinkedList<>();
	}

	/**
	 * construct geometries with starting shapes
	 * 
	 * @param geometries list of Intersectables the shape will contain
	 */
	public Geometries(Intersectable... geometries) {
		shapes = List.of(geometries);
	}

	/**
	 * add new Intersectables to the collection
	 * 
	 * @param geometries the new Intersectables
	 */
	public void add(Intersectable... geometries) {
		shapes.addAll(List.of(geometries));
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		LinkedList<GeoPoint> intersections = null;
		for (Intersectable shape : shapes) {
			List<GeoPoint> shapeIntersections = shape.findGeoIntersectionsHelper(ray);
			if (shapeIntersections != null) {
				if (intersections == null)
					intersections = new LinkedList<>();
				intersections.addAll(shapeIntersections);
			}
		}
		return intersections;
	}

}
