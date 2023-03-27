package unittests.primitives;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * testing Point
 * @author shulm
 *
 */
class PointTests {
	/**
	 * tests {@link Point#add(Vector)}
	 */
	@Test
	void testAdd() {
		// ================= Equivalence Partitions Tests ===========================
		//TC01: tests that function is properly working
		Point p = new Point(-1,1,0);
		p.add(new Vector(2,0,1));
		assertEquals(new Point(1,1,1), p);
	}
	
	/**
	 * tests {@link Point#subtract(Point)}
	 */
	@Test
	void testSubtract() {
		// ================= Equivalence Partitions Tests ===========================
		//TC01: tests that function is properly working
		Point p = new Point(2,2,2);
		Vector v = p.subtract(new Point(1,1,1));
		assertEquals(new Vector(1,1,1), v);
		
		// ================= BVA Tests =========================
		//TC02: tests vector zero 
		p = new Point(-1,3,7);
		v = p.subtract(new Point(-1,3,7));
		assertThrows(IllegalArgumentException.class);
	}

}
