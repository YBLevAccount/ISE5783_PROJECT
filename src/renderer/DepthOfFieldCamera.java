package renderer;

import java.util.List;

import geometries.UniformRectangleGrid;
import primitives.*;

/**
 * 
 * camera that supports depth of field
 * 
 * @author Shulman and Yonatan
 * 
 */
public class DepthOfFieldCamera extends Camera {
	private double focalDistance;
	private double a;
	private static final int rayNum = 9;

	/**
	 * constructor for depthOfFieldCamera
	 * 
	 * @param position      the position of the camera
	 * @param vTo           the first vector
	 * @param vUp           the second vector
	 * @param focalDistance the distance that the object needs to be in full focus
	 * @param a             decides the blurriness of objects that are not in full
	 *                      focus
	 * @throws IllegalArgumentException when the vectors are not orthogonal
	 */
	public DepthOfFieldCamera(Point position, Vector vTo, Vector vUp, double focalDistance, double a) {
		super(position, vTo, vUp);
		this.focalDistance = focalDistance;
		this.a = a;
	}

	@Override
	protected void castRay(int nX, int nY, int j, int i) {
		Ray mainRay = super.constructRay(getNX(), getNY(), j, i);
		Point focalPoint = mainRay.getPoint(focalDistance);
		UniformRectangleGrid targetArea = new UniformRectangleGrid(mainRay.getP0(), getvUp(), getvRight(), 2 * a, 2 * a);
		List<Point> points = targetArea.generateTargets(rayNum);
		Color totalColor = Color.BLACK;
		for (Point point : points) {
			Ray secondaryRay = new Ray(point, focalPoint.subtract(point));
			totalColor = totalColor.add(rayTracer.traceRay(secondaryRay));
		}
		imageWriter.writePixel(j, i, totalColor.scale(1d / points.size()));
	}
}
