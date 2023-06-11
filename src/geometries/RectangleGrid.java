package geometries;

import java.util.List;

import primitives.*;

/**
 * a target area of a rectangle
 * 
 * @author Shulman and Yonatan
 */
public abstract class RectangleGrid implements TargetArea {
	protected Point center;
	protected Vector vUp;
	protected Vector vRight;
	protected double width;
	protected double height;

	/**
	 * constructor for Grid
	 * 
	 * @param center the center of the grid
	 * @param vUp    the up vector of the grid
	 * @param vRight the right vector of the grid
	 * @param length the length of the sides of the grid
	 * @param height the height of the sides of the grid
	 */
	public RectangleGrid(Point center, Vector vUp, Vector vRight, double width, double height) {
		this.center = center;
		this.vUp = vUp;
		this.vRight = vRight;
		this.width = width;
		this.height = height;
	}

	/**
	 * generates targets on an area
	 * 
	 * @param numTargets the number of targets to generate
	 * @return the list of points that were generated
	 */
	public abstract List<Point> generateTargets(int numTargets);

	/**
	 * getter for the width
	 * 
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * setter for the width
	 * 
	 * @param width the width to set
	 * @return this object
	 */
	public RectangleGrid setWidth(double width) {
		this.width = width;
		return this;
	}

	/**
	 * getter for the height
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * setter for the height
	 * 
	 * @param height the height to set
	 * @return this object
	 */
	public RectangleGrid setHeight(double height) {
		this.height = height;
		return this;
	}

}
