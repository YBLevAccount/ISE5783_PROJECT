package renderer;

import primitives.Color;


import primitives.Ray;
import scene.*;

import java.util.List;

import geometries.Intersectable.GeoPoint;

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
		List<GeoPoint> geoPoints = scene.geometries.findGeoIntersections(ray);
		return (geoPoints == null) ? scene.background : calcColor(ray.findClosestGeoPoint(geoPoints));
	}

	/**
	 * returns the color of a given point
	 * 
	 * @param point the point to calculate
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geoPoint) {
		return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
	}

}
