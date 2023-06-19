/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;

/**
 * tests UniformRectangleGrid
 * 
 * @author Yonatan and Shulman
 *
 */
class UniformRectangleGridTests {

	/**
	 * tests
	 * {@link geometries.UniformRectangleGrid#calculateTargetPoint(int, int, int, int)}
	 */
	@Test
	void calculateTargetPointTest() {
		UniformRectangleGrid urg = new UniformRectangleGrid(new Point(0, 0, 10), new Vector(1, 0, 0),
				new Vector(0, 1, 0), 24, 24);
		String badPoint = "wrong point calculation";

		// ============ Equivalence Partitions Tests ==============
		// EP01: 4X4 Inside (1,1)
		assertEquals(new Point(3, -3, 10), urg.calculateTargetPoint(4, 4, 1, 1), badPoint);
		
		// =============== Boundary Values Tests ==================
		// BV01: 3X3 Center (1,1)
		assertEquals(new Point(0, 0, 10), urg.calculateTargetPoint(3, 3, 1, 1), badPoint);

		// BV02: 3X3 Center of Upper Side (0,1)
		assertEquals(new Point(8, 0, 10), urg.calculateTargetPoint(3, 3, 1, 0), badPoint);

		// BV03: 3X3 Center of Left Side (1,0)
		assertEquals(new Point(0, -8, 10), urg.calculateTargetPoint(3, 3, 0, 1), badPoint);

		// BV04: 3X3 Corner (0,0)
		assertEquals(new Point(8, -8, 10), urg.calculateTargetPoint(3, 3, 0, 0), badPoint);

		// BV05: 4X4 Corner (0,0)
		assertEquals(new Point(9, -9, 10), urg.calculateTargetPoint(4, 4, 0, 0), badPoint);

		// BV06: 4X4 Side (0,1)
		assertEquals(new Point(9, -3, 10), urg.calculateTargetPoint(4, 4, 1, 0), badPoint);
	}

}
