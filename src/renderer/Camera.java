package renderer;

import java.util.MissingResourceException;

import geometries.*;
import primitives.*;
import renderer.PixelManager.Pixel;

import static primitives.Util.*;

import java.util.LinkedList;

/**
 * represents the camera that creates the rays
 * 
 * @author Shulman and Yonatan
 */
public class Camera {
	private final Point position;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double height;
	private double width;
	private UniformRectangleGrid vp = null;
	private double distance;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;
	private double focalDistance;
	private double apertureLength = 0;
	private UniformRectangleGrid cp = null;
	private AdaptiveSuperSampler adaptiveSuperSampler = new AdaptiveSuperSampler();

	/**
	 * Pixel manager for supporting:
	 * <ul>
	 * <li>multi-threading</li>
	 * <li>debug print of progress percentage in Console window/tab</li>
	 * <ul>
	 */
	private PixelManager pixelManager;
	private int threadNum = 0;

	/**
	 * finds the center of the view plane
	 * 
	 * @return the center of the view plane
	 */
	public Point findVPCenter() {
		return position.add(vTo.scale(distance));
	}

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
	 * getter for the number of pixels on the x axis
	 * 
	 * @return the number of pixels on the x axis
	 */
	public int getNX() {
		return imageWriter.getNX();
	}

	/**
	 * getter for the number of pixels on the y axis
	 * 
	 * @return the number of pixels on the y axis
	 */
	public int getNY() {
		return imageWriter.getNY();
	}

	/**
	 * getter for maxRecursionLevel
	 * 
	 * @return the maxRecursionLevel
	 */
	public int getMaxRecursionLevel() {
		return adaptiveSuperSampler.getMaxRecursion();
	}

	/**
	 * setter for maxRecursionLevel
	 * 
	 * @param maxRecursionLevel the maxRecursionLevel to set
	 * @return this object
	 */
	public Camera setMaxRecursionLevel(int maxRecursionLevel) {
		adaptiveSuperSampler.setMaxRecursion(maxRecursionLevel);
		return this;
	}

	/**
	 * a constructor that sets all three vector using vTo and vUp. vRight will be
	 * calculated as the cross product of vTo and vUp
	 * 
	 * @param position the position of the camera
	 * @param vTo      the first vector
	 * @param vUp      the second vector
	 * @throws IllegalArgumentException when the vectors are not orthogonal
	 */
	public Camera(Point position, Vector vTo, Vector vUp) {
		if (!isZero(vTo.dotProduct(vUp)))
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
		vp = null;
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
		vp = null;
		return this;
	}

	/**
	 * getter for the focal distance
	 * 
	 * @return the focalDistance
	 */
	public double getFocalDistance() {
		return focalDistance;
	}

	/**
	 * setter for the focal distance
	 * 
	 * @param focalDistance the focalDistance to set
	 * @return this object
	 */
	public Camera setFocalDistance(double focalDistance) {
		this.focalDistance = focalDistance;
		return this;
	}

	/**
	 * getter for the aperture length
	 * 
	 * @return the aperture length
	 */
	public double getApertureLength() {
		return apertureLength;
	}

	/**
	 * setter for aperture length for depth of field
	 * 
	 * @param apertureLength new aperture length
	 * @return this object
	 */
	public Camera setApertureLength(double apertureLength) {
		this.apertureLength = alignZero(apertureLength);
		cp = null;
		return this;
	}

	/**
	 * getter for number of beams per each pixel
	 * 
	 * @return the number of beams
	 */
	public int getRayNum() {
		return adaptiveSuperSampler.getRayNum();
	}

	/**
	 * setter for the number of beams per each pixel
	 * 
	 * @param rayNum the new number of beams
	 * @return this object
	 */
	public Camera setRayNum(int rayNum) {
		adaptiveSuperSampler.setRayNum(rayNum);
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
		Point pIJ = findVPCenter(); // pCenter
		if (vp == null)
			vp = new UniformRectangleGrid(findVPCenter(), vUp, vRight, width, height);
		pIJ = vp.calculateTargetPoint(nX, nY, j, i);
		return new Ray(position, pIJ.subtract(position));

	}

