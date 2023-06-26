package renderer;

import java.util.List;
import primitives.*;

/**
 * this class does adaptive super sampling for beams of ray
 * 
 * @author Shulman and Yonatan
 *
 */
public class AdaptiveSuperSampler {
	private static final double maxDifference = 3d;
	private int rayNum = 4; // default value
	private int maxRecursion = 0;
	private RayTracerBase rayTracer = null;

	/**
	 * constructor for AdaptiveSuperSampler
	 * 
	 **/
	public AdaptiveSuperSampler() {
	}

	/**
	 * calculates the color using adaptive super sampling
	 * 
	 * @param adaptiveRay the current adaptive ray that we are calculating
	 * @param rayTracer   the ray tracer to calculate with
	 * @return the calculated color
	 */
	public Color traceAdaptiveRay(AdaptiveRay adaptiveRay, RayTracerBase rayTracer) {
		Color baseColor = rayTracer.traceRay(adaptiveRay.getRay());
		this.rayTracer = rayTracer;
		return calcColor(adaptiveRay, baseColor, maxRecursion);

	}

	/**
	 * implements the recursion of adaptive super sampling
	 * 
	 * @param adaptiveRay    the current adaptive ray that we are calculating
	 * @param lastColor      the color of the previous stage of recursion
	 * @param recursionLevel the current level of recursion
	 * @return the calculated color
	 */
	private Color calcColor(AdaptiveRay adaptiveRay, Color lastColor, int recursionLevel) {
		List<AdaptiveRay> secondaryRays = adaptiveRay.splitRay(rayNum);
		Color totalColor = lastColor;
		for (AdaptiveRay secondaryRay : secondaryRays) {
			Color secondaryColor = rayTracer.traceRay(secondaryRay.getRay());
			totalColor = totalColor
					.add((lastColor.closeTo(secondaryColor, maxDifference) || recursionLevel == 1) ? secondaryColor
							: calcColor(secondaryRay, secondaryColor, recursionLevel - 1));
		}
		return totalColor.reduce(secondaryRays.size() + 1);
	}

	/**
	 * getter for rayNum
	 * 
	 * @return the rayNum
	 */
	public int getRayNum() {
		return rayNum;
	}

	/**
	 * getter for rayNum
	 * 
	 * @param rayNum the rayNum to set
	 * @return this object
	 */
	public AdaptiveSuperSampler setRayNum(int rayNum) {
		this.rayNum = rayNum;
		return this;
	}

	/**
	 * getter for maxRecursion
	 * 
	 * @return the maxRecursion
	 */
	public int getMaxRecursion() {
		return maxRecursion;
	}

	/**
	 * setter for maxRecursion
	 * 
	 * @param maxRecursion the maxRecursion to set
	 * @return this object
	 */
	public AdaptiveSuperSampler setMaxRecursion(int maxRecursion) {
		this.maxRecursion = maxRecursion;
		return this;
	}

}
