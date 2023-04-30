package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;

/**
 * tests Sphere
 * 
 * @author Yonatan
 *
 */
class SphereTests {
	/**
	 * tests {@link geometries.Sphere#getNormal(Point)}
	 */
	@Test
	void testGetNormal() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the getNormal function works properly
		Sphere s = new Sphere(new Point(3, 1, 3), 5);
		assertEquals(new Vector(4, 3, 0).normalize(), s.getNormal(new Point(7, 4, 3)));
	}

	/**
	 * tests {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(new Point(1, 0, 0), 1d);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray's line is outside the sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
				"Ray's line out of sphere");
		// TC02: Ray starts before and crosses the sphere (2 points)
		Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
		Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
		List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)));
		assertEquals(2, result.size(), "Wrong number of points");
		if (result.get(0).getX() > result.get(1).getX())
			result = List.of(result.get(1), result.get(0));
		assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
		// TC03: Ray starts inside the sphere (1 point)
		p1 = new Point(1.4114378277661476476d, 0.91143782776614764763d, 0);
		result = sphere.findIntersections(new Ray(new Point(1, 0.5d, 0), new Vector(1, 1, 0)));
		assertEquals(1, result.size(), "Wrong number of points");
		assertEquals(List.of(p1), result, "Ray crosses sphere");
		// TC04: Ray starts after the sphere (0 points)
		result = sphere.findIntersections(new Ray(new Point(3, 3, 3), new Vector(1, 1, -1)));
		assertEquals(0, result.size(), "Wrong number of points");
		// =============== Boundary Values Tests ==================
		// **** Group: Ray's line crosses the sphere (but not the center)
		// TC05: Ray starts at sphere and goes inside (1 points)
		p1 = new Point(1, Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
		result = sphere
				.findIntersections(new Ray(new Point(1, Math.sqrt(2) / 2, Math.sqrt(2) / 2), new Vector(0, 0, -1)));
		assertEquals(1, result.size(), "Wrong number of points");
		assertEquals(List.of(p1), result, "Ray crosses sphere");
		// TC06: Ray starts at sphere and goes outside (0 points)
		result = sphere
				.findIntersections(new Ray(new Point(1, Math.sqrt(2) / 2, Math.sqrt(2) / 2), new Vector(0, 2, 1)));
		assertEquals(0, result.size(), "Wrong number of points");
		// **** Group: Ray's line goes through the center
		// TC07: Ray starts before the sphere (2 points)
		p1 = new Point(1, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
		p2 = new Point(1, Math.sqrt(2) / 2, Math.sqrt(2) / 2);
		result = sphere.findIntersections(
				new Ray(new Point(1, Math.sqrt(2) / 2 + 1, Math.sqrt(2) / 2 + 1), new Vector(0, -1, -1)));
		assertEquals(2, result.size(), "Wrong number of points");
		if (result.get(0).getY() > result.get(1).getY())
			result = List.of(result.get(1), result.get(0));
		assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
		// TC08: Ray starts at sphere and goes inside (1 points)
		p1 = new Point(1, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
		result = sphere
				.findIntersections(new Ray(new Point(1, Math.sqrt(2) / 2, Math.sqrt(2) / 2), new Vector(0, -1, -1)));
		assertEquals(1, result.size(), "Wrong number of points");
		assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
		// TC09: Ray starts inside (1 points)
		p1 = new Point(1, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
		result = sphere.findIntersections(
				new Ray(new Point(1, Math.sqrt(2) / 2 - 0.5d, Math.sqrt(2) / 2 - 0.5d), new Vector(0, -1, -1)));
		assertEquals(1, result.size(), "Wrong number of points");
		assertEquals(List.of(p1), result, "Ray crosses sphere");
		// TC10: Ray starts at the center (1 points)
		p1 = new Point(1, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
		result = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, -1, -1)));
		assertEquals(1, result.size(), "Wrong number of points");
		assertEquals(List.of(p1), result, "Ray crosses sphere");
		// TC11: Ray starts at sphere and goes outside (0 points)
		result = sphere
				.findIntersections(new Ray(new Point(1, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2), new Vector(0, -1, -1)));
		assertEquals(0, result.size(), "Wrong number of points");
		// TC12: Ray starts after sphere (0 points)
		result = sphere.findIntersections(
				new Ray(new Point(1, -Math.sqrt(2) / 2 - 1, -Math.sqrt(2) / 2 - 1), new Vector(0, -1, -1)));
		assertEquals(0, result.size(), "Wrong number of points");
		// **** Group: Ray's line is tangent to the sphere (all tests 0 points)
		// TC13: Ray starts before the tangent point
		result = sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0)));
		assertEquals(0, result.size(), "Wrong number of points");
		// TC14: Ray starts at the tangent point
		result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(0, 1, 0)));
		assertEquals(0, result.size(), "Wrong number of points");
		// TC15: Ray starts after the tangent point
		result = sphere.findIntersections(new Ray(new Point(2, 0, 1), new Vector(0, 0, 1)));
		assertEquals(0, result.size(), "Wrong number of points");
		// **** Group: Special cases
		// TC16: Ray's line is outside, ray is orthogonal to ray start to sphere's
		// center line
		result = sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 1, 1)));
		assertEquals(0, result.size(), "Wrong number of points");
	}
}