	/**
	 * this function checks that all the arguments are given
	 * 
	 * @return this object
	 * 
	 * @throws MissingResourceException if some resources are missing
	 */
	public Camera renderImage() {
		if (position == null || vUp == null || vTo == null || vRight == null || height <= 0 || width <= 0
				|| distance <= 0 || imageWriter == null || rayTracer == null
				|| (adaptiveSuperSampler.getMaxRecursion() >= 1
						&& (apertureLength <= 0 || adaptiveSuperSampler.getRayNum() <= 0 || focalDistance <= 0)))
			throw new MissingResourceException("Arguments Are Missing", "Camera",
					"Set the Argument That Has Not Been Set");
		int nY = imageWriter.getNY();
		int nX = imageWriter.getNX();
		pixelManager = new PixelManager(nY, nX, 0);
		if (threadNum == 0)
			for (int i = 0; i < nY; ++i)
				for (int j = 0; j < nX; ++j)
					castRay(nX, nY, j, i);
		else { // see further... option 2
			int threadsCount = threadNum;
			var threads = new LinkedList<Thread>(); // list of threads
			while (threadsCount-- > 0) // add appropriate number of threads
				threads.add(new Thread(() -> { // add a thread with its code
					Pixel pixel; // current pixel(row,col)
					// allocate pixel(row,col) in loop until there are no more pixels
					while ((pixel = pixelManager.nextPixel()) != null)
						// cast ray through pixel (and color it – inside castRay)
						castRay(nX, nY, pixel.col(), pixel.row());
				}));
			// start all the threads
			for (Thread thread : threads)
				thread.start();
			// wait until all the threads have finished
			try {
				for (Thread thread : threads)
					thread.join();
			} catch (InterruptedException ignore) {
			}
		}
		return this;
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
	 * @param nX number of pixels in row
	 * @param nY number of pixels in column
	 * @param j  the column index of pixel
	 * @param i  the row index of pixel
	 */

	private void castRay(int nX, int nY, int j, int i) {
		Ray mainRay = constructRay(nX, nY, j, i);
		if (adaptiveSuperSampler.getMaxRecursion() == 0) {
			imageWriter.writePixel(j, i, rayTracer.traceRay(mainRay));
			pixelManager.pixelDone();
			return;
		}
		if (cp == null)
			cp = new UniformRectangleGrid(position, vUp, vRight, apertureLength, apertureLength);
		imageWriter.writePixel(j, i,
				adaptiveSuperSampler.traceAdaptiveRay(
						new AdaptiveRay(cp, mainRay.getPoint(focalDistance / vTo.dotProduct(mainRay.getDir())), false),
						rayTracer));
		pixelManager.pixelDone();
	}

	/**
	 * create a camera from a given point and a target point using orientation
	 * 
	 * @param position the position of the camera
	 * @param target   the target of the camera
	 * @return this object
	 */
	public Camera(Point position, Point target) {
		this.position = position;
		vTo = target.subtract(position).normalize();
		Vector g = new Vector(0, 0, -1);
		if (vTo.equals(g) || vTo.equals(g.scale(-1))) { // in that case random values for vRight and vUp
			vRight = new Vector(1, 0, 0);
			vUp = new Vector(0, 1, 0);
			return;
		}
		vRight = g.crossProduct(vTo).normalize();
		vUp = vRight.crossProduct(vTo);
	}

	/**
	 * rotate the camera clockwise by a given angle
	 * 
	 * @param angle the angle to rotate
	 * @return this object
	 */
	public Camera rotate(double angle) {
		vp = null;
		cp = null;
		double cosAngle = alignZero(Math.cos(angle * Math.PI / 180));
		double sinAngle = 0;
		Vector newVRight;
		if (cosAngle == 0) {
			sinAngle = isZero(angle - 90) ? 1.d : -1.d;
			newVRight = vUp.scale(-sinAngle);
			vUp = vRight.scale(sinAngle);
			vRight = newVRight;
			return this;
		}
		sinAngle = alignZero(Math.sin(angle * Math.PI / 180));
		if (sinAngle == 0) {
			newVRight = vUp.scale(cosAngle);
			vUp = vRight.scale(cosAngle);
			vRight = newVRight;
			return this;
		}
		newVRight = vUp.scale(-sinAngle).add(vRight.scale(cosAngle));
		vUp = vUp.scale(cosAngle).add(vRight.scale(sinAngle));
		vRight = newVRight;

		return this;
	}

	/**
	 * getter for number of threads in use
	 * 
	 * @return the number of threads in use
	 */
	public int getThreadNum() {
		return threadNum;
	}

	/**
	 * setter for number of threads in use
	 * 
	 * @param threadNum the new number of threads in use
	 * @return this object
	 */
	public Camera setThreadNum(int threadNum) {
		this.threadNum = threadNum;
		return this;
	}

}
