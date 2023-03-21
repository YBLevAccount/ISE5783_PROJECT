package geometries;

import primitives.Point;

/** Triangle is polygon with 3 sides
 * @author יונתן
 *
 */
public class Triangle extends Polygon {

	/** create polygon with exactly 3 sides,
	 * @param p 3 vertices that create the triangle
	 * 
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}

}
