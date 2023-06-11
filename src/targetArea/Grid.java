package targetArea;

import java.util.List;

import primitives.*;

/**
 * a target area of a rectangle
 * 
 * @author Shulman and Yonatan
 */
public abstract class Grid implements TargetArea {
	protected Point center;
	protected Vector vUp;
	protected Vector vRight;
	protected double length;

	/**
	 * constructor for Grid
	 * 
	 * @param center the center of the grid
	 * @param vUp    the up vector of the grid
	 * @param vRight the right vector of the grid
	 * @param length the length of the sides of the grid
	 * @param height the height of the sides of the grid
	 */
	Grid(Point center, Vector vUp, Vector vRight, double length, double height) {
		this.center = center;
		this.vUp = vUp;
		this.vRight = vRight;
		this.length = length;
	}

	/**
	 * generates targets on an area
	 * 
	 * @param numTargets the number of targets to generate
	 * @return the list of points that were generated
	 */
	public abstract List<Point> generateTargets(int numTargets);
}
