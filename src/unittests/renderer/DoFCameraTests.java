package unittests.renderer;

import static java.awt.Color.WHITE;
import static org.junit.jupiter.api.Assertions.*;

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

	/**
	 * tests both cameras on the same image
	 */
	@Test
	void depthOfFieldTest() {
		Scene scene = new Scene("Test scene");
		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.1));
		Material materail = new Material().setKd(0.4).setKr(0.4).setKt(0.2).setShininess(100);
		scene.geometries.add(
				new Sphere(new Point(500, 50, 0), 50).setEmission(new Color(0, 0, 255)).setMaterial(materail),
				new Sphere(new Point(1000, -50, 0), 100).setEmission(new Color(255, 0, 0)).setMaterial(materail));
		scene.lights.add(new DirectionalLight(new Color(400, 400, 400), new Vector(1, -1, -1)));
		RayTracerBasic tracer = new RayTracerBasic(scene);

		Camera camera = new DepthOfFieldCamera(Point.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1), 500, 10)
				.setVPSize(200, 200).setVPDistance(500).setRayTracer(tracer);
		ImageWriter imageWriter = new ImageWriter("WithDepthOfFild", 500, 500);
		camera.setImageWriter(imageWriter).renderImage() //
				.writeToImage();

		Camera baseCamera = new Camera(Point.ZERO, new Vector(1, 0, 0), new Vector(0, 0, 1)).setVPSize(200, 200)
				.setVPDistance(500).setRayTracer(tracer);
		imageWriter = new ImageWriter("WithoutDepthOfFild", 500, 500);
		baseCamera.setImageWriter(imageWriter).renderImage() //
				.writeToImage();
	}

}
