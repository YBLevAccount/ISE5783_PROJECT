package renderer;

import primitives.Color;

import primitives.*;
import scene.*;
import geometries.Intersectable.GeoPoint;
import lighting.*;

import java.util.List;

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
		return (geoPoints == null) ? scene.background : calcColor(ray.findClosestGeoPoint(geoPoints), ray);
	}

	/**
	 * returns the color of a given point
	 * 
	 * @param point the point to calculate
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		return scene.ambientLight.getIntensity().add(calcLocalEffects(intersection, ray));
	}

	/**
	 * calculate the effects of the geometry itself without the effect of other
	 * geometries on this object
	 * 
	 * @param gp  the point to calculate the effects on
	 * @param ray the ray we intersected with
	 * @return the result color of the local effects
	 */
	private Color calcLocalEffects(GeoPoint gp, Ray ray) {
		Color color = gp.geometry.getEmission();
		Vector v = ray.getDir();
		Vector n = gp.geometry.getNormal(gp.point);
		double nv = Util.alignZero(n.dotProduct(v));
		if (nv == 0)
			return color;
		Material material = gp.geometry.getMaterial();
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color iL = lightSource.getIntensity(gp.point);
				color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, nl, v)));
			}
		}
		return color;
	}

	private Double3 calcDiffusive(Material material, double cosAngle) {
		return material.kD.scale(cosAngle > 0 ? cosAngle : -cosAngle);
	}

	private Double3 calcSpecular(Material material, Vector normal, Vector lightDir, double cosAngle, Vector rayDir) {
		Vector r = lightDir.subtract(normal.scale(2 * cosAngle));
		double coefficient = -rayDir.dotProduct(r);
		coefficient = coefficient > 0 ? coefficient : 0;
		return material.kS.scale(Math.pow(coefficient, material.nShininess));
	}
}
