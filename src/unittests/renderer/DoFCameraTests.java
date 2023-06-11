package unittests.renderer;

import static java.awt.Color.WHITE;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * test the DoF image upgrade compared to the basic camera
 * 
 * @author Yonatan and Shulman
 *
 */
class DoFCameraTests {

	private Camera camera;
	private Scene scene;
	private RayTracerBasic tracer;

	/**
	 * create a pyramid using base, size and direction vectors
	 * 
	 * @param size     of the pyramid for any direction
	 * @param base     bottom left corner of the pyramid
	 * @param forward  vector pointing forward
	 * @param right    vector pointing right
	 * @param material the material of the pyramid
	 * @param color    the color of the pyramid
	 * @return the pyramid geometries
	 */
	private Geometries createPyramid(double size, Point base, Vector forward, Vector right, Material material,
			Color color) {
		Geometries pyramid = new Geometries();
		Vector up = right.crossProduct(forward);
		Point top = base.add(forward.scale(size / 2)).add(right.scale(size / 2)).add(up.scale(size));
		Point farLeft = base.add(forward.scale(size));
		right = right.scale(size);
		Point closeRight = base.add(right);
		Point farRight = farLeft.add(right);

		Triangle[] triangles = { new Triangle(top, closeRight, base), new Triangle(top, base, farLeft),
				new Triangle(top, farLeft, farRight), new Triangle(top, farRight, closeRight) };
		for (Triangle triangle : triangles)
			triangle.setEmission(color).setMaterial(material);

		pyramid.add(triangles);
		return pyramid;
	}

	/**
	 * set up the object in the scene and the camera direction
	 */
	private void setUp() {
		camera = new Camera(new Point(0, 0, 20), new Vector(1, 0, 0), new Vector(0, 0, 1)).setVPSize(180, 180)
				.setVPDistance(350);
		Vector vTo = camera.getvTo();
		Vector vRight = camera.getvRight();

		scene = new Scene("Test scene");
		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.1));
		scene.background = new Color(135, 206, 235);

		Material brickMaterial = new Material().setKd(0.8).setKr(0.15).setKt(0.1).setShininess(20);
		Material sandMaterial = new Material().setKd(0.95);
		Material glassMaterial = new Material().setKt(0.8).setKs(0.65).setKd(0.1).setShininess(300);
		Color brickColor = new Color(170, 74, 68);
		Color sandColor = new Color(194, 178, 128);
		Color glassColor = new Color(15, 15, 15);
		Point l1pos = new Point(550, 120, 110);
		Point l2pos = new Point(550, -120, 100);

		scene.geometries.add(
				// the ground, just a send plane
				new Plane(Point.ZERO, new Vector(0, 0, 1)).setEmission(sandColor).setMaterial(sandMaterial),
				// the pyramids
				createPyramid(50, new Point(400, 125, 0), vTo, vRight, brickMaterial, brickColor),
				createPyramid(50, new Point(500, 25, 0), vTo, vRight, brickMaterial, brickColor),
				createPyramid(50, new Point(600, -75, 0), vTo, vRight, brickMaterial, brickColor),
				// the light covers
				new Sphere(l1pos, 80).setEmission(glassColor).setMaterial(glassMaterial),
				new Sphere(l2pos, 70).setEmission(glassColor).setMaterial(glassMaterial));

		scene.lights.add(new DirectionalLight(new Color(100, 100, 100), new Vector(1, -1, -1)));
		scene.lights.add(new PointLight(new Color(400, 0, 400), l1pos).setKq(0.00005).setKl(0.0005));
		scene.lights.add(new PointLight(new Color(0, 400, 0), l2pos).setKq(0.0005).setKl(0.005));

		tracer = new RayTracerBasic(scene);
		camera.setRayTracer(tracer);
	}

	/**
	 * tests both cameras on the same image
	 */
	@Test
	void depthOfFieldTest() {
		setUp();

		ImageWriter imageWriter = new ImageWriter("WithDepthOfFild", 500, 500);
		camera.setImageWriter(imageWriter).setApertureLength(20).setFocalDistance(500);
		camera.setRayNum(121); // for debug only!!! for real thing use different number
		camera.renderImage().writeToImage();

	}

	@Test
	void noDepthOfFieldTest() {
		setUp();

		ImageWriter imageWriter = new ImageWriter("WithoutDepthOfFild", 500, 500);
		camera.setImageWriter(imageWriter).renderImage().writeToImage();
	}
}
