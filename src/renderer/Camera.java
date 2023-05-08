/**
 * 
 */
package renderer;

import primitives.*;

/**
 * represents the camera that creates the rays
 * 
 * @author Shulman and Yonatan
 *
 */
public class Camera {
	private Point position;
	private Vector vUp, vTo, vRight;
	private double height, width, distance;

	/**
	 * getter for position
	 * 
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * getter for vUp
	 * 
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * getter for vTo
	 * 
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * getter for vRight
	 * 
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * getter for height
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * getter for width
	 * 
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * getter for distance
	 * 
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * a constructor that sets all three vector using vTo and vUp. vRight will be
	 * calculated as the cross product of vTo and vUp
	 * 
	 * @param vTo the first vector
	 * @param vUp the second vector
	 * @throws IllegalArgumentException when the vectors are not orthogonal
	 */
	public Camera(Point position, Vector vTo, Vector vUp) {
		if (!Util.isZero(vTo.dotProduct(vUp)))
			throw new IllegalArgumentException("Vectors are not orthogonal");
		this.position = position;
		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		this.vRight = this.vTo.crossProduct(this.vUp);
	}

	/**
	 * setter for view plane size
	 * 
	 * @param width  of the view plane
	 * @param height of the view plane
	 * @return this object
	 */
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * setter for distance from the view plane
	 * 
	 * @param distance from the view plane
	 * @return this object
	 */
	public Camera setVPDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * constructs a ray through a specific pixel and specific resolution
	 * 
	 * @param nX number of pixels in each row
	 * @param nY number of pixels in each column
	 * @param j  the column index of pixel
	 * @param i  the row index of pixel
	 * @return the constructed ray
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point pIJ = position.add(vTo.scale(distance)); // pCenter
		double xJ = Util.alignZero((j - (nX - 1) / 2d) * (width / nX));
		if (xJ != 0)
			pIJ = pIJ.add(vRight.scale(xJ));
		double yI = Util.alignZero(-(i - (nY - 1) / 2d) * (height / nY));
		if (yI != 0)
			pIJ = pIJ.add(vUp.scale(yI));
		return new Ray(position, pIJ.subtract(position));

	}
}
