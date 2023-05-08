package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * testing Vector
 * 
 * @author Shulman and Yonatan
 *
 */
class VectorTests {
	/**
	 * tests {@link primitives.Vector#add(Vector)}
	 */
	@Test
	void testAdd() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the add function is working properly
		assertEquals(new Vector(3, 8, 12), new Vector(1, 2, 5).add(new Vector(2, 6, 7)));
		// ================= BVA Tests ===========================
		// TC02: tests that an exception is thrown for vector zero
		assertThrows(IllegalArgumentException.class, () -> new Vector(5, 2, 4).add(new Vector(-5, -2, -4)));
	}

	/**
	 * tests {@link primitives.Vector#subtract(Vector)}
	 */
	@Test
	void testSubtract() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that function is properly working
		assertEquals(new Vector(2, 1, 1), new Vector(2, 2, 2).subtract(new Vector(0, 1, 1)));

		// ================= BVA Tests =========================
		// TC02: tests vector zero
		assertThrows(IllegalArgumentException.class, () -> new Vector(-1, 3, 7).subtract(new Vector(-1, 3, 7)));
	}

	/**
	 * tests {@link primitives.Vector#scale()}
	 */
	@Test
	void testScale() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the function is working properly for a positive number that
		// does not change the direction of the vector
		assertEquals(new Vector(4, 10, 8), new Vector(2, 5, 4).scale(2));
		// ================= Equivalence Partitions Tests ===========================
		// TC02: tests that the function is working properly for a negative number that
		// changes the direction of the vector
		assertEquals(new Vector(-9, -21, -3), new Vector(3, 7, 1).scale(-3));
		// ================= BVA Tests ===========================
		// TC03: tests that the correct exception is thrown for a scale of zero that
		// would result in vector zero
		assertThrows(IllegalArgumentException.class, () -> new Vector(6, 4, 2).scale(0));
	}

	/**
	 * tests {@link primitives.Vector#crossProduct(Vector)}
	 */
	@Test
	void testCrossProduct() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the cross product function is working correctly
		assertEquals(new Vector(-13, 2, 16), new Vector(4, 2, 3).crossProduct(new Vector(2, 5, 1)));
		// ================= BVA Tests ===========================
		// TC02: tests that an exception is thrown when the vector are parallel with the
		// same direction
		assertThrows(IllegalArgumentException.class, () -> new Vector(3, 2, 2).crossProduct(new Vector(6, 4, 4)));
		// TC03: tests that an exception is thrown when the vector are parallel with
		// opposite directions
		assertThrows(IllegalArgumentException.class, () -> new Vector(4, 1, 5).crossProduct(new Vector(-12, -3, -15)));
	}

	/**
	 * tests {@link primitives.Vector#dotProduct(Vector)}
	 */
	@Test
	void testDotProduct() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the dot product function is working correctly for positive
		// outputs
		double d = new Vector(1, 4, 2).dotProduct(new Vector(5, 5, 2));
		assertTrue(Util.isZero(d - 29));
		// ================= Equivalence Partitions Tests ===========================
		// TC02: tests that the dot product function is working correctly for negative
		// outputs
		d = new Vector(-3, 1, -5).dotProduct(new Vector(1, 2, 4));
		assertTrue(Util.isZero(d + 21));
		// ================= BVA Tests ===========================
		// TC03: tests that the result is equal when the vector are orthogonal
		d = new Vector(4, 8, 1).dotProduct(new Vector(-5, 4, -12));
		assertTrue(Util.isZero(d));
	}

	/**
	 * tests {@link primitives.Vector#lengthSquared()}
	 */
	@Test
	void testLengthSquared() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the lengthSquared function is working properly
		double d = new Vector(3, 5, 2).lengthSquared();
		assertTrue(Util.isZero(d - 38));
	}

	/**
	 * tests {@link primitives.Vector#length()}
	 */
	@Test
	void testLength() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the length function is working properly
		double d = new Vector(4, 1, 2).length();
		assertTrue(Util.isZero(d - Math.sqrt(21)));
	}

	/**
	 * tests {@link primitives.Vector#normalize()}
	 */
	@Test
	void testNormalize() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the normalize function is working properly
		assertEquals(new Vector(2.d / 3.d, 1.d / 3.d, 2.d / 3.d), new Vector(2, 1, 2).normalize());
	}

}
