package geometries;

import java.util.List;

import primitives.*;

/**
 * a target area of a rectangle
 * 
 * @author Shulman and Yonatan
 */
public abstract class RectangleGrid implements TargetArea {
	/**
	 * center of the rectangle
	 */
	protected final Point center;
	/**
	 * vector pointing up
	 */
	protected final Vector vUp;
	/**
	 * vector pointing down
	 */
	protected final Vector vRight;
	/**
	 * the width of the rectangle
	 */
	protected final double width;
	/**
	 * the height of the rectangle
	 */
	protected final double height;

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
	 * getter for the height
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * getter for the center
	 * 
	 * @return the center
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * getter for the vector vUp
	 * 
	 * @return the vector vUp
	 */
	public Vector getVUp() {
		return vUp;
	}

	/**
	 * getter for the vector vRight
	 * 
	 * @return the vector vRight
	 */
	public Vector getVRight() {
		return vUp;
	}

}
