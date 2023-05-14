/**
 * 
 */
package renderer;

import java.util.MissingResourceException;

import primitives.*;

/**
 * represents the camera that creates the rays
 * 
 * @author Shulman and Yonatan
 */
public class Camera {
	private Point position;
	private Vector vUp, vTo, vRight;
	private double height, width, distance;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;

	/**
	 * setter for imageWriter
	 * 
	 * @param imageWriter to write the image
	 * @return this object
	 */
	public Camera setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * setter for rayTracer
	 * 
	 * @param rayTracer that will trace a ray
	 * @return this object
	 */
	public Camera setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

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

	/**
	 * this function checks that all the arguments are given
	 * 
	 * @throws MissingResourceException if some resources are missing
	 */
	public void renderImage() {
		if (position == null || vUp == null || vTo == null || vRight == null || height <= 0 || width <= 0
				|| distance <= 0 || imageWriter == null || rayTracer == null)
			throw new MissingResourceException("Arguments Are Missing", "Camera",
					"Set the Argument That Has Not Been Set");
		int nY = imageWriter.getNY();
		int nX = imageWriter.getNX();
		for (int j = 0; j < nY; ++j)
			for (int i = 0; i < nX; ++i)
				imageWriter.writePixel(j, i, castRay(j, i));
	}

	/**
	 * adds a grid that shows the pixels to the image
	 * 
	 * @param interval the space between lines in the grid
	 * @param color    the color of the lines in the grid
	 * @throws MissingResourceException if the image writer is not initialized
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("Image Writer is Missing", "Camera", "Set Image Writer");
		int nY = imageWriter.getNY();
		int nX = imageWriter.getNX();
		for (int j = 0; j < nY; j += interval)
			for (int i = 0; i < nX; ++i)
				imageWriter.writePixel(i, j, color);
		for (int j = 0; j < nY; ++j)
			for (int i = 0; i < nX; i += interval)
				imageWriter.writePixel(i, j, color);
	}

	/**
	 * writes the image to a file
	 * 
	 * @throws MissingResourceException if the image writer is not initialized
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("Image Writer is Missing", "Camera", "Set Image Writer");
		imageWriter.writeToImage();
	}

	/**
	 * finds the color of a pixel
	 * 
	 * @param j the column index of pixel
	 * @param i the row index of pixel
	 * @return the calculated color of the pixel
	 */
	private Color castRay(int j, int i) {
		return rayTracer.traceRay(constructRay(imageWriter.getNX(), imageWriter.getNY(), j, i));
	}

}
