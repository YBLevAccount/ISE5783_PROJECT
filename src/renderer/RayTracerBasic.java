package renderer;

import primitives.Color;

import primitives.Ray;
import scene.*;

import java.util.List;

import primitives.*;

/**
 * the most basic rayTracer
 * 
 * @author Shulman and Yonatan
 *
 */
public class RayTracerBasic extends RayTracerBase {
	/**
	 * construct rayTracer with specific scene
	 * 
	 * @param scene with the relevant geometries and lighting
	 */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}

	@Override
	public Color traceRay(Ray ray) {
		List<Point> points = scene.geometries.findIntersections(ray);
		return points == null ? scene.background : calcColor(ray.findClosestPoint(points));
	}

	/**
	 * returns the color of a given point
	 * 
	 * @param point the point to calculate
	 * @return the color of the point
	 */
	private Color calcColor(Point point) {
		return scene.ambientLight.getIntensity();
	}

}
