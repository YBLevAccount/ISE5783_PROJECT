package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;

/**
 * testing Plane
 * @author Yonatan
 *
 */
class PlaneTests {
	/**
	 * tests {@link geometries.Plane#Plane(Point,Point,Point)}
	 */
	void testConstructorThreePoints() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the constructor creates the correct normal
		Plane p = new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,1));
		assertEquals(new Vector(1,1,1).normalize(), p.getNormal());
		
		// ================= BVA Tests ===========================
		// TC02: tests that the correct exception is thrown when the first two points
		// are equal
		assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(3, 4, 5), new Point(3, 4, 5), new Point(6, 2, 1)));
		// TC03: tests that the correct exception is thrown when all the points
		// on the same line
		assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(3, 4, 5), new Point(6, 8, 10), new Point(12, 16, 20)));
	}

	/**
	 * tests {@link geometries.Plane#getNormal(Point)}
	 */
	@Test
	void testGetNormal() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that getNormal(Point) is working properly
		Plane p = new Plane(new Point(1, 1, 1), new Point(2, 2, 2), new Point(-3, 4, -3));
		assertEquals(new Vector(-1, 0, 1).normalize(), p.getNormal(new Point(1, 0, 1)));
	}
	
	/**
	 * tests {@link geometries.Plane#findIntersections(Ray)}
	 */
	@Test
	void testFindIntersection() {
		Plane plane = new Plane(new Point(1, 0, 0), new Point(-1, 0, 0), new Point(0, 1, 0));
		// ================= Equivalence Partitions Tests ===========================
		// TC01: ray intersects with the plane, not orthogonal (1 point)
		List<Point> result = plane.findIntersections(new Ray(new Point(2, 2, 2), new Vector(-1, -2, -4)));
		assertEquals(1, result.size(), "wrong number of intersections");
		assertEquals(new Point(1.5, 1, 0), result.get(0), "wrong point found");
		//TC02: ray does not intersects with the plane, not parallel (0 points)
		result = plane.findIntersections(new Ray(new Point(2, 4, 24), new Vector(13, -2, 1)));
		assertEquals(0, result.size(), "wrong number of intersections");
		// ================= BVA Tests ===========================
		// **** Group: ray is parallel to the plane (0 points)
		// TC03: ray included in the plane
		result = plane.findIntersections(new Ray(new Point(0, 0.25d, 0), new Vector(1, -2, 0)));
		assertEquals(0, result.size(), "wrong number of intersections");
		// TC04: ray does not  intersect with the plane
		result = plane.findIntersections(new Ray(new Point(7, 4, 240), new Vector(1, -3, 0)));
		assertEquals(0, result.size(), "wrong number of intersections");
		// **** Group: ray is orthogonal to the plane
		// TC05: ray starts before the plane (1 point)
		Vector planeNormal = new Vector(0, 0, 1);
		result = plane.findIntersections(new Ray(new Point(-1, -2, -2), planeNormal));
		assertEquals(1, result.size(), "wrong number of intersections");
		assertEquals(new Point(-1, -2, 0), result.get(0), "wrong point found");
		// TC06: ray starts at the plane (0 points)
		result = plane.findIntersections(new Ray(new Point(1, 0, 0), planeNormal));
		assertEquals(0, result.size(), "wrong number of intersections");
		// TC07: ray starts after the plane (0 points)
		result = plane.findIntersections(new Ray(new Point(-1, -2, 13), planeNormal));
		assertEquals(0, result.size(), "wrong number of intersections");
		// **** Group: the other tests
		// TC08: ray starts at the plane but not on P0 (0 points)
		result = plane.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 3, 4)));
		assertEquals(0, result.size(), "wrong number of intersections");
		// TC09: ray starts at P0
		result = plane.findIntersections(new Ray(plane.getP0(), new Vector(2, 3, 4)));
		assertEquals(0, result.size(), "wrong number of intersections");
	}

}
