package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * testing Ray
 * 
 * @author Shulman and Yonatan
 *
 */
class RayTests {

	/**
	 * Test method for {@link primitives.Ray#getPoint(double)}.
	 */
	@Test
	void testGetPoint() {
		Point start = new Point(1, 0, 0);
		Ray ray = new Ray(start, new Vector(0, 1, 0));
		// ================= Equivalence Partitions Tests ===========================
		// TC01: works with double except 0
		assertEquals(new Point(1, 2, 0), ray.getPoint(2));

		// ================= BVA Tests =========================
		// TC02: works with 0
		assertDoesNotThrow(() -> ray.getPoint(0), "t = 0 throws an exception");
		assertEquals(start, ray.getPoint(0), "t = 0 the resulting point is not the ray head ");
	}

	/**
	 * tests {@link primitives.Ray#findClosestPoint(List)}
	 */
	@Test
	void testFindClosestPoint() {
		Ray ray = new Ray(new Point(1, 1, 1), new Vector(1, 0, 1));
		// ================= Equivalence Partitions Tests ===========================
		// TC01: the closest point to the head of the ray is in the middle of the list
		List<Point> points = List.of(new Point(3, 1, 3), new Point(2, 1, 2), new Point(4, 1, 4));
		assertEquals(new Point(2, 1, 2), ray.findClosestPoint(points));
		// ================= BVA Tests =========================
		// TC02: empty list
		points = List.of();
		assertNull(ray.findClosestPoint(points));
		// TC03: the closest point to the head of the ray is the first element of the
		// list
		points = List.of(new Point(3.5, 1, 3.5), new Point(4.5, 1, 4.5), new Point(7.5, 1, 7.5), new Point(10, 1, 10));
		assertEquals(new Point(3.5, 1, 3.5), ray.findClosestPoint(points));
		// TC04: the closest point to the head of the ray is the last element of the
		// list
		points = List.of(new Point(7.1, 1, 7.1), new Point(3.2, 1, 3.2));
		assertEquals(new Point(3.2, 1, 3.2), ray.findClosestPoint(points));
	}

}
