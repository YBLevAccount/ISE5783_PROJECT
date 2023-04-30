package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;

/** Triangle is polygon with 3 sides
 * @author Yonatan
 *
 */
public class Triangle extends Polygon {

	/** 
	 * create polygon with exactly 3 sides,
	 * @param p1 1st vertex of the triangle
	 * @param p2 2nd vertex of the triangle
	 * @param p3 3rd vertex of the triangle
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}
	
	@Override
	public List<Point> findIntersections(Ray ray){
		return null;
	}

}
