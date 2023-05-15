package renderer;

import scene.Scene;
import primitives.*;

/**
 * this class finds the ray intersection with our scene
 * 
 * @author Shulman and Yonatan
 * 
 */
public abstract class RayTracerBase {
	/**
	 * the scene that contains the geometries and lighting
	 */
	protected final Scene scene;

	/**
	 * construct rayTracer with specific scene
	 * 
	 * @param scene with the relevant geometries and lighting
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * this method takes a ray and find the color at the first intersection
	 * 
	 * @param ray the ray to intersect with geometries
	 * @return the calculated color
	 */
	abstract public Color traceRay(Ray ray);

}
