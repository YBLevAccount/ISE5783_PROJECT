/**
 * 
 */
package unittests.renderer;

import static java.awt.Color.WHITE;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * tests rendring image from different positions and angles
 * 
 * @author Yonatan and Shulman
 *
 */
class CameraTransformationTests {

	private Camera camera;
	private Scene scene;
	private RayTracerBasic tracer;

	/**
	 * this function create a box in the space
	 * 
	 * @param position the low close left point of the box
	 * @param forward  vector pointing forward with size to go with
	 * @param up       vector pointing up with size to go with
	 * @param right    vector pointing right with size to go with
	 * @param color    the color of the box
	 * @param material the material of the box
	 */
	private Geometries createBox(Point position, Vector forward, Vector up, Vector right, Color color,
			Material material) {
		Point[][][] points = new Point[2][2][2];
		for (int i = 0; i < 2; ++i)
			for (int j = 0; j < 2; ++j)
				for (int k = 0; k < 2; ++k) {
					Point value = position;
					if (i > 0)
						value = value.add(forward);
					if (j > 0)
						value = value.add(up);
					if (k > 0)
						value = value.add(right);
					points[i][j][k] = value;
				}

		List<Polygon> polygons = List.of(
				new Polygon(points[0][0][0], points[0][1][0], points[0][1][1], points[0][0][1]),
				new Polygon(points[1][0][0], points[1][1][0], points[1][1][1], points[1][0][1]),
				new Polygon(points[0][0][0], points[1][0][0], points[1][0][1], points[0][0][1]),
				new Polygon(points[0][1][0], points[1][1][0], points[1][1][1], points[0][1][1]),
				new Polygon(points[0][0][0], points[1][0][0], points[1][1][0], points[0][1][0]),
				new Polygon(points[0][0][1], points[1][0][1], points[1][1][1], points[0][1][1]));

		Geometries box = new Geometries();
		for (Polygon polygon : polygons)
			box.add(polygon.setEmission(color).setMaterial(material));

		return box;
	}

	private void setUp() {
		scene = new Scene("Test scene");
		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.1));
		scene.background = new Color(178, 190, 181);

		Material woodMaterial = new Material().setKd(0.9);
		Material oneSideMirror = new Material().setKr(0.9).setKt(0.1);
		Material metal = new Material().setKd(0.4).setKs(0.4).setKr(0.2).setShininess(60);
		Color woodColor = new Color(133, 94, 66);

		scene.geometries.add(
				// the floor, made from wood
				new Plane(Point.ZERO, new Vector(0, 0, 1)).setEmission(woodColor).setMaterial(woodMaterial),
				// the one side mirror
				new Plane(new Point(500, -400, 0), new Point(500, -400, 800), new Point(500, 400, 800))
						.setMaterial(oneSideMirror),
				// the table
				createBox(new Point(280, -20, 0), new Vector(40, 0, 0), new Vector(0, 0, 60), new Vector(0, 20, 0),
						woodColor, woodMaterial),
				createBox(new Point(260, -40, 60), new Vector(80, 0, 0), new Vector(0, 0, 20), new Vector(0, 60, 0),
						woodColor, woodMaterial),
				// the sits
				createBox(new Point(280, -80, 0), new Vector(40, 0, 0), new Vector(0, 0, 40), new Vector(0, 20, 0),
						new Color(255, 255, 0), metal),
				createBox(new Point(260, -100, 40), new Vector(80, 0, 0), new Vector(0, 0, 20), new Vector(0, 60, 0),
						new Color(255, 255, 0), metal),
				createBox(new Point(280, 50, 0), new Vector(40, 0, 0), new Vector(0, 0, 40), new Vector(0, 20, 0),
						new Color(0, 255, 255), metal),
				createBox(new Point(260, 20, 40), new Vector(80, 0, 0), new Vector(0, 0, 20), new Vector(0, 60, 0),
						new Color(0, 255, 255), metal),
				// light from the ground
				new Sphere(new Point(700, 0, 80), 50).setEmission(new Color(255, 0, 255)).setMaterial(metal));

		scene.lights.addAll(List.of(
				// general light
				new DirectionalLight(new Color(20, 20, 15), new Vector(0, 0, -1)),
				// strong light in one side
				new SpotLight(new Color(800, 0, 0), new Point(200, 200, 600), new Vector(0, -0.5, -1)).setKl(0.001)
						.setKq(0.0001),
				new SpotLight(new Color(0, 800, 0), new Point(200, -200, 600), new Vector(0, 0.5, -1)).setKl(0.002)
						.setKq(0.0002),
				new SpotLight(new Color(0, 0, 800), new Point(200, 0, 600), new Vector(-0.1, 0, -1)).setKl(0.003)
						.setKq(0.0003),
				// weak light in the other side
				new PointLight(new Color(30, 30, 30), new Point(600, 0, 300)).setKl(0.005).setKq(0.0005)));

		tracer = new RayTracerBasic(scene);
		camera = new Camera(new Point(0, 0, 150), new Vector(1, 0, -0.3), new Vector(0.3, 0, 1)).setVPDistance(280)
				.setVPSize(180, 180).setRayTracer(tracer);
	}

	/**
	 * shows the image from starting position
	 */
	@Test
	void baseCase() {
		setUp();
		ImageWriter imageR = new ImageWriter("TranpositionBaseCase", 800, 800);
		camera.setImageWriter(imageR).renderImage().writeToImage();
	}

	/**
	 * shows the image from different position
	 */
	@Test
	void testPositionChange() {
		setUp();
		camera = new Camera(new Point(1000, 120, 70), Point.ZERO).setVPDistance(280)
				.setVPSize(180, 180).setRayTracer(tracer);
		ImageWriter imageR = new ImageWriter("TranpositionChangePosition", 800, 800);
		camera.setImageWriter(imageR).renderImage().writeToImage();
	}

	/**
	 * show the image after rotation
	 */
	@Test
	void testRotation() {
		setUp();
		ImageWriter imageR = new ImageWriter("TranpositionAfterRotation", 800, 800);
		camera.rotate(30).setImageWriter(imageR).renderImage().writeToImage();
	}
}
