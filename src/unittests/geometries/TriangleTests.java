package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * tests Triangle
 * 
 * @author Shulman and Yonatan
 *
 */
class TriangleTests {

	/**
	 * tests {@link geometries.Triangle#Triangle(Point,Point,Point)}
	 */
	void testConstructor() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the constructor works with correct input
		try {
			new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 0));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct triangle");
		}

		// ================= BVA Tests ===========================
		// TC02: tests that the correct exception is thrown when the first two points
		// are equal
		assertThrows(IllegalArgumentException.class,
				() -> new Triangle(new Point(3, 4, 5), new Point(3, 4, 5), new Point(6, 2, 1)));
		// TC03: tests that the correct exception is thrown when all the points
		// on the same line
		assertThrows(IllegalArgumentException.class,
				() -> new Triangle(new Point(3, 4, 5), new Point(6, 8, 10), new Point(12, 16, 20)));
	}

	/**
	 * tests {@link geometries.Triangle#getNormal(Point)}
	 */
	@Test
	void testGetNormal() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that getNormal(Point) is working properly
		Point a = new Point(1, 1, 1), b = new Point(2, 2, 2), c = new Point(-3, 4, -3);
		Triangle t = new Triangle(a, b, c);
		Vector result = t.getNormal(new Point(1, 0, 1));
		// check that the vector is normalized
		assertEquals(0, Util.alignZero(result.lengthSquared() - 1));
		// check that the vector is orthogonal to the edges
		assertEquals(0, Util.alignZero(result.dotProduct(a.subtract(b))));
		assertEquals(0, Util.alignZero(result.dotProduct(b.subtract(c))));
	}

	/**
	 * tests {@link geometries.Triangle#findIntersections(Point)}
	 */
	@Test
	void testFindIntersections() {
		Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
		// ================= Equivalence Partitions Tests ===========================
		// TC01: intersection inside the Triangle (1 point)
		List<Point> result = triangle.findIntersections(new Ray(new Point(0.25d, 3d, 0.25d), new Vector(0, -1, 0)));
		assertEquals(1, result.size(), "Wrong number of points");
		assertEquals(new Point(0.25, 0.5, 0.25), result.get(0), "wrong point was found");
		// TC02: intersection with plane but outside the triangle against edge (0
		// points)
		result = triangle.findIntersections(new Ray(new Point(1.5, 1, -0.5), new Vector(-1, 0, 0)));
		assertNull(result, "Wrong number of points");
		// TC03: intersection with plane but outside the triangle against vertex (0
		// points)
		result = triangle.findIntersections(new Ray(new Point(1, -0.5, -3), new Vector(0, 0, 1)));
		assertNull(result, "Wrong number of points");

		// ================= BVA Tests ===========================
		// TC04: intersection on edge (0 points)
		result = triangle.findIntersections(new Ray(new Point(0, 0.5, 1), new Vector(0, 0, -1)));
		assertNull(result, "Wrong number of points");
		// TC05: intersection on vertex (0 points)
		result = triangle.findIntersections(new Ray(new Point(3, 0, 4), new Vector(-1, 0, -1)));
		assertNull(result, "Wrong number of points");
		// TC06: intersection on continuation of a edge (0 points)
		result = triangle.findIntersections(new Ray(new Point(1, -3, 6), new Vector(-1, -1, -1)));
		assertNull(result, "Wrong number of points");
	}

}
