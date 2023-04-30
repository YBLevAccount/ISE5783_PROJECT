package geometries;

import java.util.List;

import primitives.*;

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
		Point rayP0 = ray.getP0();
		Vector v1 = vertices.get(0).subtract(rayP0);
		Vector v2 = vertices.get(1).subtract(rayP0);
		Vector v3 = vertices.get(2).subtract(rayP0);
		Vector rayVec = ray.getDir();
		double t1 = Util.alignZero(rayVec.dotProduct(v1.crossProduct(v2)));
		double t2 = Util.alignZero(rayVec.dotProduct(v2.crossProduct(v3)));
		double t3 = Util.alignZero(rayVec.dotProduct(v3.crossProduct(v1)));
		if (Util.checkSign(t1, t2) && Util.checkSign(t2, t3))
			return plane.findIntersections(ray);
		return null;
	}

}
