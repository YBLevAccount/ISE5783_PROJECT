/**
 * 
 */
package unittests.renderer;

import org.junit.jupiter.api.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * tests ImageWriter
 * 
 * @author Shulman and Yonatan
 *
 */
class ImageWriterTests {

	/**
	 * tests {@link renderer.ImageWriter#writeToImage()}.
	 */
	@Test
	void testWriteToImage() {
		ImageWriter imageWriter = new ImageWriter("squares", 800, 500);
		// ============ Equivalence Partitions Tests ==============
		// TC01: 16 X 10 yellow squares with red lines
		Color red = new Color(255, 0, 0);
		Color yellow = new Color(255, 255, 0);
		for (int j = 0; j < 500; ++j)
			for (int i = 0; i < 800; ++i) {
				Color color = (i % 50 <= 1 || j % 50 <= 1) ?  red : yellow;
				imageWriter.writePixel(i, j, color);
			}
		imageWriter.writeToImage();
	}
}
