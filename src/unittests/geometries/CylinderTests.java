package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
import geometries.*;

import org.junit.jupiter.api.Test;

/**
 * testing Cylinder
 * @author Yanatan
 *
 */
class CylinderTests {

	@Test
	void testGetNormal() {
		// ================= Equivalence Partitions Tests ===========================
		// TC01: tests that the getNormal function is working properly on the side
		Cylinder c = new Cylinder(8, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1);
		assertEquals(new Vector(0, 1, 0), c.getNormal(new Point(0,1, 7)));
		Vector bottomNormal = new Vector(0, 0, -1);
		Vector topNormal = bottomNormal.scale(-1);
		// TC02: tests that the getNormal function is working properly on the bottom
		// base
		assertEquals(bottomNormal, c.getNormal(new Point(0.5d, 0, 0)));
		// TC03: tests that the getNormal function is working properly on the top base
		assertEquals(topNormal, c.getNormal(new Point(0.5d,0,8)));
		// ================= BVA Tests ===========================
		try {
			// TC04: tests that the getNormal function works in the middle of the bottom
			// base
			assertEquals(bottomNormal, c.getNormal(new Point(0, 0, 0)));
			// TC05: tests that the getNormal function works in the middle of the top base
			assertEquals(topNormal, c.getNormal(new Point(0, 0, 8)));
		} catch (IllegalArgumentException e) {
			fail("center points as the point create vector ZERO");
		}
	}

}
