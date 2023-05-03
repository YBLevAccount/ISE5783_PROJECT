package unittests.integrationtests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import geometries.*;
import renderer.Camera;
import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * integration test with camera and geometries
 * 
 * @author shulm
 *
 */
class CameraWithGeometriesTests {
	/**
	 * temp method that constructs a ray through every pixel and counts the
	 * intersections with the intersectable
	 * 
	 * @param shape  the intersectable to intersect with
	 * @param camera the camera to construct the rays with
	 * @param nX     the number of pixels in a row
	 * @param nY     the number of pixels in a column
	 * @return the number of intersections
	 */
	private int checkAllPixels(Intersectable shape, Camera camera, int nX, int nY) {
		int numOfIntersections = 0;
		for (int j = 0; j < nY; ++j)
			for (int i = 0; i < nX; ++i) {
				List<Point> intersections = shape.findIntersections(camera.constructRay(nX, nY, j, i));
				if (intersections != null)
					numOfIntersections += intersections.size();
			}
		return numOfIntersections;
	}

	/**
	 * test {@link render.Camera#constructRay(int, int, int, int)} with
	 * {@link geometries.Geometries}
	 */
	@Test
	void testConstructRayWithGeometries() {
		Camera camera = new Camera(new Point(0, 0, -1), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1)
				.setVPSize(3, 3);
		// **** Group: Test with sphere
		// TC01: The sphere is after the view plane (2 points)
		assertEquals(2, checkAllPixels(new Sphere(new Point(0, 0, -4), 1), camera, 3, 3));
		// TC02: The sphere contains the view plane (18 points)
		assertEquals(18, checkAllPixels(new Sphere(new Point(0, 0, -4), 2.5), camera, 3, 3));
		// TC03: The sphere intersects with the view plane (10 points)
		assertEquals(10, checkAllPixels(new Sphere(new Point(0, 0, -3.5), 2), camera, 3, 3));
		// TC04: The sphere contains the camera (9 points)
		assertEquals(9, checkAllPixels(new Sphere(new Point(0, 0, -1), 8), camera, 3, 3));
		// TC05: The sphere is behind the camera (0 points)
		assertEquals(0, checkAllPixels(new Sphere(new Point(0, 0, 1), 1), camera, 3, 3));
		// **** Group: Test with plane
		// TC06: The plane is parallel to the view plane (9 points)
		assertEquals(9,
				checkAllPixels(new Plane(new Point(0, 0, -3), new Point(0, 1, -3), new Point(1, 0, -3)), camera, 3, 3));
		// TC07: The plane is at a small angle and visible by all pixels (9 points)
		assertEquals(9,
				checkAllPixels(new Plane(new Point(0, 0, -3), new Point(0, 1, -2.9), new Point(1, 0, -3)), camera, 3, 3));
		// TC08: The plane is at a big angle and not visible by the bottom pixels (6
		// points)
		assertEquals(6,
				checkAllPixels(new Plane(new Point(0, 0, -10), new Point(0, 1, -2.05), new Point(1, 0, -3)), camera, 3, 3));
		// **** Group: Test with triangle
		// TC09: Only one ray intersects with the triangle (1 point)
		assertEquals(1, checkAllPixels(new Triangle(new Point(0, 1, -3), new Point(1, -1, -3), new Point(-1, -1, -3)),
				camera, 3, 3));
		// TC10: More than one ray intersects with the triangle (2 points)
		assertEquals(2, checkAllPixels(new Triangle(new Point(0, 20, -3), new Point(1, -1, -3), new Point(-1, -1, -3)),
				camera, 3, 3));

	}

}
