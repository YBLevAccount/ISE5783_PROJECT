package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * @author Yonatan
 *
 */
public class Geometries implements Intersectable {

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
	public List<Point> findIntersections(Ray ray) {
		LinkedList<Point> intersections = null;
		for (Intersectable shape : shapes) {
			List<Point> shapeIntersections = shape.findIntersections(ray);
			if (shapeIntersections != null) {
				if (intersections == null)
					intersections = new LinkedList<>();
				intersections.addAll(shapeIntersections);
			}
		}
		return intersections;
	}

}
