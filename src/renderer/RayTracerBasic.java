package renderer;

import primitives.Color;

import primitives.*;
import static primitives.Util.*;
import scene.*;
import geometries.Intersectable.GeoPoint;
import lighting.*;

/**
 * the most basic rayTracer
 * 
 * @author Shulman and Yonatan
 *
 */
public class RayTracerBasic extends RayTracerBase {

	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;

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
		GeoPoint intersectionPoint = scene.geometries.findClosestIntersection(ray);
		return (intersectionPoint == null) ? scene.background : calcColor(intersectionPoint, ray);
	}

	/**
	 * returns the color of a given point
	 * 
	 * @param point the point to calculate
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint intersection, Ray ray) {
		return scene.ambientLight.getIntensity().add(calcColor(intersection, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE));
	}

	/**
	 * recursive function to calculate the color
	 * 
	 * @param gp    the geoPoint of the intersection
	 * @param ray   the ray of the intersection
	 * @param level the level of recursion we're in
	 * @param k     the current coefficient at our level of recursion
	 * @return the calculated color
	 */
	private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
		Vector v = ray.getDir();
		Vector n = gp.geometry.getNormal(gp.point);
		double nv = alignZero(n.dotProduct(v));
		if (nv == 0)
			return scene.background;

		Color color = calcLocalEffects(gp, n, v, nv, k);
		return 1 == level ? color : color.add(calcGlobalEffects(gp, n, v, nv, level, k));
	}

	/**
	 * calculates the effects of other object in the scene in a specific point
	 * 
	 * @param gp    the specific point and its geometry
	 * @param n     normal to the geometry at the point
	 * @param v     direction of the ray to the point
	 * @param nv    dot-product of (n,v)
	 * @param level the current level of recursion
	 * @param k     the current coefficient at our level of recursion
	 * @return the calculated color
	 */
	private Color calcGlobalEffects(GeoPoint gp, Vector n, Vector v, double nv, int level, Double3 k) {
		Material material = gp.geometry.getMaterial();
		return calcColorGlobalEffect(reflectionRay(n, gp.point, v, nv), level, k, material.kR)
				.add(calcColorGlobalEffect(refractionRay(n, gp.point, v), level, k, material.kT));
	}

	/**
	 * calculates the effect of specific global part of the system
	 * 
	 * @param ray   the ray to trace
	 * @param level of the recursion
	 * @param k     the recursion coefficient
	 * @param kx    the attenuation of the specific part
	 * @return the calculated color
	 */
	private Color calcColorGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
		Double3 kkx = k.product(kx);
		if (kkx.lowerThan(MIN_CALC_COLOR_K))
			return Color.BLACK;

		GeoPoint gp = scene.geometries.findClosestIntersection(ray);
		return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
	}

	/**
	 * calculate the effects of the geometry itself without the effect of other
	 * geometries on this object
	 * 
	 * @param gp the point to calculate the effects on
	 * @param n  normal to the geometry at the point
	 * @param v  direction of the ray to the point
	 * @param nv dot-product of (n,v)
	 * @return the result color of the local effects
	 */
	private Color calcLocalEffects(GeoPoint gp, Vector n, Vector v, double nv, Double3 k) {
		Color color = gp.geometry.getEmission();
		Material material = gp.geometry.getMaterial();
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point);
			double nl = n.dotProduct(l);
			if (alignZero(nl * nv) > 0) {
				Double3 ktr = transparency(gp, lightSource, l, n);
				if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
					Color iL = lightSource.getIntensity(gp.point).scale(ktr);
					color = color.add(iL.scale(calcDiffusive(material, nl)),
							iL.scale(calcSpecular(material, n, l, nl, v)));
				}
			}
		}
		return color;
	}

	/**
	 * calculates the diffusive light part of the object
	 * 
	 * @param material the material of the object
	 * @param cosAngle the cosine of the angle between the light and the normal to
	 *                 the object
	 * @return the diffusive light color
	 */
	private Double3 calcDiffusive(Material material, double cosAngle) {
		return material.kD.scale(cosAngle > 0 ? cosAngle : -cosAngle);
	}

	/**
	 * calculates the specular light part of the object
	 * 
	 * @param material the material of the object
	 * @param normal   the normal to the object
	 * @param lightDir the direction of the light
	 * @param cosAngle the cosine of the angle between the light and the normal to
	 *                 the object
	 * @param rayDir   the direction the camera is pointed to
	 * @return
	 */
	private Double3 calcSpecular(Material material, Vector normal, Vector lightDir, double cosAngle, Vector rayDir) {
		Vector r = lightDir.subtract(normal.scale(2 * cosAngle));
		double coefficient = alignZero(-rayDir.dotProduct(r));
		return coefficient <= 0 ? Double3.ZERO : material.kS.scale(Math.pow(coefficient, material.nShininess));
	}

	/**
	 * calculates the transparency coefficient of a point on a geometry
	 * 
	 * @param gp          the point and its geometry
	 * @param lightSource the light source
	 * @param l           the direction vector of the light source
	 * @param n           the normal to the geometry
	 * @param cosAngle    the cosine of the angle between l and n
	 * @return the transparency coefficient
	 */
	private Double3 transparency(GeoPoint gp, LightSource lightSource, Vector l, Vector n) {
		Ray lightRay = new Ray(n, gp.point, l.scale(-1));
		var intersections = scene.geometries.findGeoIntersections(lightRay, lightSource.getDistance(gp.point));

		Double3 ktr = Double3.ONE;
		if (intersections == null)
			return ktr;
		
		for (GeoPoint intersection : intersections) {
			ktr = ktr.product(intersection.geometry.getMaterial().kT);
			if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
		}
		return ktr;
	}

	/**
	 * calculates the ray for the refraction
	 * 
	 * @param n        the normal vector to the geometry
	 * @param point    the point on our geometry
	 * @param v        the vector coming to the point
	 * @param cosAngle the cosine of the angle between the inVector and the normal
	 *                 vector
	 * @return the calculated ray
	 */
	private Ray refractionRay(Vector n, Point point, Vector v) {
		return new Ray(n, point, v);
	}

	/**
	 * calculates the ray for the reflection
	 * 
	 * @param n     the normal vector to the geometry
	 * @param point the point on our geometry
	 * @param v     the vector coming in to the point
	 * @param nv    dot-product of (n,v)
	 * @return the calculated ray
	 */
	private Ray reflectionRay(Vector n, Point point, Vector v, double nv) {
		return new Ray(n, point, v.subtract(n.scale(2 * nv)));

	}

}
