package geometries;

import primitives.Point;

/** Triangle is polygon with 3 sides
 * @author Yonatan
 *
 */
public class Triangle extends Polygon {

	/** 
	 * create polygon with exactly 3 sides,
	 * @param p1
	 * @param p2
	 * @param p3
	 * the vertices that create the triangle
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}

}