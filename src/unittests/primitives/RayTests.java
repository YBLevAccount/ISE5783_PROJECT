package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * testing Ray
 * @author Yonatan
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
		try {
			assertEquals(start, ray.getPoint(0));
		} catch (IllegalArgumentException e) {
			fail("doesnt work with 0");
		}
	}

}
