package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Double3;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

class VectorTests {
	/**
	 * tests {@link primitives.Vector#add(Vector)}
	 */
	@Test
	void testAdd() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the add function is working properly
		Vector v = new Vector(1, 2, 5);
		v = v.add(new Vector(2, 6, 7));
		assertEquals(v, new Vector(3, 8, 12));
		// ================= BVA Tests ===========================
		// TC02: tests that an exception is thrown for vector zero
		try {
			v = new Vector(5, 2, 4);
			v.add(new Vector(-5, -2, -4));
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "ZERO vector");
		} catch (Exception e) {
			fail("Incorrect exception for vector zero");
		}
	}
	/**
	 *  tests {@link primitives.Vector#subtract(Vector)}
	 */
	@Test
	void testSubtract() {
		//there is no need to test the function as it was already tested in Point
	}

	/**
	 * tests {@link primitives.Vector#scale()}
	 */
	@Test
	void testScale() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the function is working properly for a positive number that
		// does not change the direction of the vector
		Vector v = new Vector(2, 5, 4);
		v = v.scale(2);
		assertEquals(v, new Vector(4, 10, 8));
		// ================= Equivalence Partitions Tests ===========================
		// TC02: tests that the function is working properly for a negative number that
		// changes the direction of the vector
		v = new Vector(3, 7, 1);
		v = v.scale(-3);
		assertEquals(v, new Vector(-9, -21, -3));
		// ================= BVA Tests ===========================
		// TC03: tests that the correct exception is thrown for a scale of zero that
		// would result in vector zero
		try {
			v = new Vector(6, 4, 2);
			v.scale(0);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "ZERO vector");
		} catch (Exception e) {
			fail("Incorrect exception for vector zero");
		}
	}

	/**
	 * tests {@link primitives.Vector#crossProduct(Vector)}
	 */
	@Test
	void testCrossProduct() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the cross product function is working correctly
		Vector v = new Vector(4, 2, 3);
		v = v.crossProduct(new Vector(2, 5, 1));
		assertEquals(v, new Vector(-13, 2, 16));
		// ================= BVA Tests ===========================
		// TC02: tests that an exception is thrown when the vector are parallel with the
		// same direction
		try {
			v = new Vector(3, 2, 2);
			v.crossProduct(new Vector(6, 4, 4));
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "ZERO vector");
		} catch (Exception e) {
			fail("Incorrect exception for vector zero");
		}
		// TC03: tests that an exception is thrown when the vector are parallel with
		// opposite directions
		try {
			v = new Vector(4, 1, 5);
			v.crossProduct(new Vector(-12, -3, -15));
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "ZERO vector");
		} catch (Exception e) {
			fail("Incorrect exception for vector zero");
		}
	}

	/**
	 * tests {@link primitives.Vector#dotProduct(Vector)}
	 */
	@Test
	void testDotProduct() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the dot product function is working correctly for positive
		// outputs
		Vector v = new Vector(1, 4, 2);
		double d = v.dotProduct(new Vector(5, 5, 2));
		assertTrue(Util.isZero(d - 29));
		// ================= Equivalence Partitions Tests ===========================
		// TC02: tests that the dot product function is working correctly for negative
		// outputs
		v = new Vector(-3, 1, -5);
		d = v.dotProduct(new Vector(1, 2, 4));
		assertTrue(Util.isZero(d + 21));
		// ================= BVA Tests ===========================
		// TC03: tests that the result is equal when the vector are orthogonal
		v = new Vector(4, 8, 1);
		d = v.dotProduct(new Vector(-5, 4, -12));
		assertTrue(Util.isZero(d));
	}

	/**
	 * tests {@link primitives.Vector#lengthSquared()}
	 */
	@Test
	void testLengthSquared() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the lengthSquared function is working properly
		Vector v = new Vector(3, 5, 2);
		double d = v.lengthSquared();
		assertTrue(Util.isZero(d - 38));
	}

	/**
	 * tests {@link primitives.Vector#length()}
	 */
	@Test
	void testLength() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the length function is working properly
		Vector v = new Vector(4, 1, 2);
		double d = v.length();
		assertTrue(Util.isZero(d - Math.sqrt(21)));
	}

	/**
	 * tests {@link primitives.Vector#normalize()}
	 */
	@Test
	void testNormalize() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the normalize function is working properly
		Vector v = new Vector(2, 1, 2);
		v = v.normalize();
		assertEquals(v, new Vector(2.d / 3.d, 1.d / 3.d, 2.d / 3.d));
	}

}
