package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import geometries.*;

import org.junit.jupiter.api.Test;

class CylinderTests {

	@Test
	void testGetNormal() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the getNormal function is working properly on the side
		Cylinder c = new Cylinder(8, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);
		Vector v = c.getNormal(new Point(0,1, 7));
		assertEquals(v, new Vector(0, 1, 0));
		// TC02: tests that the getNormal function is working properly on the bottom
		// base
		v = c.getNormal(new Point(0.5d, 0, 0));
		assertEquals(v, new Vector(0, 0, -1));
		// TC03: tests that the getNormal function is working properly on the top base
		v = c.getNormal(new Point(0.5d,0,8));
		assertEquals(v, new Vector(0, 0, 1));
		// ================= BVA Tests ===========================
		// TC04: tests that the getNormal function works in the middle of the bottom
		// base
		v = c.getNormal(new Point(0, 0, 0));
		assertEquals(v, new Vector(0, 0, -1));
		// TC05: tests that the getNormal function works in the middle of the top base
		v = c.getNormal(new Point(0,0,8));
		assertEquals(v, new Vector(0, 0, 1));
	}

}
