package unittests.geometries;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static primitives.Util.isZero;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Polygon;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** Testing Polygons
 * @author Dan */
public class PolygonTests {

   /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
   @Test
   public void testConstructor() {
      // ============ Equivalence Partitions Tests ==============

      // TC01: Correct concave quadrangular with vertices in correct order
      try {
         new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
      } catch (IllegalArgumentException e) {
         fail("Failed constructing a correct polygon");
      }

      // TC02: Wrong vertices order
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                   "Constructed a polygon with wrong order of vertices");

      // TC03: Not in the same plane
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                   "Constructed a polygon with vertices that are not in the same plane");

      // TC04: Concave quadrangular
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                                     new Point(0.5, 0.25, 0.5)), //
                   "Constructed a concave polygon");

      // =============== Boundary Values Tests ==================

      // TC10: Vertex on a side of a quadrangular
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                                     new Point(0, 0.5, 0.5)),
                   "Constructed a polygon with vertix on a side");

      // TC11: Last point = first point
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                   "Constructed a polygon with vertice on a side");

      // TC12: Co-located points
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                   "Constructed a polygon with vertice on a side");

   }

   /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
   @Test
   public void testGetNormal() {
      // ============ Equivalence Partitions Tests ==============
      // TC01: There is a simple single test here - using a quad
      Point[] pts =
         { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
      Polygon pol = new Polygon(pts);
      // ensure there are no exceptions
      assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
      // generate the test result
      Vector result = pol.getNormal(new Point(0, 0, 1));
      // ensure |result| = 1
      assertEquals(1, result.length(), 0.00000001, "Polygon's normal is not a unit vector");
      // ensure the result is orthogonal to all the edges
      for (int i = 0; i < 3; ++i)
         assertTrue(isZero(result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1]))),
                    "Polygon's normal is not orthogonal to one of the edges");
   }
   
   /**
	 * tests {@link geometries.Polygon#findIntersections(Point)}
	 */
	@Test
	void testFindIntersections() {
		Polygon rectangle = new Polygon (new Point(2, 0, 0), new Point(2, 0, 1), new Point(0, 0, 1), new Point(0, 0, 0));
		// ================= Equivalence Partitions Tests ===========================
		// TC01: intersection inside the Polygon (1 point)
		List<Point> result = rectangle.findIntersections(new Ray(new Point(0.25d, 3d, 0.25d), new Vector(0, -1, 0)));
		assertEquals (1, result.size(), "Wrong number of points");
		assertEquals(new Point(0.25, 0, 0.25) ,result.get(0), "wrong point was found");
		// TC02: intersection with plane but outside the Polygon against edge (0 points)
		result = rectangle.findIntersections(new Ray(new Point(4, 1, -2), new Vector(-1, -1, 0)));
		assertNull(result, "Wrong number of points");
		// TC03: intersection with plane but outside the Polygon against vertex (0 points)
		result = rectangle.findIntersections(new Ray(new Point(-1, -4, -5), new Vector(0, 1, 1)));
		assertNull(result, "Wrong number of points");
		
		// ================= BVA Tests ===========================
		// TC04: intersection on edge (0 points)
		result = rectangle.findIntersections(new Ray(new Point(1, 3, 1), new Vector(0, -1, 0)));
		assertNull(result, "Wrong number of points");
		// TC05: intersection on vertex (0 points)
		result = rectangle.findIntersections(new Ray(new Point(3, 2, 2), new Vector(-1, -2, -1)));
		assertNull(result, "Wrong number of points");
		// TC06: intersection on continuation of a edge (0 points)
		result = rectangle.findIntersections(new Ray(new Point(4, 1, 2), new Vector(-1, -1, -1)));
		assertNull(result, "Wrong number of points");
	}
}
