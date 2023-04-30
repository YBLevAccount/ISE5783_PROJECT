package unittests.primitives;

import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * testing Point
 * 
 * @author shulm
 *
 */
class PointTests {
	/**
	 * tests {@link Point#add(Vector)}
	 */
	@Test
	void testAdd() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that function is properly working
		assertEquals(new Point(1, 1, 1), new Point(-1, 1, 0).add(new Vector(2, 0, 1)));
	}

	/**
	 * tests {@link Point#subtract(Point)}
	 */
	@Test
	void testSubtract() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that function is properly working
		assertEquals(new Vector(2, 1, 1), new Point(2, 2, 2).subtract(new Point(0, 1, 1)));

		// ================= BVA Tests =========================
		// TC02: tests vector zero
		assertThrows(IllegalArgumentException.class, () -> new Point(-1, 3, 7).subtract(new Point(-1, 3, 7)));
	}

	/**
	 * tests {@link Point#distance(Point)}
	 */
	@Test
	void testDistance() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that function is properly working and returning the distance
		// between two points
		double d = new Point(3, 4, 5).distance(new Point(-7, -7, -7));
		assertTrue(Util.isZero(d - Math.sqrt(365)));
	}

	/**
	 * tests {@link Point#distanceSquared(Point)}
	 */
	@Test
	void testDistanceSquared() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that function is properly working and returning the distance
		// squared between two points
		double d = new Point(1, 5, 8).distanceSquared(new Point(-2, 4, 10));
		assertTrue(Util.isZero(d - 14));
	}

}
