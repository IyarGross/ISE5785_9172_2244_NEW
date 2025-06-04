package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

class ImageWriterTest {
    /**
     * Test method for {@link .${CLASS_NAME}.Name(.${CLASS_NAME})}.
     */
    @Test
    void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("yellow", 800, 500);
        Color red = new Color(255, 0, 0);
        for (int i = 0; i < imageWriter.getnX(); i++) {
            for (int j = 0; j < imageWriter.getnY(); j++) {
                imageWriter.writePixel(i, j, new Color(java.awt.Color.YELLOW));
            }
        }
        for (int i = 0; i < 800; i += 50) {

            for (int q = 0; q < 500; q += 50) {
                imageWriter.writePixel(i, q, red);
            }

        }
        imageWriter.writeToImage();
    }
}
