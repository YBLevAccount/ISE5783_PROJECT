package targetArea;

import java.util.List;

import primitives.Point;

/**
 * generates target points on an area
 * 
 * @author Shulman and Yonatan
 * 
 */
public interface TargetArea {
	/**
	 * generates targets on an area
	 * 
	 * @param numTargets the number of targets to generate
	 * @return the list of points that were generated
	 */
	public List<Point> generateTargets(int numTargets);
}
