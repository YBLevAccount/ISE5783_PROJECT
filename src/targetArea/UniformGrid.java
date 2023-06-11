package targetArea;

import java.util.List;

import primitives.Point;
import primitives.Vector;

/**
 * generates targets in a uniform pattern
 * 
 * @author Shulman and Yonatan
 */
public class UniformGrid extends Grid {

	/**
	 * constructor for Grid
	 * 
	 * @param center the center of the grid
	 * @param vUp    the up vector of the grid
	 * @param vRight the right vector of the grid
	 * @param length the length of the sides of the grid
	 * @param height the height of the sides of the grid
	 */
	public UniformGrid(Point center, Vector vUp, Vector vRight, double length, double height) {
		super(center, vUp, vRight, length, height);
	}

	@Override
	public List<Point> generateTargets(int numTargets) {
		return null;
	}

}
