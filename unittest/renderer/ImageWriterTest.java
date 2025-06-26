package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static primitives.Util.*;
class ImageWriterTests {
    /**
     * test
     */
    @Test
    public void testImageWriter() {

        ImageWriter image = new ImageWriter( "myImage",800, 500);
        for (int j = 0; j < 800; j++) {
            if (isZero(j % 50))
                for (int i = 0; i < 500; i++)
                    image.writePixel(j, i, Color.GREEN);
            else
                for (int i = 0; i < 500; i++) {
                    if (isZero(i % 50))
                        image.writePixel(j, i, Color.GREEN);
                    else
                        image.writePixel(j, i, new Color(7, 10, 170));
                }
        }
        image.writeToImage();
    }
}