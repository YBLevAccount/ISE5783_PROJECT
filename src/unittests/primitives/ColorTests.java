/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.*;

/**
 * tests Color
 * 
 * @author Yonatan and Shulman
 *
 */
class ColorTests {

	/**
	 * tests {@link primitives.Color#closeTo(primitives.Color, double)}
	 */
	@Test
	void testCloseTo() {
		Color a = new Color(151, 151, 150);
		Color b = new Color(150, 150, 149);
		// EP
		// TC01: a close to b 
		assertTrue(a.closeTo(b, 2));
		// TC02: a is not close to b
		assertFalse(a.closeTo(b, 0.5));
		
		// BVA
		// TC03: the difference is exact
		assertFalse(a.closeTo(b, 1));
	}

}
