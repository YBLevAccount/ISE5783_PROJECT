package renderer;

import primitives.*;
import targetArea.UniformGrid;

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
	public Color castRay(int j, int i) {
		Ray mainRay = super.constructRay(getNX(), getNY(), j, i);
		Point focalPoint = mainRay.getPoint(focalDistance);
		Point pixelCenter = mainRay.getPoint(getDistance() / mainRay.getDir().dotProduct(getvTo()));
		UniformGrid uniformGrid = new UniformGrid(pixelCenter, getvUp(), getvRight(), 2 * a, 2 * a);
		return null;
	}
}
