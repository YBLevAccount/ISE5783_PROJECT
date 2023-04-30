/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

/**
 * @author Yonatan
 *
 */
class GeometriesTests {

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersections() {
		Geometries geo = new Geometries(new Sphere(new Point (0, 1, 0), 1),
						 new Plane(new Point(0, -1, 0), new Vector(0, 1, 0)),
						 new Triangle(new Point(0, 4, 0), new Point(1, 4, 0), new Point(0, 4, 1)));
		// ================= Equivalence Partitions Tests ===========================
		// TC01: some of the shapes intersect and some don't
		List<Point> result = geo.findIntersections(new Ray(new Point(0, 1, 0), new Vector(0, -1, 0)));
		assertEquals(2, result.size(), "Wrong number of points");
		// ================= BVA Tests ===========================
		// TC02: empty list
		assertNull(new Geometries().findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0))));
		// TC03: no intersections at all
		result = geo.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));
		assertNull(result, "Wrong number of points");
		// TC04: all the shapes intersect
		result = geo.findIntersections(new Ray(new Point(0.25, 5, 0.25), new Vector(0, -1, 0)));
		assertEquals(4, result.size(), "Wrong number of points");
		//TC05: only one shape intersect
		result = geo.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, -1, 0)));
		assertEquals(1, result.size(), "Wrong number of points");
	}

}
