package renderer;

import java.util.LinkedList;
import java.util.List;

import geometries.*;
import primitives.*;

/**
 * @author Shulman and Yonatan
 *
 */
public class AdaptiveRay {
	private UniformRectangleGrid targetArea;
	private Point position;
	private boolean forward;
	private int numSplits = 4;

	/**
	 * constructor for AdaptiveRay
	 * 
	 * @param targetArea the area that needs to be focused
	 * @param position   the position that needs to be focused
	 * @param forward    True if the ray is from position to targetArea, False
	 *                   otherwise
	 */
	public AdaptiveRay(UniformRectangleGrid targetArea, Point position, boolean forward) {
		this.targetArea = targetArea;
		this.position = position;
		this.forward = forward;
	}

	/**
	 * creates the main ray used for the super sampling
	 * 
	 * @return the main ray
	 */
	public Ray getRay() {
		Point center = targetArea.getCenter();
		Vector direction = center.subtract(position);
		return (forward ? new Ray(position, direction) : new Ray(center, direction.scale(-1)));
	}

	public List<AdaptiveRay> splitRay() {
		List<Point> points = targetArea.generateTargets(numSplits);
		List<AdaptiveRay> adaptiveRays = new LinkedList<>();
		for (Point point : points)
			adaptiveRays.add(new AdaptiveRay(new UniformRectangleGrid(point, targetArea.getVUp(),
					targetArea.getVRight(), targetArea.getHeight() / Math.sqrt(numSplits),
					targetArea.getWidth() / Math.sqrt(numSplits)), position, forward));
		return adaptiveRays;
	}
}
