package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Util;
import primitives.Vector;

/**
 * generates targets in a uniform pattern
 * 
 * @author Shulman and Yonatan
 */
public class UniformRectangleGrid extends RectangleGrid {

	/**
	 * constructor for Grid
	 * 
	 * @param center the center of the grid
	 * @param vUp    the up vector of the grid
	 * @param vRight the right vector of the grid
	 * @param length the length of the sides of the grid
	 * @param height the height of the sides of the grid
	 */
	public UniformRectangleGrid(Point center, Vector vUp, Vector vRight, double width, double height) {
		super(center, vUp, vRight, width, height);
	}

	/**
	 * calculates the specific target point inside a given area
	 * 
	 * @param nX the number of targets in each row
	 * @param nY the number of targets in each column
	 * @param j  the target column
	 * @param i  the target row
	 * @return the calculated point
	 */
	public Point calculateTargetPoint(int nX, int nY, int j, int i) {
		Point target = center;
		double xJ = Util.alignZero((j - (nX - 1) / 2d) * (width / nX));
		if (xJ != 0)
			target = target.add(vRight.scale(xJ));
		double yI = Util.alignZero(-(i - (nY - 1) / 2d) * (height / nY));
		if (yI != 0)
			target = target.add(vUp.scale(yI));
		return target;
	}

	@Override
	public List<Point> generateTargets(int numTargets) {
		List<Point> result = new LinkedList<>();
		int sqrtNumTargets = (int) Math.sqrt(numTargets);
		for (int j = 0; j < sqrtNumTargets; j++)
			for (int i = 0; i < sqrtNumTargets; i++) {
				result.add(calculateTargetPoint(sqrtNumTargets, sqrtNumTargets, j, i));
			}
		return result;
	}

}
