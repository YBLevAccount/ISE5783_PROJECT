package unittests.renderer;

import static java.awt.Color.YELLOW;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {

	/**
	 * Produce a scene with basic 3D model and render it into a png image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
						new Double3(1, 1, 1))) //
				.setBackground(new Color(75, 127, 90));

		scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
				// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
				// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
		// right
		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500) //
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
	}

	/**
	 * testing that the rotations and placement changes of the camera work properly
	 */
	@Test
	public void testCameraChangesAndRotations() {
		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
						new Double3(1, 1, 1))) //
				.setBackground(new Color(75, 127, 90));

		scene.geometries.add(new Sphere(new Point(0, 0, -100), 50d),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
				// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
				// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
		// right
		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500);

		// ================= BVA Tests =========================
		// TC01: tests case where the new position is in the middle of the scene(should
		// throw exception)
		camera.setImageWriter(new ImageWriter("set position render test1", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		try {
			camera.changePosition(new Point(0, 0, -100));
			fail("Exception Was Not Thrown");
		} catch (IllegalArgumentException e) {

		} catch (Exception e) {
			fail("Wrong Exception Has Been Thrown");
		}
		// TC02: tests case where the camera is flipped to the other side of the scene
		camera.setImageWriter(new ImageWriter("set position render test2", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene)).changePosition(new Point(0, 0, -200));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		// **** Group: The new vTo ray is orthogonal to the old one. The result in all
		// cases should be a sphere
		// TC03: tests case where the new vTO vector is equal to the old vUp vector
		camera.setImageWriter(new ImageWriter("set position render test3", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene)).changePosition(new Point(0, 100, -100));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		// TC04: tests case where the new VTo vector is opposite to the old vUp vector
		camera.setImageWriter(new ImageWriter("set position render test4", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene)).changePosition(new Point(0, -100, -100));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		// TC05: tests case where the new vTo vector is equal to the old vRight vector
		camera.setImageWriter(new ImageWriter("set position render test5", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene)).changePosition(new Point(100, 0, -100));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		// TC06: tests case where the new vTo vector is opposite to the old vRight
		// vector
		camera.setImageWriter(new ImageWriter("set position render test6", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene)).changePosition(new Point(-100, 0, -100));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
		// ================= Equivalence Partitions Tests ===========================
		// TC07: tests that function is properly working
		camera.setImageWriter(new ImageWriter("set position render test7", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene)).changePosition(new Point(67, 54, 212));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
	}
	// For stage 6 - please disregard in stage 5
	/**
	 * Produce a scene with basic 3D model - including individual lights of the
	 * bodies and render it into a png image with a grid
	 */
	// @Test
	// public void basicRenderMultiColorTest() {
	// Scene scene = new Scene("Test scene")//
	// .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //
	//
	// scene.geometries.add( //
	// new Sphere(new Point(0, 0, -100), 50),
	// // up left
	// new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new
	// Point(-100, 100, -100))
	// .setEmission(new Color(GREEN)),
	// // down left
	// new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new
	// Point(-100, -100, -100))
	// .setEmission(new Color(RED)),
	// // down right
	// new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new
	// Point(100, -100, -100))
	// .setEmission(new Color(BLUE)));
	//
	// Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1,
	// 0)) //
	// .setVPDistance(100) //
	// .setVPSize(500, 500) //
	// .setImageWriter(new ImageWriter("color render test", 1000, 1000))
	// .setRayTracer(new RayTracerBasic(scene));
	//
	// camera.renderImage();
	// camera.printGrid(100, new Color(WHITE));
	// camera.writeToImage();
	// }

	/** Test for XML based scene - for bonus */
	@Test
	public void basicRenderXml() {
		Scene scene = new Scene("XML Test scene");
		// enter XML file name and parse from XML file into scene object
		// using the code you added in appropriate packages
		// ...
		// NB: unit tests is not the correct place to put XML parsing code

		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500).setImageWriter(new ImageWriter("xml render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene));
		camera.renderImage();
		camera.printGrid(100, new Color(YELLOW));
		camera.writeToImage();
	}
}