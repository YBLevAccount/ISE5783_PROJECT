package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;

class SphereTests {
	/**
	 * tests {@link geometries.Sphere#getNormal(Point)}
	 */
	@Test
	void testGetNormal() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the getNormal function works properly
		Sphere s = new Sphere(new Point(3, 1, 3), 5);
		Vector v = s.getNormal(new Point(7, 4, 3));
		assertEquals(v, new Vector(4, 3, 0).normalize());
	}
}
