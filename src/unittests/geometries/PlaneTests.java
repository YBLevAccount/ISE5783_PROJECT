package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;

class PlaneTests {
	/**
	 * tests {@link geometries.Plane#Plane(Point,Point,Point)}
	 */
	void testConstructorThreePoints() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the constructor creates the correct normal
		Plane p = new Plane(new Point(1,0,0), new Point(0,1,0), new Point(0,0,1));
		Vector v = p.getNormal();
		assertEquals(v,new Vector(1,1,1).normalize());
		
		// ================= BVA Tests ===========================
		// TC02: tests that the correct exception is thrown when the first two points
		// are equal
		try {
			new Plane(new Point(3, 4, 5), new Point(3, 4, 5), new Point(6, 2, 1));
		} catch (IllegalArgumentException e) {
			assertEquals(e, "First and second points are equal");
		} catch (Exception e) {
			fail("Incorrect exception for first and second points being equal");
		}
		// TC03: tests that the correct exception is thrown when the first two points
		// are equal
		try {
			new Plane(new Point(3, 4, 5), new Point(6, 8, 10), new Point(12, 16, 20));
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Points are on the same line");
		} catch (Exception e) {
			fail("Incorrect exception for points being on the same lines");
		}
	}

	/**
	 * tests {@link geometries.Plane#getNormal(Point)}
	 */
	@Test
	void testGetNormal() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that getNormal(Point) is working properly
		Plane p = new Plane(new Point(1, 1, 1), new Point(2, 2, 2), new Point(-3, 4, -3));
		Vector v = p.getNormal(new Point(1, 0, 1));
		assertEquals(v, new Vector(-1, 0, 1).normalize());
	}

}
