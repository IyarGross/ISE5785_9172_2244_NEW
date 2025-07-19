package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void FirstImage() {
        assertDoesNotThrow(() -> {
            Color turquoise = new Color(17, 236, 192);
            Color black = new Color(java.awt.Color.BLACK);
            ImageWriter imageWriter = new ImageWriter(801, 501);
            for (int i = 0; i <= 800; i++) {
                for (int j = 0; j <= 500; j++) {
                    if (i % 50 == 0 || j % 50 == 0) {
                        imageWriter.writePixel(i, j, black);
                    } else {
                        imageWriter.writePixel(i, j,turquoise);
                    }
                }
            }
            imageWriter.writeToImage("firstImage");
        }, "Failed to create image");
    }
}