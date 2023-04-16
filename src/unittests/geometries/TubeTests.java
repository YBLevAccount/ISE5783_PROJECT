package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import geometries.*;

class TubeTests {
	/**
	 * tests {@link geometries.Tube#getNormal(Point)}
	 */
	@Test
	void testGetNormal() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the getNormal function is working properly
		Tube t = new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);
		Vector v = t.getNormal(new Point(0, 1, 4));
		assertEquals(v, new Vector(0, 1, 0));
		// ================= BVA Tests ===========================
		// TC02: tests that the getNormal function works when the P-P0 is orthogonal
		// to v and t=0
		v = t.getNormal(new Point(1, 0, 0));
		assertEquals(v, new Vector(1, 0, 0));
	}

}
